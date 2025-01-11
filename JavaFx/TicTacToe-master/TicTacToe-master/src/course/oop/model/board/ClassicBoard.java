package course.oop.model.board;

import course.oop.controller.Controller;
import course.oop.model.Marker;
import course.oop.util.Utilities;
import javafx.animation.Animation;
import javafx.animation.RotateTransition;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.util.*;

import static java.lang.System.exit;

public class ClassicBoard implements GameBoard {

    ClassicBoard[] neighbors = new ClassicBoard[Tile.numDirections];

    Marker m;

    private static double x = 400;
    private static double y = 300;
    Tile[][] tiles;
    int n;
    int rotation;
    static private boolean spin, rebound;

    public ClassicBoard(boolean properties, int n){
        this.n = n;
        this.tiles = createClassicBoard(properties, n);
    }


    // Creates an n by n grid
    static Tile[][] createClassicBoard(boolean properties, int n){
        Tile[][] tiles = new Tile[n][n];

        // build tiles
        for (int row = 0; row < n; row++) for (int col = 0; col < n; col++){
            tiles[row][col] = new Tile(properties);
        }

        // build connections
        for (int row = 0; row < n; row++){
            for (int col = 0; col < n; col++){
                Tile current = tiles[row][col];
                //connect all its neighbors

                for (int rowOffset = -1; rowOffset <= 1; rowOffset++){
                    for (int colOffset = -1; colOffset <=1; colOffset++){
                        int nRow = row + rowOffset;
                        int nCol = col + colOffset;
                        if (!(0 <= nRow && nRow < n && 0 <= nCol && nCol < n)) continue;
                        Tile neighbor  = tiles[nRow][nCol];
                        if (neighbor == current) continue;
                        //get direction
                        int direction = -1;
                        if (rowOffset == -1){
                            if (colOffset == -1) direction = Tile.UL;
                            if (colOffset ==  0) direction = Tile.U;
                            if (colOffset ==  1) direction = Tile.UR;
                        }
                        if (rowOffset == 0){
                            if (colOffset == -1) direction = Tile.L;
                            if (colOffset ==  1) direction = Tile.R;
                        }
                        if (rowOffset == 1){
                            if (colOffset == -1) direction = Tile.DL;
                            if (colOffset ==  0) direction = Tile.D;
                            if (colOffset ==  1) direction = Tile.DR;
                        }
                        current.biconnect(direction, neighbor);
                    }
                }
            }
        }

        return tiles;
    }


    public void biconnect(int direction, ClassicBoard o){
        this.neighbors[direction] = o;
        o.neighbors[Tile.reverse(direction)] = this;
    }
    @Override
    public StackPane asJavaFXNode() {
        StackPane stack = new StackPane();

        HashMap<String, Image> images = new HashMap<>();

        final int s = 100;
        // Setup the board
        GridPane board = new GridPane();
        for (int row=0; row<n; row++) for (int col=0;col<n;col++){
           Tile t = tiles[row][col];
           ImageView emoji;

           String emojiID = t.getMark();
           if (emojiID.equals("")) emojiID = "blank";

           // Get image and update table
           Image image = images.getOrDefault(emojiID, new Image(String.format("%s.png", emojiID)));
           images.put(emojiID, image);

           emoji = new ImageView(image);
           emoji.setFitWidth(s-10);
           emoji.setFitHeight(s-10);

           // onClick
           String command = String.format("select %d %d", row, col);
           String clicked = String.format("Clicked %d %d", row, col);
           emoji.setOnMouseClicked(e -> {
               System.out.println(clicked);
               Controller.execute(command);
           });

           board.add(emoji, col, row);
           board.setAlignment(Pos.CENTER);
        }
        // Setup board constraints
        ColumnConstraints cMax = new ColumnConstraints(s,s,s);
        RowConstraints rMax = new RowConstraints(s,s,s);
        for (int i =0 ; i <n; i++) board.getColumnConstraints().add(cMax);
        for (int i =0 ; i <n; i++) board.getRowConstraints().add(rMax);
        /*
        board.getColumnConstraints().addAll(cMax, cMax, cMax);
        board.getRowConstraints().addAll(rMax, rMax, rMax);
         */

        // Setup tic tac toe board image
        ImageView gridImage = new ImageView(new Image("ttt.jpg"));
        gridImage.setFitWidth(s*n);
        gridImage.setFitHeight(s*n);
        board.setAlignment(Pos.CENTER);

        stack.getChildren().addAll(gridImage, board);
        //stack.getChildren().addAll(gridImage);
        stack.getChildren().forEach(c -> StackPane.setAlignment(c, Pos.CENTER));

        if (spin){
            RotateTransition rt = new RotateTransition();
            rt.setDuration(Duration.seconds(1));
            rt.setByAngle(360);
            rt.setCycleCount(Animation.INDEFINITE);
            rt.setNode(stack);
            rt.play();
        }

        if (rebound){
            TranslateTransition tt = new TranslateTransition();
            tt.setDuration(Duration.seconds(1));
            tt.setFromX(ClassicBoard.x);
            tt.setFromY(ClassicBoard.y);
            tt.setToX(Math.random()*800 - 400);
            tt.setToY(Math.random()*600 - 300);
            tt.setCycleCount(1);
            tt.setNode(stack);
            tt.setOnFinished(e -> {
                ClassicBoard.x = tt.getToX();
                ClassicBoard.y = tt.getToY();
                tt.setFromX(ClassicBoard.x);
                tt.setFromY(ClassicBoard.y);
                tt.setToX(Math.random()*800 - 400);
                tt.setToY(Math.random()*600 - 300);
                tt.play();
            });
            tt.play();
        }

        double centerX = stack.getWidth()/2.0;
        double centerY = stack.getHeight()/2.0;
        System.out.println("stack x " + stack.getTranslateX());
        System.out.println("stack y " + stack.getTranslateY());


        //stack.getTransforms().add(new Rotate(-90*rotation, 312, 312));
        //stack.getTransforms().add(new Rotate(-90*rotation, 354.5, 300));
        RotateTransition rt = new RotateTransition();
        int signe = rotation == 0 ? 1 : rotation / Math.abs(rotation);
        if (rotation != 0){
            rt.setByAngle(signe*Math.abs(rotation)*-90);
        } else rt.setByAngle(0);
        rt.setDuration(Duration.millis(1));
        rt.setCycleCount(Math.abs(rotation) % 4);
        rt.setNode(stack);
        rt.play();
        System.out.println("angle " + rt.getByAngle());

        return stack;
    }

