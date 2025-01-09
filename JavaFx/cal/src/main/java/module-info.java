module com.example.cal {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.cal to javafx.fxml;
    exports com.example.cal;
}