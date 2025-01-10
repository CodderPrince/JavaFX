import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Tourism_Module {

    @FXML
    private Button attractionbtn;

    @FXML
    private Button hotelsbtn;

    @FXML
    private Button restaurantbtn;

    @FXML
    void view_attractions(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Tourist_attractions.fxml"));
        try {
            Parent root = loader.load();
            attractions_controller scene = loader.getController();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle(" Tourist attractions ");
            stage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @FXML
    void view_hotels(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hotels.fxml"));
        try {
            Parent root = loader.load();
            hotels_controller scene = loader.getController();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle(" Hotels ");
            stage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @FXML
    void view_restaurants(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Restaurants.fxml"));
        try {
            Parent root = loader.load();
            restaurant_controller scene = loader.getController();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle(" Restaurant ");
            stage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
