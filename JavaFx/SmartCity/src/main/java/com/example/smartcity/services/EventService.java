package com.example.smartcity.services;

import com.example.smartcity.models.EmergencyEvent;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EventService {

    // List to store emergency events
    private List<EmergencyEvent> emergencyEvents;

    // Constructor
    public EventService() {
        this.emergencyEvents = new ArrayList<>();
    }

    // Add a new emergency event
    public void addEmergencyEvent(String type, String location, String severity) {
        EmergencyEvent event = new EmergencyEvent(type, location, LocalDateTime.now(), severity);
        emergencyEvents.add(event);
        System.out.println("Emergency Event Added: " + event);
    }

    // Resolve an emergency event by its index
    public void resolveEmergencyEvent(int index) {
        if (index >= 0 && index < emergencyEvents.size()) {
            EmergencyEvent event = emergencyEvents.get(index);
            event.resolve();
            System.out.println("Emergency Event Resolved: " + event);
        } else {
            System.out.println("Invalid event index!");
        }
    }

    // Get all active (unresolved) events
    public List<EmergencyEvent> getActiveEvents() {
        return emergencyEvents.stream()
                .filter(event -> !event.isResolved())
                .collect(Collectors.toList());
    }

    // Get all events
    public List<EmergencyEvent> getAllEvents() {
        return new ArrayList<>(emergencyEvents);
    }

    // Remove resolved events
    public void clearResolvedEvents() {
        emergencyEvents = emergencyEvents.stream()
                .filter(event -> !event.isResolved())
                .collect(Collectors.toList());
        System.out.println("Resolved events cleared!");
    }

    // Print all events for debugging
    public void printAllEvents() {
        if (emergencyEvents.isEmpty()) {
            System.out.println("No emergency events recorded.");
        } else {
            System.out.println("All Emergency Events:");
            emergencyEvents.forEach(System.out::println);
        }
    }
}
