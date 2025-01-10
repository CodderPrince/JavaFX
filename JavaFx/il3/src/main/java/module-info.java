module com.example.il3 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.il3 to javafx.fxml;
    exports com.example.il3;
}