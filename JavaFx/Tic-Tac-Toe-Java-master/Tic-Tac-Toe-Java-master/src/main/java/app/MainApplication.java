package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("/view/Game.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 800);
        scene.getStylesheets().add(Objects.requireNonNull(MainApplication.class.getResource("/css/Style.css")).toExternalForm());
        stage.getIcons().add(new Image(Objects.requireNonNull(MainApplication.class.getResource("/images/icon.png")).openStream()));
        stage.setTitle("Tic-Tac-Toe");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {launch();}
}