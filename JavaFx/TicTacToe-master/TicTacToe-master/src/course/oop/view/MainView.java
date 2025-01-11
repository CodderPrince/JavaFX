package course.oop.view;

import course.oop.controller.Controller;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;

public class MainView implements TTTView {
    //private final Scene scene;
    private final Parent root;

    public MainView() {

        // Root node
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(20);

        // Title page
        Label title = new Label("Tic-tac-toe: The Game!");
        title.setTextFill(Color.web("#0076a3"));
        title.setFont(Font.font(50));

        // Main Menu
        Button start = new Button("Start");
        start.setTextFill(Color.web("#0076a3"));
        Button quit = new Button("Quit");
        quit.setTextFill(Color.web("red"));
        Button store = new Button("Shop");
        store.setTextFill(Color.web("#0076a3"));

        // Actions
        start.setOnAction(e -> onStart());
        quit.setOnAction(e -> Controller.execute("quit"));
        store.setOnAction(e -> Controller.execute("store"));

        root.getChildren().addAll(title, start, store, quit);
        //this.scene = new Scene(root, 800, 600);
        this.root = root;
    }

    @Override
    public Parent getRoot() {
        return root;
    }

    private void onStart() {
        Controller.execute("setup");
    }
}
