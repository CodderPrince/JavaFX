package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class P14_4 extends Application {

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {

        Button button = new Button("Ok");
        
        //create a stackpane
        StackPane pane = new StackPane();
        pane.getChildren().add(button);
        
        //create circle
        Circle circle = new Circle();
        circle.setCenterX(100);
        circle.setCenterY(100);
        circle.setRadius(50);
        circle.setStroke(Color.BLUE);
        circle.setFill(Color.PINK);
        
        //create a pane to hold the cirlce
        Pane pane1 = new Pane();
        pane1.getChildren().addAll(circle,pane);
        
        //create scene
        Scene scene = new Scene(pane1,200,300);
        primaryStage.setScene(scene); 
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
