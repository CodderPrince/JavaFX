module com.example.il1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.il1 to javafx.fxml;
    exports com.example.il1;
}