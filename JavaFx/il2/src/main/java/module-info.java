module com.example.il2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.il2 to javafx.fxml;
    exports com.example.il2;
}