module com.example.smartcity {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.smartcity.app to javafx.fxml;
    opens com.example.smartcity.controllers to javafx.fxml;
    opens com.example.smartcity.models to javafx.base;
    opens com.example.smartcity.utils to javafx.base;

    exports com.example.smartcity.app;
    exports com.example.smartcity.controllers;
    exports com.example.smartcity.models;
    exports com.example.smartcity.utils;
}
