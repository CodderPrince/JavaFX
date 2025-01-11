package com.example.smartcity.controllers;

import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

public class DashboardController {

    @FXML
    private Label energyUsageLabel; // Displays total energy usage.

    @FXML
    private Label trafficStatusLabel; // Displays traffic status.

    @FXML
    private PieChart energyUsageChart; // Displays energy usage distribution.

    @FXML
    private BarChart<String, Number> trafficFlowChart; // Displays traffic flow over time.

    /**
     * Initializes the dashboard view.
     * This method is automatically called after the FXML file is loaded.
     */
    @FXML
    public void initialize() {
        // Initialize the energy usage chart.
        initializeEnergyUsageChart();

        // Initialize the traffic flow chart.
        initializeTrafficFlowChart();

        // Set sample values for the labels.
        energyUsageLabel.setText("Total Energy Usage: 1200 kWh");
        trafficStatusLabel.setText("Traffic Status: Moderate");
    }

    /**
     * Initializes the energy usage pie chart with sample data.
     */
    private void initializeEnergyUsageChart() {
        PieChart.Data renewableData = new PieChart.Data("Renewable", 60);
        PieChart.Data nonRenewableData = new PieChart.Data("Non-Renewable", 40);

        energyUsageChart.getData().addAll(renewableData, nonRenewableData);
    }

    /**
     * Initializes the traffic flow bar chart with sample data.
     */
    private void initializeTrafficFlowChart() {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Traffic Flow");

        series.getData().add(new XYChart.Data<>("8 AM", 120));
        series.getData().add(new XYChart.Data<>("9 AM", 150));
        series.getData().add(new XYChart.Data<>("10 AM", 90));
        series.getData().add(new XYChart.Data<>("11 AM", 110));

        trafficFlowChart.getData().add(series);
    }
}
