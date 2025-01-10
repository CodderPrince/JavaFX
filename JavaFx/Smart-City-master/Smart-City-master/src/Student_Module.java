import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Student_Module {

    @FXML
    private Button clubbtn;

    @FXML
    private Button libbtn;

    @FXML
    private Button uni;

    @FXML
    void view_clubs(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("clubs.fxml"));
        try {
            Parent root = loader.load();
            club_controller scene = loader.getController();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle(" Clubs ");
            stage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @FXML
    void view_libraries(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("libraries.fxml"));
        try {
            Parent root = loader.load();
            library_controller scene = loader.getController();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle(" Library ");
            stage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @FXML
    void view_universities(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("universities.fxml"));
        try {
            Parent root = loader.load();
            university_controller scene = loader.getController();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle(" University ");
            stage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}