    @Override
    public Tile selectTile(int[] coords, Marker m) {
        int row = coords[0];
        int col = coords[1];
        if (0 <= row && 0 <= col && row < n && col < n)
            return tiles[row][col].placeMarker(m);
        System.out.println(Utilities.ANSI_RED + "Bad tile: " + row + " " + col + Utilities.ANSI_RESET);
        return null;
    }

    @Override
    public String selectRandomTile() {
        List<Integer[]> available = new ArrayList<>();
        for (int i = 0; i < n; i++) for (int j = 0; j < n; j++){
            if (tiles[i][j].isEmpty()) {
                available.add(new Integer[]{i,j});
            }
        }

        int randomIndex = (int) Math.round(Math.random() * (available.size()-1));

        int randomRow = available.get(randomIndex)[0];
        int randomCol = available.get(randomIndex)[1];
        return String.format("%d %d", randomRow, randomCol);
    }

    @Override
    public int determineWinner() {
        // Logic for finding winner
        // 1 -> Party 1 wins. 2 -> Party 2 wins. 3 -> Tie. 0 -> No winner.

        // Check diagonal for winning solutions
        for (int rowcol = 0; rowcol < n; rowcol++){
            Tile curr = tiles[rowcol][rowcol];
            if (curr.isEmpty()) continue;
            int targetId = curr.getOccupantId();

            // Check all directions for a winner
            for (int direction = Tile.U; direction < Tile.numDirections; direction++){
                int inARow = 1;
                Tile neighbor = curr.getNeighbor(direction);
                while (neighbor != null && neighbor.getOccupantId() == targetId){
                    //System.out.println("Woo! " + neighbor);
                    inARow++;
                    neighbor = neighbor.getNeighbor(direction);
                }
                int reverseDirection = Tile.reverse(direction);
                neighbor = curr.getNeighbor(reverseDirection);
                while (neighbor != null && neighbor.getOccupantId() == targetId){
                    //System.out.println("Woo! " + neighbor);
                    inARow++;
                    neighbor = neighbor.getNeighbor(reverseDirection);
                }
                if (inARow == n) return targetId;
            }
            // N in a row!
        }
        // TODO: Check other diagonal for even N

        // Check to see if Tie
        if (!hasAvailableTiles()) return 3;

        // No winner and no tie.
        return 0;
    }

    @Override
    public Tile getTile(int[] coords) {
        return tiles[coords[0]][coords[1]];
    }

    @Override
    public void spin() {
        spin = true;
    }

    @Override
    public void rebound() {
        rebound = true;
    }

    @Override
    public void clear() {
        for (int i = 0; i < n; i++) for (int j = 0; j < n; j++){
            tiles[i][j].clearMarker();
        }
    }

    @Override
    public void clearEffects() {
        spin = false;
        rebound = false;
        y = 300;
        x = 400;
    }
    @Override
    public void rotate(String direction) {
        switch (direction){
            case "cw":{
                this.rotation--;
                break;
            }
            case "ccw":{
                this.rotation++;
                break;
            }
        }
    }
    boolean hasAvailableTiles() {
        for (int i = 0; i < n; i++) for (int j = 0; j < n; j++)
                if (tiles[i][j].isEmpty()) return true;
        return false;
    }

    ClassicBoard getNeighbor(int direction){
       return neighbors[direction];
    }

    public void placeMarker(Marker m){
        this.m = m;
    }

    public Marker getMarker(){
        return this.m;
    }
}
