package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.Stop;
import javafx.scene.paint.Color;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;

public class PickFourCards extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create a BorderPane
        BorderPane pane = new BorderPane();

        // Create an HBox to hold the card images with spacing
        HBox cardBox = new HBox(15); // Add spacing between cards
        cardBox.setPadding(new Insets(10)); // Add padding around the HBox
        cardBox.setAlignment(Pos.CENTER); // Center the cards
        pane.setCenter(cardBox);

        // Create a Refresh button
        Button refreshButton = new Button("Refresh");

        // Center the button at the bottom
        VBox buttonBox = new VBox(refreshButton);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(10));
        pane.setBottom(buttonBox);

        // Set a gradient background
        LinearGradient gradient = new LinearGradient(
                0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.LIGHTBLUE),
                new Stop(1, Color.DARKBLUE)
        );
        pane.setBackground(new Background(new BackgroundFill(gradient, CornerRadii.EMPTY, Insets.EMPTY)));

        // Display four random cards initially
        displayRandomCards(cardBox);

        // Set the button action to refresh the cards
        refreshButton.setOnAction(e -> displayRandomCards(cardBox));

        // Create the scene and set the stage
        Scene scene = new Scene(pane, 500, 300); // Adjusted size for layout
        primaryStage.setTitle("Pick Four Cards");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Method to display four random cards
    private void displayRandomCards(HBox cardBox) {
        cardBox.getChildren().clear(); // Clear previous cards

        // Get a shuffled deck of card indices (1-52)
        ArrayList<Integer> deck = new ArrayList<>();
        for (int i = 1; i <= 52; i++) {
            deck.add(i);
        }
        Collections.shuffle(deck);

        // Load the first four cards as images
        for (int i = 0; i < 4; i++) {
            int cardNumber = deck.get(i);
            String imagePath = "/cards/" + cardNumber + ".png";

            try {
                // Load the card image using getResource
                Image cardImage = new Image(getClass().getResource(imagePath).toExternalForm());
                ImageView cardView = new ImageView(cardImage);

                // Customize card size
                cardView.setFitHeight(150); // Set card height
                cardView.setFitWidth(100);  // Set card width
                cardView.setPreserveRatio(true); // Preserve aspect ratio

                // Add the customized card to the HBox
                cardBox.getChildren().add(cardView);
            } catch (Exception e) {
                System.out.println("Error loading image: " + imagePath);
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
