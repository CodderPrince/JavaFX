module tictactoe {
    requires javafx.controls;
    requires javafx.fxml;

    opens app to javafx.fxml;
    exports app;

    opens controllers to javafx.fxml;
    exports controllers;

    opens models to javafx.fxml;
    exports models;
}
