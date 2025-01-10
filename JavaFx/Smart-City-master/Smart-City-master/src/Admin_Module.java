import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.mysql.cj.xdevapi.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class Admin_Module implements Initializable {

    @FXML
    private ComboBox<String> addbtn;

    @FXML
    private TextField passbtn;

    @FXML
    private Button updatebtn;

    @FXML
    private TextField userbtn;

    @FXML
    void adduser(ActionEvent event) {
        String s= addbtn.getSelectionModel().getSelectedItem().toString();
        Connection con = dbconnection.getConnection();
        if(con == null) {
            return;
        }
        if (s.equals("Admin")){
            String user= userbtn.getText();
            String pass= passbtn.getText();
            String query = "INSERT INTO users (username, password, type) VALUES (?,?, 'admin');";
            try {
                PreparedStatement preparedStatement = con.prepareStatement(query);
                preparedStatement.setString(1, user);
                preparedStatement.setString(2, pass);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            //statement.executeUpdate("INSERT INTO Customers " + "VALUES (1001, 'Simpson', 'Mr.', 'Springfield', 2001)");
        }
        else if (s.equals("Student")){
            String user= userbtn.getText();
            String pass= passbtn.getText();
            String query = "INSERT INTO users (username, password, type) VALUES (?, ? , 'student');";
            try {
                PreparedStatement preparedStatement = con.prepareStatement(query);
                preparedStatement.setString(1, user);
                preparedStatement.setString(2, pass);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        else if (s.equals("Tourist")){
            String user= userbtn.getText();
            String pass= passbtn.getText();
            String query = "INSERT INTO users (username, password, type) VALUES (?,? , 'tourist');";
            try {
                PreparedStatement preparedStatement = con.prepareStatement(query);
                preparedStatement.setString(1, user);
                preparedStatement.setString(2, pass);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        else {
            String user= userbtn.getText();
            String pass= passbtn.getText();
            String query = "INSERT INTO users (username, password, type) VALUES (?,?,'business');";
            try {
                PreparedStatement preparedStatement = con.prepareStatement(query);
                preparedStatement.setString(1, user);
                preparedStatement.setString(2, pass);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb){
        ObservableList<String> list = FXCollections.observableArrayList("Admin","Student","Tourist","Business");
        addbtn.setItems(list);
    }

}
