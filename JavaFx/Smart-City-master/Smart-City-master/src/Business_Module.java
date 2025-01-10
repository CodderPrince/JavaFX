import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Business_Module {

    @FXML
    private Button compbtn;

    @FXML
    private Button historybtn;

    @FXML
    void view_companies(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("company.fxml"));
        try {
            Parent root = loader.load();
            company_controller scene = loader.getController();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle(" Companies ");
            stage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @FXML
    void view_history(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("history.fxml"));
        try {
            Parent root = loader.load();
            history_controller scene = loader.getController();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle(" City history ");
            stage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}

