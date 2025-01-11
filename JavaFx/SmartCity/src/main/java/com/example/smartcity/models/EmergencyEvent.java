package com.example.smartcity.models;

import java.time.LocalDateTime;

public class EmergencyEvent {

    // Properties of the emergency event
    private String type;            // Type of emergency (e.g., Fire, Accident, Medical)
    private String location;        // Location description of the emergency
    private LocalDateTime timestamp; // Time when the emergency occurred
    private String severity;        // Severity level (e.g., Low, Medium, High)
    private boolean resolved;       // Status of the emergency

    // Constructor
    public EmergencyEvent(String type, String location, LocalDateTime timestamp, String severity) {
        this.type = type;
        this.location = location;
        this.timestamp = timestamp;
        this.severity = severity;
        this.resolved = false; // Default to unresolved when created
    }

    // Getters and Setters
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public boolean isResolved() {
        return resolved;
    }

    public void setResolved(boolean resolved) {
        this.resolved = resolved;
    }

    // Mark the emergency as resolved
    public void resolve() {
        this.resolved = true;
    }

    // String representation of the emergency event
    @Override
    public String toString() {
        return "EmergencyEvent{" +
                "type='" + type + '\'' +
                ", location='" + location + '\'' +
                ", timestamp=" + timestamp +
                ", severity='" + severity + '\'' +
                ", resolved=" + resolved +
                '}';
    }
}
