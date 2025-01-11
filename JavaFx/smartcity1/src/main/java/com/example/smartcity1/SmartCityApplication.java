package com.example.smartcity1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SmartCityApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Root Layout
        BorderPane root = new BorderPane();

        // Header
        Label header = new Label("Smart City Dashboard");
        header.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-padding: 10px;");
        root.setTop(header);

        // Map View (Left Pane)
        VBox mapView = new VBox();
        mapView.setPadding(new Insets(10));
        mapView.setSpacing(10);

        Label mapLabel = new Label("City Map:");
        Button residentialButton = new Button("Residential Zone");
        Button commercialButton = new Button("Commercial Zone");
        Button industrialButton = new Button("Industrial Zone");

        mapView.getChildren().addAll(mapLabel, residentialButton, commercialButton, industrialButton);
        root.setLeft(mapView);

        // Dashboard (Center Pane)
        GridPane dashboard = new GridPane();
        dashboard.setPadding(new Insets(10));
        dashboard.setHgap(10);
        dashboard.setVgap(10);

        Label trafficLabel = new Label("Traffic Status:");
        ListView<String> trafficList = new ListView<>();
        trafficList.getItems().addAll("Main Street: Heavy Traffic", "Second Avenue: Moderate Traffic", "Third Lane: Light Traffic");

        Label energyLabel = new Label("Energy Usage:");
        PieChart energyChart = new PieChart();
        energyChart.getData().addAll(
                new PieChart.Data("Residential", 40),
                new PieChart.Data("Commercial", 35),
                new PieChart.Data("Industrial", 25)
        );

        dashboard.add(trafficLabel, 0, 0);
        dashboard.add(trafficList, 0, 1);
        dashboard.add(energyLabel, 1, 0);
        dashboard.add(energyChart, 1, 1);

        root.setCenter(dashboard);

        // Footer
        Label footer = new Label("\u00A9 2024 Smart City Project | Designed by You");
        footer.setStyle("-fx-font-size: 12px; -fx-padding: 10px; -fx-alignment: center;");
        root.setBottom(footer);

        // Scene Setup
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Smart City Application");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
