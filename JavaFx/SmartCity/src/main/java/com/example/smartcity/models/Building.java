package com.example.smartcity.models;

public class Building {

    // Properties of the building
    private String name;          // Name of the building
    private String type;          // Type of the building (e.g., Residential, Commercial, Industrial)
    private int floors;           // Number of floors
    private double energyUsage;   // Energy consumption in kWh
    private double latitude;      // Latitude of the building's location
    private double longitude;     // Longitude of the building's location

    // Constructor
    public Building(String name, String type, int floors, double energyUsage, double latitude, double longitude) {
        this.name = name;
        this.type = type;
        this.floors = floors;
        this.energyUsage = energyUsage;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getFloors() {
        return floors;
    }

    public void setFloors(int floors) {
        this.floors = floors;
    }

    public double getEnergyUsage() {
        return energyUsage;
    }

    public void setEnergyUsage(double energyUsage) {
        this.energyUsage = energyUsage;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    // Method to calculate the carbon footprint of the building
    public double calculateCarbonFootprint() {
        // Example calculation: energy usage * emission factor (e.g., 0.5 kg CO2/kWh)
        double emissionFactor = 0.5; // kg CO2 per kWh
        return energyUsage * emissionFactor;
    }

    // Method to provide a string representation of the building
    @Override
    public String toString() {
        return "Building{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", floors=" + floors +
                ", energyUsage=" + energyUsage + " kWh" +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
