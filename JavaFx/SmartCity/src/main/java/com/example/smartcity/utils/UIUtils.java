package com.example.smartcity.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Optional;

public class UIUtils {

    /**
     * Creates and displays an informational alert dialog.
     *
     * @param title   The title of the alert.
     * @param message The message to display in the alert.
     */
    public static void showInfoAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Creates and displays a confirmation dialog.
     *
     * @param title   The title of the dialog.
     * @param message The message to display in the dialog.
     * @return True if the user confirms, otherwise false.
     */
    public static boolean showConfirmationDialog(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }

    /**
     * Creates a styled circular icon for UI purposes.
     *
     * @param radius The radius of the circle.
     * @param color  The fill color of the circle.
     * @return A styled Circle node.
     */
    public static Circle createCircleIcon(double radius, Color color) {
        Circle circle = new Circle(radius);
        circle.setFill(color);
        circle.setStroke(Color.BLACK);
        circle.setStrokeWidth(1.5);
        return circle;
    }

    /**
     * Adds a label with styled text to a container.
     *
     * @param container The container (e.g., StackPane) to which the label will be added.
     * @param text      The text to display in the label.
     * @param style     The CSS style for the label.
     */
    public static void addStyledLabel(StackPane container, String text, String style) {
        Label label = new Label(text);
        label.setStyle(style);
        container.getChildren().add(label);
    }
}
