package course.oop.model.board;

import course.oop.controller.Controller;
import course.oop.model.Marker;
import javafx.animation.Animation;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UltimateBoard implements GameBoard{
    int rotations;
    static private boolean spin, rebound;
    double x = 400;
    double y = 300;
    // last tile played
    ClassicBoard nextSubBoard = null;
    ClassicBoard[][] subBoards;
    boolean properties;
    int n;

    public UltimateBoard(boolean properties, int n){
        this.properties = properties;
        this.n = n;
        this.subBoards = createUltimateBoard(properties, n);
    }

    private static ClassicBoard[][] createUltimateBoard(boolean properties, int n) {
        ClassicBoard[][] boards = new ClassicBoard[n][n];
        for (int i=0; i<n; i++) for (int j=0; j<n; j++){
            boards[i][j] = new ClassicBoard(properties, n);
        }
        // build connections
        for (int row = 0; row < n; row++){
            for (int col = 0; col < n; col++){
                ClassicBoard current = boards[row][col];
                //connect all its neighbors

                for (int rowOffset = -1; rowOffset <= 1; rowOffset++){
                    for (int colOffset = -1; colOffset <=1; colOffset++){
                        int nRow = row + rowOffset;
                        int nCol = col + colOffset;
                        if (!(0 <= nRow && nRow < n && 0 <= nCol && nCol < n)) continue;
                        ClassicBoard neighbor  = boards[nRow][nCol];
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

        return boards;
    }


    private int getRelatedBoard(int n){
        return n % this.n;
    }
    private int getBoard(int n){
        return n / this.n;
    }

    @Override
    public StackPane asJavaFXNode() {
        StackPane stack = new StackPane();

        HashMap<String, Image> images = new HashMap<>();

//        final int s = 100;
        // Setup the board
        GridPane board = new GridPane();
        board.setGridLinesVisible(true);
        // for every subboard
        for (int row=0; row<n; row++) for (int col=0;col<n;col++) {
            ClassicBoard sub = subBoards[row][col];
            int subBoardSide = 50;

            StackPane substack = new StackPane();
            // switch on won or not
            if (sub.determineWinner() == 0 || sub.determineWinner() == 3 ) {
                // Is grid
                GridPane subBoard = new GridPane();
                for (int rrow = 0; rrow < n; rrow++)
                    for (int ccol = 0; ccol < n; ccol++) {
                        Tile t = sub.getTile(new int[]{rrow, ccol});
                        ImageView emoji;
                        String emojiID = t.getMark();
                        if (emojiID.equals("")) emojiID = "blank";
                        emoji = new ImageView(new Image(emojiID + ".png"));
                        emoji.setFitWidth(subBoardSide - 5);
                        emoji.setFitHeight(subBoardSide - 5);

                        // onClick
                        String command = String.format("select %d %d", row * n + rrow, col * n + ccol);
                        emoji.setOnMouseClicked(e -> Controller.execute(command));
                        subBoard.add(emoji, ccol, rrow);
                    }
                // Setup board constraints
                ColumnConstraints cMax = new ColumnConstraints(subBoardSide, subBoardSide, subBoardSide);
                RowConstraints rMax = new RowConstraints(subBoardSide, subBoardSide, subBoardSide);
                for (int i = 0; i < n; i++){
                    subBoard.getColumnConstraints().addAll(cMax);
                    subBoard.getRowConstraints().addAll(rMax);
                }
                // Setup tic tac toe board image
                ImageView gridImage = new ImageView(new Image("ttt.jpg"));
                gridImage.setFitWidth(subBoardSide * n);
                gridImage.setFitHeight(subBoardSide * n);
                subBoard.setAlignment(Pos.CENTER);

                //substack.getChildren().addAll(gridImage, subBoard);
                substack.getChildren().addAll(gridImage, subBoard);
                substack.getChildren().forEach(c -> StackPane.setAlignment(c, Pos.CENTER));
                if (this.nextSubBoard != null && this.nextSubBoard != sub) substack.setEffect(new ColorAdjust(0.0, 0, -0.2, 0.0));
            } else {
                // Is single emoji.
                String emojiID = sub.getMarker().getMark();
                ImageView tttBoard =  new ImageView(new Image("ttt.jpg"));
                ImageView emoji = new ImageView(new Image(emojiID + ".png"));

                tttBoard.setFitHeight(subBoardSide*n - 5);
                tttBoard.setFitWidth(subBoardSide*n - 5);
                emoji.setFitHeight(subBoardSide*n - 5);
                emoji.setFitWidth(subBoardSide*n - 5);
                substack.getChildren().addAll(tttBoard, emoji);
                substack.getChildren().forEach(child -> StackPane.setAlignment(child, Pos.CENTER));
            }
            board.add(substack, col, row);
        }
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
            tt.setFromX(this.x);
            tt.setFromY(this.y);
            tt.setToX(Math.random()*800 - 400);
            tt.setToY(Math.random()*600 - 300);
            tt.setCycleCount(1);
            tt.setNode(stack);
            tt.setOnFinished(e -> {
                this.x = tt.getToX();
                this.y = tt.getToY();
                tt.setFromX(this.x);
                tt.setFromY(this.y);
                tt.setToX(Math.random()*800 - 400);
                tt.setToY(Math.random()*600 - 300);
                tt.play();
            });
            tt.play();
        }

        RotateTransition rt = new RotateTransition();
        int signe = rotations == 0 ? 1 : rotations / Math.abs(rotations);
        if (rotations != 0){
            rt.setByAngle(signe*Math.abs(rotations)*-90);
        } else rt.setByAngle(0);
        rt.setDuration(Duration.millis(1));
        rt.setCycleCount(Math.abs(rotations) % 4);
        rt.setNode(stack);
        rt.play();
        System.out.println("angle " + rt.getByAngle());
        stack.getChildren().add(board);
        StackPane.setAlignment(board, Pos.CENTER);

        return stack;
    }

    @Override
    public Tile selectTile(int[] coords, Marker m) {
        //if (! (0 <= row && row <= 3*n && 0 <= col && col <= 3*n)) return null;
        ClassicBoard subBoard = subBoards[getBoard(coords[0])][getBoard(coords[1])];
        if (nextSubBoard != subBoard && nextSubBoard != null){
            System.err.println("Wrong subboard");
            return null;
        }

        nextSubBoard = subBoards[getRelatedBoard(coords[0])][getRelatedBoard(coords[1])];
        coords[0] = coords[0] % n;
        coords[1] = coords[1] % n;
        Tile selected = subBoard.getTile(coords);
        if (!selected.isEmpty()) return null;
        selected.placeMarker(m);
        if (subBoard.determineWinner() == 1 || subBoard.determineWinner() == 2){
            subBoard.placeMarker(m);
        }
        if (nextSubBoard.determineWinner() != 0) nextSubBoard = null;
        return selected;
    }

    @Override
    public String selectRandomTile() {
        ClassicBoard restriction = getRestrictedBoard();
        // if no restrictions
        if (restriction == null){
            // return all free tiles globally
            List<Integer[]> available = new ArrayList<>();
            for (int i = 0; i < n; i++) for (int j=0; j<n; j++){
                ClassicBoard curr = subBoards[i][j];
                if (curr.determineWinner() != 0) continue;
                for (int ii = 0; ii < n; ii++) for (int jj = 0; jj < n; jj++) {
                    if (curr.getTile(new int[]{ii,jj}).isEmpty())
                        available.add(new Integer[]{ii + i*n, jj + j*n});
                }
            }
            int randomIndex = (int) Math.round(Math.random() * (available.size()-1));
            int randomRow = available.get(randomIndex)[0];
            int randomCol = available.get(randomIndex)[1];
            return String.format("%d %d", randomRow, randomCol);
        }

        int nextSubBoardCol = 0, nextSubBoardRow = 0;
        for (int i = 0; i < n ; i++) for (int j = 0; j < n; j++){
            if (subBoards[i][j] == nextSubBoard){
                nextSubBoardCol = j;
                nextSubBoardRow = i;
                break;
            }
        }
        // restricted
        List<Integer[]> available = new ArrayList<>();

        for (int i = 0; i < n; i++) for (int j = 0; j < n; j++) {
            if (restriction.getTile(new int[]{i, j}).isEmpty())
                available.add(new Integer[]{i + nextSubBoardRow * n, j + nextSubBoardCol * n});
        }
        int randomIndex = (int) Math.round(Math.random() * (available.size()-1));
        int randomRow = available.get(randomIndex)[0];
        int randomCol = available.get(randomIndex)[1];
        return String.format("%d %d", randomRow, randomCol);
    }

    private ClassicBoard getRestrictedBoard(){
        return nextSubBoard;
    }

    @Override
    public int determineWinner() {
        // Logic for finding winner
        // 1 -> Party 1 wins. 2 -> Party 2 wins. 3 -> Tie. 0 -> No winner.

        // Check diagonal for winning solutions
        for (int rowcol = 0; rowcol < n; rowcol++){
            ClassicBoard curr = subBoards[rowcol][rowcol];
            int targetId = curr.determineWinner();
            if (targetId == 0) continue;

            // Check all directions for a winner
            for (int direction = Tile.U; direction < Tile.numDirections; direction++){
                int inARow = 1;
                ClassicBoard neighbor = curr.getNeighbor(direction);
                while (neighbor != null && neighbor.determineWinner() == targetId){
                    //System.out.println("Woo! " + neighbor);
                    inARow++;
                    neighbor = neighbor.getNeighbor(direction);
                }
                int reverseDirection = Tile.reverse(direction);
                neighbor = curr.getNeighbor(reverseDirection);
                while (neighbor != null && neighbor.determineWinner() == targetId){
                    //System.out.println("Woo! " + neighbor);
                    inARow++;
                    neighbor = neighbor.getNeighbor(reverseDirection);
                }
                if (inARow == n) return targetId;
            }
            // N in a row!
        }

        // Check to see if Tie
        if (!hasAvailableTiles()) return 3;

        // No winner and no tie.
        return 0;
    }

    private boolean hasAvailableTiles(){
        for (int i=0; i<n; i++) for (int j=0; j<n; j++){
            ClassicBoard sub = subBoards[i][j];
            if (sub.determineWinner() == 0) return true;
        }
        return false;
    }

    @Override
    public Tile getTile(int[] coords) {
        ClassicBoard sub = subBoards[coords[0]/n][coords[1]/n];
        coords[0] = coords[0] % n;
        coords[1] = coords[1] % n;
        return sub.getTile(new int[]{coords[0], coords[1]});
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
        this.subBoards = createUltimateBoard(this.properties, n);
        this.nextSubBoard = null;
    }

    @Override
    public void clearEffects() {
        this.spin = false;
        this.rebound = false;
    }

    @Override
    public void rotate(String direction) {
        switch (direction){
            case "cw":
            {
                rotations--;
                break;
            }
            case "ccw":
            {
                rotations++;
                break;
            }
        }
    }
}
