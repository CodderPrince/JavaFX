package application;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

public class DragPoints extends Application {

    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();

        // Create the circle
        Circle circle = new Circle(200, 200, 150); // Center (200, 200) with radius 150
        circle.setFill(Color.TRANSPARENT);
        circle.setStroke(Color.BLACK);

        // Generate three random points on the circle
        ArrayList<Circle> points = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            double angle = Math.random() * 360;
            Point2D randomPoint = getPointOnCircle(circle, angle);
            Circle point = new Circle(randomPoint.getX(), randomPoint.getY(), 10, Color.RED);
            points.add(point);
        }

        // Create lines for the triangle
        Line line1 = new Line();
        Line line2 = new Line();
        Line line3 = new Line();

        // Create texts to display angles
        Text angle1 = new Text();
        Text angle2 = new Text();
        Text angle3 = new Text();

        // Add all elements to the pane
        pane.getChildren().addAll(circle, points.get(0), points.get(1), points.get(2), line1, line2, line3, angle1, angle2, angle3);

        // Set up dynamic interaction
        for (int i = 0; i < points.size(); i++) {
            Circle point = points.get(i);
            point.setOnMouseDragged(event -> {
                Point2D mousePoint = new Point2D(event.getX(), event.getY());
                Point2D newPoint = getClosestPointOnCircle(circle, mousePoint);
                point.setCenterX(newPoint.getX());
                point.setCenterY(newPoint.getY());

                updateTriangle(points, line1, line2, line3, angle1, angle2, angle3);
            });
        }

        // Initial update for the triangle and angles
        updateTriangle(points, line1, line2, line3, angle1, angle2, angle3);

        // Create the scene and display the stage
        Scene scene = new Scene(pane, 400, 400, Color.LIGHTGRAY);
        primaryStage.setTitle("Drag Points on Circle");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Method to get a point on the circle given an angle in degrees
    private Point2D getPointOnCircle(Circle circle, double angle) {
        double x = circle.getCenterX() + circle.getRadius() * Math.cos(Math.toRadians(angle));
        double y = circle.getCenterY() + circle.getRadius() * Math.sin(Math.toRadians(angle));
        return new Point2D(x, y);
    }

    // Method to get the closest point on the circle from a given point
    private Point2D getClosestPointOnCircle(Circle circle, Point2D point) {
        double angle = Math.atan2(point.getY() - circle.getCenterY(), point.getX() - circle.getCenterX());
        double x = circle.getCenterX() + circle.getRadius() * Math.cos(angle);
        double y = circle.getCenterY() + circle.getRadius() * Math.sin(angle);
        return new Point2D(x, y);
    }

    // Method to update the triangle and angles
    private void updateTriangle(ArrayList<Circle> points, Line line1, Line line2, Line line3, Text angle1, Text angle2, Text angle3) {
        // Update the triangle lines
        line1.setStartX(points.get(0).getCenterX());
        line1.setStartY(points.get(0).getCenterY());
        line1.setEndX(points.get(1).getCenterX());
        line1.setEndY(points.get(1).getCenterY());

        line2.setStartX(points.get(1).getCenterX());
        line2.setStartY(points.get(1).getCenterY());
        line2.setEndX(points.get(2).getCenterX());
        line2.setEndY(points.get(2).getCenterY());

        line3.setStartX(points.get(2).getCenterX());
        line3.setStartY(points.get(2).getCenterY());
        line3.setEndX(points.get(0).getCenterX());
        line3.setEndY(points.get(0).getCenterY());

        // Calculate distances between points
        double a = getDistance(points.get(1), points.get(2));
        double b = getDistance(points.get(0), points.get(2));
        double c = getDistance(points.get(0), points.get(1));

        // Calculate angles in degrees
        double angleA = Math.toDegrees(Math.acos((b * b + c * c - a * a) / (2 * b * c)));
        double angleB = Math.toDegrees(Math.acos((a * a + c * c - b * b) / (2 * a * c)));
        double angleC = 180 - angleA - angleB;

        // Update the angles' positions and texts
        angle1.setText(String.format("%.2f", angleA));
        angle1.setX(points.get(0).getCenterX() + 10);
        angle1.setY(points.get(0).getCenterY() - 10);

        angle2.setText(String.format("%.2f", angleB));
        angle2.setX(points.get(1).getCenterX() + 10);
        angle2.setY(points.get(1).getCenterY() - 10);

        angle3.setText(String.format("%.2f", angleC));
        angle3.setX(points.get(2).getCenterX() + 10);
        angle3.setY(points.get(2).getCenterY() - 10);
    }

    // Method to calculate the distance between two points
    private double getDistance(Circle p1, Circle p2) {
        return Math.sqrt(Math.pow(p1.getCenterX() - p2.getCenterX(), 2) + Math.pow(p1.getCenterY() - p2.getCenterY(), 2));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
