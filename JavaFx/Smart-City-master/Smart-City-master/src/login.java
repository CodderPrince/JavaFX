import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class login implements Initializable{

    @FXML
    private ComboBox<String> loginasbtn;

    @FXML
    private Button loginbtn;

    @FXML
    private TextField passbtn;

    @FXML
    private TextField userbtn;

    @FXML
    // When login button is clicked
    void call_login(ActionEvent event) {
        String s= loginasbtn.getSelectionModel().getSelectedItem().toString();
        Connection con = dbconnection.getConnection();
        if(con == null) {
            return;
        }
        if (s.equals("Admin")){
            String user= userbtn.getText();
            String pass= passbtn.getText();
            String query = "SELECT * FROM users WHERE type='admin' and username=? and password=?;";
            try {
                PreparedStatement preparedStatement = con.prepareStatement(query);
                preparedStatement.setString(1, user);
                preparedStatement.setString(2, pass);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()){
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Admin.fxml"));
                try {
                    Parent root = loader.load();
                    Admin_Module scene = loader.getController();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.setTitle(" Admin View ");
                    stage.show();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                }
                else{
                    Alert errorAlert = new Alert(AlertType.ERROR);
                    errorAlert.setHeaderText("Input not valid");
                    errorAlert.showAndWait(); 
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
        }
        else  if (s.equals("Student")){
            String user= userbtn.getText();
            String pass= passbtn.getText();
            //System.out.print(user + " "+pass);
            String query = "SELECT * FROM users WHERE type='student' and username=? and password=?;";
            try {
                PreparedStatement preparedStatement = con.prepareStatement(query);
                preparedStatement.setString(1, user);
                preparedStatement.setString(2, pass);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()){
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("home_student.fxml"));
                try {
                    Parent root = loader.load();
                    Student_Module scene = loader.getController();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.setTitle(" Student View ");
                    stage.show();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    //e.printStackTrace();
                }
                }
                else{
                    Alert errorAlert = new Alert(AlertType.ERROR);
                    errorAlert.setHeaderText("Input not valid");
                    errorAlert.showAndWait(); 
                }
            } catch (SQLException e) {
                //TODO Auto-generated catch block
                //e.printStackTrace();
            } 
        }
        else  if (s.equals("Tourist")){
            String user= userbtn.getText();
            String pass= passbtn.getText();
            String query = "SELECT * FROM users WHERE type='tourist' and username=? and password=?;";
            try {
                PreparedStatement preparedStatement = con.prepareStatement(query);
                preparedStatement.setString(1, user);
                preparedStatement.setString(2, pass);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()){
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("tourist_mode_homepage.fxml"));
                try {
                    Parent root = loader.load();
                    Tourism_Module scene = loader.getController();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.setTitle(" Tourist View ");
                    stage.show();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                }
                else{
                    Alert errorAlert = new Alert(AlertType.ERROR);
                    errorAlert.setHeaderText("Input not valid");
                    errorAlert.showAndWait(); 
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } 
        }

        else{
            String user= userbtn.getText();
            String pass= passbtn.getText();
            String query = "SELECT * FROM users WHERE type='business' and username=? and password=?;";
            try {
                PreparedStatement preparedStatement = con.prepareStatement(query);
                preparedStatement.setString(1, user);
                preparedStatement.setString(2, pass);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()){
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Busines.fxml"));
                try {
                    Parent root = loader.load();
                    Business_Module scene = loader.getController();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.setTitle(" Business View ");
                    stage.show();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    //e.printStackTrace();
                }
                }
                else{
                    Alert errorAlert = new Alert(AlertType.ERROR);
                    errorAlert.setHeaderText("Input not valid");
                    errorAlert.showAndWait(); 
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                //e.printStackTrace();
            } 
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){
        ObservableList<String> list = FXCollections.observableArrayList("Admin","Student","Tourist","Business");
        loginasbtn.setItems(list);
    }


}
