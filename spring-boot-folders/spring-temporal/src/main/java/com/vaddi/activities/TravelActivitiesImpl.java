package com.vaddi.activities;

import org.springframework.stereotype.Service;

import com.vaddi.dto.TravelRequest;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TravelActivitiesImpl implements TravelActivities {

    @Override
    public void bookFlight(TravelRequest travelRequest) {
        // rest call to flight service
        log.info("Flight booked for user: {} to destination: {} on date: {}",
                travelRequest.getUserId(),
                travelRequest.getDestination(),
                travelRequest.getTravelDate());

    }

    @Override
    public void cancelFlight(TravelRequest travelRequest) {
        log.info("🛑 Cancelling flight for user {} because of failure",
                travelRequest.getUserId());
    }

    @Override
    public void bookHotel(TravelRequest travelRequest) {
        // gRPC call to hotel service
        log.info("Hotel booked for user: {} at destination: {} on date: {}",
                travelRequest.getUserId(),
                travelRequest.getDestination(),
                travelRequest.getTravelDate());
    }

    @Override
    public void cancelHotel(TravelRequest travelRequest) {
        log.info("🛑 Cancelling hotel for user {} because of failure",
                travelRequest.getUserId());
    }

    @Override
    public void arrangeTransport(TravelRequest travelRequest) {
        // Kafka message to transport service

        log.info("Transport arranged for user: {} at destination: {} on date: {}",
                travelRequest.getUserId(),
                travelRequest.getDestination(),
                travelRequest.getTravelDate());

        //simulate a failure to demonstrate compensation
       throw new RuntimeException("Simulated transport arrangement failure!");
    }

    @Override
    public void cancelTransport(TravelRequest travelRequest) {
        log.info("🛑 Cancelling transport for user {}",
                travelRequest.getUserId());
    }

    @Override
    public void cancelBooking(TravelRequest travelRequest) {
        log.info("Cancelling booking for user: {} at destination: {} on date: {}",
                travelRequest.getUserId(),
                travelRequest.getDestination(),
                travelRequest.getTravelDate());
    }

    @Override
    public void confirmBooking(TravelRequest travelRequest) {
        log.info("Booking confirmed for user: {} at destination: {} on date: {}",
                travelRequest.getUserId(),
                travelRequest.getDestination(),
                travelRequest.getTravelDate());
    }
}
