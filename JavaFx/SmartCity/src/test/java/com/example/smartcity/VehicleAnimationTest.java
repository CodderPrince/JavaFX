package com.example.smartcity;


import com.example.smartcity.utils.AnimationUtils;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class VehicleAnimationTest extends Application {
    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();

        // Create a vehicle representation (Rectangle)
        Rectangle vehicle = new Rectangle(50, 20, Color.BLUE);
        vehicle.setLayoutX(50);
        vehicle.setLayoutY(100);

        pane.getChildren().add(vehicle);

        // Create an animation to move the vehicle across the pane
        AnimationUtils.createTranslateAnimation(vehicle, 0, 300, 0, 0, 2000); // 2-second animation

        Scene scene = new Scene(pane, 400, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Vehicle Animation Test");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
