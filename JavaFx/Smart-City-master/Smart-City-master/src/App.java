import java.io.IOException;
import java.sql.Connection;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class App extends Application{

    @Override
    public void start(Stage primaryStage) {
        Parent root;
        try {
            // Loading the main login page
            root = FXMLLoader.load(getClass().getResource("login.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("Smart City");        
            primaryStage.setScene(scene); 
            primaryStage.show(); 
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}
