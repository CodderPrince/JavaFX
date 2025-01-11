package com.example.smartcity.services;

import com.example.smartcity.models.Building;
import com.example.smartcity.models.EnergySource;
import com.example.smartcity.models.Vehicle;
import com.example.smartcity.models.EmergencyEvent;

import java.util.ArrayList;
import java.util.List;

public class DataService {

    // Lists to store various data entities
    private List<Building> buildings;
    private List<EnergySource> energySources;
    private List<Vehicle> vehicles;
    private List<EmergencyEvent> emergencyEvents;

    // Constructor to initialize data collections
    public DataService() {
        this.buildings = new ArrayList<>();
        this.energySources = new ArrayList<>();
        this.vehicles = new ArrayList<>();
        this.emergencyEvents = new ArrayList<>();
    }

    // Building Management
    public void addBuilding(Building building) {
        buildings.add(building);
    }

    public List<Building> getBuildings() {
        return new ArrayList<>(buildings); // Return a copy to prevent modification
    }

    public void removeBuilding(Building building) {
        buildings.remove(building);
    }

    // Energy Source Management
    public void addEnergySource(EnergySource energySource) {
        energySources.add(energySource);
    }

    public List<EnergySource> getEnergySources() {
        return new ArrayList<>(energySources);
    }

    public void removeEnergySource(EnergySource energySource) {
        energySources.remove(energySource);
    }

    // Vehicle Management
    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    public List<Vehicle> getVehicles() {
        return new ArrayList<>(vehicles);
    }

    public void removeVehicle(Vehicle vehicle) {
        vehicles.remove(vehicle);
    }

    // Emergency Event Management
    public void addEmergencyEvent(EmergencyEvent event) {
        emergencyEvents.add(event);
    }

    public List<EmergencyEvent> getEmergencyEvents() {
        return new ArrayList<>(emergencyEvents);
    }

    public void removeEmergencyEvent(EmergencyEvent event) {
        emergencyEvents.remove(event);
    }

    // Utility Methods
    public void clearAllData() {
        buildings.clear();
        energySources.clear();
        vehicles.clear();
        emergencyEvents.clear();
    }

    public int getTotalBuildings() {
        return buildings.size();
    }

    public int getTotalEnergySources() {
        return energySources.size();
    }

    public int getTotalVehicles() {
        return vehicles.size();
    }

    public int getTotalEmergencyEvents() {
        return emergencyEvents.size();
    }
}
