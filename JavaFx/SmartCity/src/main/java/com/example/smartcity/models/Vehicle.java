package com.example.smartcity.models;

public class Vehicle {

    // Properties of the vehicle
    private String id;             // Unique identifier for the vehicle
    private String type;           // Type of the vehicle (e.g., Car, Bus, Emergency)
    private String fuelType;       // Fuel type (e.g., Gasoline, Diesel, Electric)
    private double speed;          // Current speed of the vehicle in km/h
    private double fuelEfficiency; // Fuel efficiency in km/l (for non-electric vehicles)
    private boolean inMotion;      // Indicates whether the vehicle is currently in motion

    // Constructor
    public Vehicle(String id, String type, String fuelType, double speed, double fuelEfficiency) {
        this.id = id;
        this.type = type;
        this.fuelType = fuelType;
        this.speed = speed;
        this.fuelEfficiency = fuelEfficiency;
        this.inMotion = speed > 0; // Set inMotion based on speed
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
        this.inMotion = speed > 0; // Update inMotion based on speed
    }

    public double getFuelEfficiency() {
        return fuelEfficiency;
    }

    public void setFuelEfficiency(double fuelEfficiency) {
        this.fuelEfficiency = fuelEfficiency;
    }

    public boolean isInMotion() {
        return inMotion;
    }

    // Method to calculate the estimated range
    public double calculateRange(double fuelLevel) {
        if ("Electric".equalsIgnoreCase(fuelType)) {
            // For electric vehicles, range is based on battery capacity
            return fuelLevel * 5; // Example: 1 unit of charge = 5 km
        } else {
            // For fuel-based vehicles, range = fuel level * efficiency
            return fuelLevel * fuelEfficiency;
        }
    }

    // Method to stop the vehicle
    public void stop() {
        this.speed = 0;
        this.inMotion = false;
    }

    // String representation of the vehicle
    @Override
    public String toString() {
        return "Vehicle{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", fuelType='" + fuelType + '\'' +
                ", speed=" + speed + " km/h" +
                ", fuelEfficiency=" + fuelEfficiency + " km/l" +
                ", inMotion=" + inMotion +
                '}';
    }
}
