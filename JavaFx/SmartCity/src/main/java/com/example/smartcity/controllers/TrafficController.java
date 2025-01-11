package com.example.smartcity.controllers;

import com.example.smartcity.utils.AnimationUtils;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class TrafficController {

    @FXML
    private Pane trafficPane; // A Pane defined in the FXML for animations.

    /**
     * Animates a single vehicle (represented as a rectangle) across the trafficPane.
     */
    public void animateVehicle() {
        // Create a vehicle representation (Rectangle)
        Rectangle vehicle = new Rectangle(50, 20, Color.BLUE);
        vehicle.setLayoutX(50);
        vehicle.setLayoutY(100);

        // Add the vehicle to the trafficPane
        trafficPane.getChildren().add(vehicle);

        // Animate the vehicle across the pane
        AnimationUtils.createTranslateAnimation(vehicle, 0, 300, 0, 0, 3000); // 3-second animation
    }

    /**
     * Animates multiple vehicles across the trafficPane.
     */
    public void animateVehicles() {
        for (int i = 0; i < 5; i++) {
            Rectangle vehicle = new Rectangle(50, 20, Color.BLUE);
            vehicle.setLayoutX(50);
            vehicle.setLayoutY(50 + (i * 50)); // Stagger vehicles vertically
            trafficPane.getChildren().add(vehicle);

            // Add animation for each vehicle
            AnimationUtils.createTranslateAnimation(vehicle, 0, 300, 0, 0, 3000 + (i * 500)); // Stagger animation timing
        }
    }
}
