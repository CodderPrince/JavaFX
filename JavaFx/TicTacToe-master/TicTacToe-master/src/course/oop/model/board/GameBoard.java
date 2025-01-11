package course.oop.model.board;

import course.oop.model.Marker;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public interface GameBoard {
    StackPane asJavaFXNode();

    Tile selectTile(int[] coords, Marker m);
    String selectRandomTile();
    int determineWinner();
    Tile getTile(int[] coords);
    void spin();
    void rebound();
    void clear();
    void clearEffects();
    void rotate(String direction);
}
