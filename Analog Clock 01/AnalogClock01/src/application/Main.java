package application;

import java.time.LocalTime;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

	// Clock dimensions
	private static final double CLOCK_RADIUS = 200;
	private static final double CENTER_X = CLOCK_RADIUS;
	private static final double CENTER_Y = CLOCK_RADIUS;

	@Override
	public void start(Stage primaryStage) {
		// Create a Pane for drawing clock elements
		Pane pane = new Pane();

		// Create the clock face (circle)
		Circle clockFace = new Circle(CENTER_X, CENTER_Y, CLOCK_RADIUS, Color.LIGHTCYAN);
		clockFace.setStroke(Color.BLACK);
		clockFace.setStrokeWidth(2);

		// Add hour markers (numbers 1 to 12)
		for (int i = 1; i <= 12; i++) {
			double angle = Math.toRadians(i * 30);  // 360 / 12 = 30 degrees per hour
			double xPos = CENTER_X + (CLOCK_RADIUS - 30) * Math.cos(angle);
			double yPos = CENTER_Y + (CLOCK_RADIUS - 30) * Math.sin(angle);

			// Place the numbers (1 to 12)
			Text number = new Text(xPos - 10, yPos + 10, Integer.toString(i));
			number.setFill(Color.BLACK);
			number.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
			pane.getChildren().add(number);
		}

		// Create the hour, minute, and second hands
		Line hourHand = createHand(CLOCK_RADIUS * 0.5, 8, Color.RED);
		Line minuteHand = createHand(CLOCK_RADIUS * 0.7, 6, Color.GREEN);
		Line secondHand = createHand(CLOCK_RADIUS * 0.8, 2, Color.BLUE);

		// Center Circle for the clock
		Circle centerCircle = new Circle(CENTER_X, CENTER_Y, 8, Color.BLACK);

		// Place the hands at the center of the clock
		hourHand.setTranslateX(CENTER_X);
		hourHand.setTranslateY(CENTER_Y);
		minuteHand.setTranslateX(CENTER_X);
		minuteHand.setTranslateY(CENTER_Y);
		secondHand.setTranslateX(CENTER_X);
		secondHand.setTranslateY(CENTER_Y);

		// Add elements to the pane
		pane.getChildren().addAll(clockFace, hourHand, minuteHand, secondHand, centerCircle);

		// Create a Text for the time display
		Text timeText = new Text(CENTER_X - 35, CENTER_Y + CLOCK_RADIUS + 30, "");
		timeText.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

		// Update the time and move the hands every second
		Timeline timeline = new Timeline(
				new KeyFrame(Duration.seconds(0), e -> updateTime(hourHand, minuteHand, secondHand, timeText)),
				new KeyFrame(Duration.seconds(1), e -> updateTime(hourHand, minuteHand, secondHand, timeText)));
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();

		// Create the Scene
		Scene scene = new Scene(pane, 2 * CLOCK_RADIUS, 2 * CLOCK_RADIUS + 50);  // Add extra space for the text
		primaryStage.setTitle("Colorful Analog Clock");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private Line createHand(double length, double width, Color color) {
		Line hand = new Line(0, 0, length, 0);
		hand.setStroke(color);
		hand.setStrokeWidth(width);
		hand.setTranslateX(CENTER_X);
		hand.setTranslateY(CENTER_Y);
		return hand;
	}

	private void updateTime(Line hourHand, Line minuteHand, Line secondHand, Text timeText) {
		// Get the current time
		LocalTime now = LocalTime.now();

		// Calculate the angles for the hands
		double secondAngle = (now.getSecond() / 60.0) * 360;
		double minuteAngle = (now.getMinute() / 60.0) * 360 + (now.getSecond() / 60.0) * 6; // Minute hand also moves
																							 // slightly with seconds
		double hourAngle = (now.getHour() % 12 / 12.0) * 360 + (now.getMinute() / 60.0) * 30; // Hour hand moves
																								 // slightly with
																								 // minutes

		// Set the rotation of the hands
		secondHand.setRotate(secondAngle);
		minuteHand.setRotate(minuteAngle);
		hourHand.setRotate(hourAngle);

		// Update the text to show current time in HH:mm:ss format
		timeText.setText(String.format("%02d:%02d:%02d", now.getHour(), now.getMinute(), now.getSecond()));
	}

	public static void main(String[] args) {
		launch(args);
	}
}
