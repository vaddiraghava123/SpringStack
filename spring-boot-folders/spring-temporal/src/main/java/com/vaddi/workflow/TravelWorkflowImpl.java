package com.vaddi.workflow;

import java.time.Duration;

import org.springframework.stereotype.Service;

import com.vaddi.activities.TravelActivities;
import com.vaddi.dto.TravelRequest;

import io.temporal.activity.ActivityOptions;
import io.temporal.common.RetryOptions;
import io.temporal.workflow.Saga;
import io.temporal.workflow.SignalMethod;
import io.temporal.workflow.Workflow;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TravelWorkflowImpl implements TravelWorkflow {


    private boolean isUserConfirmed = false;

    @SignalMethod
    public void sendConfirmationSignal() {
        log.info("📩 Received user confirmation signal.");
        isUserConfirmed = true;
    }


    @Override
    public void bookTrip(TravelRequest travelRequest) {

        log.info("🚀 Starting travel booking for user: {}", travelRequest.getUserId());

        TravelActivities activities = Workflow.newActivityStub(TravelActivities.class,
                ActivityOptions.newBuilder()
                        .setRetryOptions(RetryOptions.newBuilder()
                                .setMaximumAttempts(3)
                                .build())
                        .setStartToCloseTimeout(Duration.ofSeconds(10))
                        .build());

        Saga.Options sagaOptions = new Saga.Options.Builder()
                .setParallelCompensation(false)
                .build();

        Saga saga = new Saga(sagaOptions);

        try {

            activities.bookFlight(travelRequest);
            saga.addCompensation(() -> activities.cancelFlight(travelRequest));

            activities.bookHotel(travelRequest);
            saga.addCompensation(() -> activities.cancelHotel(travelRequest));

            activities.arrangeTransport(travelRequest);
            saga.addCompensation(() -> activities.cancelTransport(travelRequest));

            // 24 hours (1 day) -> wait for user confirmation if you won't
            // get any withing 24hr then cancel it

            log.info("⏳ Waiting for user confirmation for 2 min...");

            boolean isConfirmed = Workflow.await(
                    Duration.ofMinutes(2),
                    () -> isUserConfirmed
            );

            if (!isConfirmed) {
                log.info("🛑 User did not confirm within 2 minutes, cancelling the booking for user: {}", travelRequest.getUserId());
                //cancel the booking
                activities.cancelBooking(travelRequest);
            } else {
                log.info("✅ User confirmed the booking: {}", travelRequest.getUserId());
                //confirm the booking
                activities.confirmBooking(travelRequest);
            }


        } catch (Exception e) {
            log.error("❌ Error during travel booking for user: {}. Initiating compensation.", travelRequest.getUserId());
            saga.compensate();
        }

        log.info("✅ Travel booking completed for user: {}", travelRequest.getUserId());

    }
}
