module TowerOfHanoi {
	requires javafx.controls;
	requires java.desktop;

	opens application to javafx.graphics, javafx.fxml;
}
