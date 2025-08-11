package com.vaddi.workflow;

import com.vaddi.dto.TravelRequest;

import io.temporal.workflow.SignalMethod;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface TravelWorkflow {

    @WorkflowMethod
    void bookTrip(TravelRequest travelRequest);


    @SignalMethod
    public void sendConfirmationSignal();

}
