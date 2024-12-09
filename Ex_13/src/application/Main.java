package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Input fields for triangle
        TextField side1Field = new TextField();
        TextField side2Field = new TextField();
        TextField side3Field = new TextField();

        // Input fields for circle
        TextField radiusField = new TextField();

        // Shared fields
        ColorPicker colorPicker = new ColorPicker();
        CheckBox filledCheckBox = new CheckBox("Filled");

        // Radio buttons to select shape
        RadioButton triangleRadioButton = new RadioButton("Triangle");
        RadioButton circleRadioButton = new RadioButton("Circle");
        ToggleGroup shapeToggleGroup = new ToggleGroup();
        triangleRadioButton.setToggleGroup(shapeToggleGroup);
        circleRadioButton.setToggleGroup(shapeToggleGroup);
        triangleRadioButton.setSelected(true);

        // Input area for triangle
        VBox triangleInput = new VBox(10, 
                new Label("Triangle - Side 1:"), side1Field,
                new Label("Triangle - Side 2:"), side2Field,
                new Label("Triangle - Side 3:"), side3Field
        );

        // Input area for circle
        VBox circleInput = new VBox(10, 
                new Label("Circle - Radius:"), radiusField
        );
        circleInput.setVisible(false); // Initially hidden

        // Shared input fields
        VBox sharedInput = new VBox(10, 
                new Label("Color:"), colorPicker,
                filledCheckBox
        );

        // Switch input areas based on selected shape
        shapeToggleGroup.selectedToggleProperty().addListener((obs, oldToggle, newToggle) -> {
            if (newToggle == triangleRadioButton) {
                triangleInput.setVisible(true);
                circleInput.setVisible(false);
            } else if (newToggle == circleRadioButton) {
                triangleInput.setVisible(false);
                circleInput.setVisible(true);
            }
        });

        // Button to calculate
        Button calculateButton = new Button("Calculate");

        // Output area
        TextArea outputArea = new TextArea();
        outputArea.setEditable(false);

        // Main layout
        VBox layout = new VBox(10, 
                new HBox(10, triangleRadioButton, circleRadioButton), 
                triangleInput, 
                circleInput, 
                sharedInput, 
                calculateButton, 
                outputArea
        );
        layout.setPadding(new Insets(20));

        // Button action
        calculateButton.setOnAction(e -> {
            try {
                if (triangleRadioButton.isSelected()) {
                    // Parse triangle inputs
                    double side1 = Double.parseDouble(side1Field.getText());
                    double side2 = Double.parseDouble(side2Field.getText());
                    double side3 = Double.parseDouble(side3Field.getText());
                    String color = colorPicker.getValue().toString(); // Get selected color
                    boolean filled = filledCheckBox.isSelected();

                    // Validate the triangle
                    if (!isValidTriangle(side1, side2, side3)) {
                        outputArea.setText("Invalid triangle. The sides do not satisfy the triangle inequality theorem.");
                        return;
                    }

                    // Create Triangle object
                    Triangle triangle = new Triangle(side1, side2, side3, color, filled);

                    // Display triangle properties
                    outputArea.setText("Triangle Details:\n");
                    outputArea.appendText("Area: " + triangle.getArea() + "\n");
                    outputArea.appendText("Perimeter: " + triangle.getPerimeter() + "\n");
                    outputArea.appendText("Color: " + triangle.getColor() + "\n");
                    outputArea.appendText("Filled: " + triangle.isFilled());
                } else if (circleRadioButton.isSelected()) {
                    // Parse circle inputs
                    double radius = Double.parseDouble(radiusField.getText());
                    String color = colorPicker.getValue().toString(); // Get selected color
                    boolean filled = filledCheckBox.isSelected();

                    // Create Circle object
                    Circle circle = new Circle(radius, color, filled);

                    // Display circle properties
                    outputArea.setText("Circle Details:\n");
                    outputArea.appendText("Area: " + circle.getArea() + "\n");
                    outputArea.appendText("Perimeter (Circumference): " + circle.getPerimeter() + "\n");
                    outputArea.appendText("Color: " + circle.getColor() + "\n");
                    outputArea.appendText("Filled: " + circle.isFilled());
                }
            } catch (NumberFormatException ex) {
                outputArea.setText("Invalid input. Please enter numeric values for the sides or radius.");
            }
        });

        // Set scene and show stage
        Scene scene = new Scene(layout, 400, 600);
        primaryStage.setTitle("Shape App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Validate if the triangle is valid
    private boolean isValidTriangle(double side1, double side2, double side3) {
        return (side1 + side2 > side3) && (side1 + side3 > side2) && (side2 + side3 > side1);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
