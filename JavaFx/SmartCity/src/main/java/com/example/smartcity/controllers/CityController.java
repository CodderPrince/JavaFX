package com.example.smartcity.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class CityController {

    @FXML
    private Pane cityPane; // The pane where the city simulation will be displayed.

    @FXML
    private Button startSimulationButton; // Button to start the simulation.

    @FXML
    private Button stopSimulationButton; // Button to stop the simulation.

    private boolean simulationRunning = false;

    /**
     * Initializes the CityController. This method is called automatically
     * after the FXML file is loaded.
     */
    @FXML
    public void initialize() {
        // Set up the initial state of the simulation.
        stopSimulationButton.setDisable(true); // Disable stop button initially.
    }

    /**
     * Starts the city simulation.
     */
    @FXML
    private void startSimulation() {
        simulationRunning = true;
        startSimulationButton.setDisable(true);
        stopSimulationButton.setDisable(false);
        System.out.println("Simulation started!");

        // Add simulation logic here, such as animations or events.
    }

    /**
     * Stops the city simulation.
     */
    @FXML
    private void stopSimulation() {
        simulationRunning = false;
        startSimulationButton.setDisable(false);
        stopSimulationButton.setDisable(true);
        System.out.println("Simulation stopped!");

        // Add logic to pause or stop animations/events.
    }

    /**
     * Resets the simulation to its initial state.
     */
    @FXML
    private void resetSimulation() {
        simulationRunning = false;
        startSimulationButton.setDisable(false);
        stopSimulationButton.setDisable(true);
        System.out.println("Simulation reset!");

        // Add logic to reset the city simulation (e.g., clear the pane, reset variables).
    }
}
