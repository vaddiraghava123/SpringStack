package com.vaddi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vaddi.dto.TravelRequest;
import com.vaddi.starter.TravelBookingWorkflowStarter;

@RestController
@RequestMapping("/travel")
public class TravelWorkflowController {

    private final TravelBookingWorkflowStarter starter;

    public TravelWorkflowController(TravelBookingWorkflowStarter starter) {
        this.starter = starter;
    }

    // Endpoint to start the travel booking workflow
    @PostMapping("/book")
    public ResponseEntity<String> bookTravel(@RequestBody TravelRequest travelRequest) {
        starter.startWorkFlow(travelRequest);
        return ResponseEntity.ok("Travel booking workflow started for user: " + travelRequest.getUserId());
    }

    // Endpoint to confirm the booking by sending a signal to the workflow
    @PostMapping("/confirm/{userId}")
    public ResponseEntity<String> confirmBooking(@RequestParam String userId) {
        starter.sendConfirmationSignal(userId);
        return ResponseEntity.ok("✅ Booking confirmed by user!");
    }


}
