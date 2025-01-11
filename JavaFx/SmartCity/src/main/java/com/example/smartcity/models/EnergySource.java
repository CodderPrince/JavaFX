package com.example.smartcity.models;

public class EnergySource {

    // Properties of the energy source
    private String name;           // Name of the energy source (e.g., Solar, Coal, Wind)
    private String type;           // Type of energy (e.g., Renewable, Non-Renewable)
    private double capacity;       // Maximum capacity in megawatts (MW)
    private double currentOutput;  // Current energy output in megawatts (MW)
    private double efficiency;     // Efficiency as a percentage (0 to 100)

    // Constructor
    public EnergySource(String name, String type, double capacity, double efficiency) {
        this.name = name;
        this.type = type;
        this.capacity = capacity;
        this.efficiency = efficiency;
        this.currentOutput = 0; // Default to 0 at initialization
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

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public double getCurrentOutput() {
        return currentOutput;
    }

    public void setCurrentOutput(double currentOutput) {
        if (currentOutput <= capacity) {
            this.currentOutput = currentOutput;
        } else {
            throw new IllegalArgumentException("Current output cannot exceed capacity.");
        }
    }

    public double getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(double efficiency) {
        if (efficiency >= 0 && efficiency <= 100) {
            this.efficiency = efficiency;
        } else {
            throw new IllegalArgumentException("Efficiency must be between 0 and 100.");
        }
    }

    // Method to calculate actual output considering efficiency
    public double calculateActualOutput() {
        return (currentOutput * efficiency) / 100;
    }

    // String representation of the energy source
    @Override
    public String toString() {
        return "EnergySource{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", capacity=" + capacity + " MW" +
                ", currentOutput=" + currentOutput + " MW" +
                ", efficiency=" + efficiency + "%" +
                ", actualOutput=" + calculateActualOutput() + " MW" +
                '}';
    }
}
