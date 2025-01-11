package com.example.smartcity.services;

import com.example.smartcity.models.Building;
import com.example.smartcity.models.EnergySource;
import com.example.smartcity.models.Vehicle;
import com.example.smartcity.models.EmergencyEvent;

import java.util.ArrayList;
import java.util.List;

public class SimulationService {

    private List<Building> buildings;
    private List<EnergySource> energySources;
    private List<Vehicle> vehicles;
    private List<EmergencyEvent> emergencyEvents;
    private boolean isRunning;

    // Constructor
    public SimulationService() {
        this.buildings = new ArrayList<>();
        this.energySources = new ArrayList<>();
        this.vehicles = new ArrayList<>();
        this.emergencyEvents = new ArrayList<>();
        this.isRunning = false;
    }

    // Start the simulation
    public void startSimulation() {
        if (!isRunning) {
            isRunning = true;
            System.out.println("Simulation started.");
            // Add logic to initialize and start the simulation processes
        } else {
            System.out.println("Simulation is already running.");
        }
    }

    // Stop the simulation
    public void stopSimulation() {
        if (isRunning) {
            isRunning = false;
            System.out.println("Simulation stopped.");
            // Add logic to pause or reset the simulation processes
        } else {
            System.out.println("Simulation is not currently running.");
        }
    }

    // Add a building to the simulation
    public void addBuilding(Building building) {
        buildings.add(building);
        System.out.println("Building added: " + building);
    }

    // Add an energy source to the simulation
    public void addEnergySource(EnergySource energySource) {
        energySources.add(energySource);
        System.out.println("Energy source added: " + energySource);
    }

    // Add a vehicle to the simulation
    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
        System.out.println("Vehicle added: " + vehicle);
    }

    // Add an emergency event to the simulation
    public void addEmergencyEvent(EmergencyEvent event) {
        emergencyEvents.add(event);
        System.out.println("Emergency event added: " + event);
    }

    // Update the simulation state (called periodically)
    public void updateSimulation() {
        if (!isRunning) {
            System.out.println("Simulation is not running. Update skipped.");
            return;
        }

        // Update logic for buildings, vehicles, and energy sources
        System.out.println("Updating simulation state...");
        vehicles.forEach(vehicle -> System.out.println("Vehicle in motion: " + vehicle));
        energySources.forEach(source -> System.out.println("Energy output: " + source.calculateActualOutput() + " MW"));

        // Handle unresolved emergency events
        emergencyEvents.stream()
                .filter(event -> !event.isResolved())
                .forEach(event -> System.out.println("Active emergency: " + event));
    }

    // Get the status of the simulation
    public boolean isRunning() {
        return isRunning;
    }

    // Print simulation details
    public void printSimulationDetails() {
        System.out.println("Simulation Details:");
        System.out.println("Buildings: " + buildings.size());
        System.out.println("Energy Sources: " + energySources.size());
        System.out.println("Vehicles: " + vehicles.size());
        System.out.println("Emergency Events: " + emergencyEvents.size());
    }
}
