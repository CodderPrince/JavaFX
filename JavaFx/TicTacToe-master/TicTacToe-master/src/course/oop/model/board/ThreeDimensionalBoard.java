package course.oop.model.board;

import course.oop.controller.Controller;
import course.oop.model.Marker;
import javafx.animation.Animation;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.util.Duration;

import java.util.*;

public class ThreeDimensionalBoard implements GameBoard {

    // 1st index indicates team
    // 2nd index indicates X Y or Z
    // 3rd index is all values from 0 to 8
    // 3 in a row looks like any ints = 3 in X Y or Z, or 3 nonzeroes in a row
    private static final int TEAM1=0, TEAM2=1, BLOCKED=2, X=0, Y=1, Z=2;
    double x = 400, y=300;
    //List<Point>[] selections;
    boolean[][][][] selections;
    ClassicBoard[] boards;
    final int n;
    boolean spin, rebound;
    int rotations;

    public ThreeDimensionalBoard(boolean properties, int n){
        selections = new boolean[3][n][n][n];
        //selections[0] = new ArrayList<Point>(3*n);
        //selections[1] = new ArrayList<Point>(3*n);
        this.n = n;
        boards = create3DBoard(properties, n);
    }


    private static ClassicBoard[] create3DBoard(boolean properties, int n){
        ClassicBoard[] boards = new ClassicBoard[n];
        for (int i=0; i<n; i++) boards[i] = new ClassicBoard(properties,n);
        return boards;
    }
    @Override
    public StackPane asJavaFXNode() {
        VBox boards = new VBox();
        boards.setSpacing(0);
        /*

        Node[] boardNodes = new Node[n];
        for (int i  = 0; i < n; i++){
            Node boardNode = this.boards[i].asJavaFXNode();
            boardNode.
        }

        boards.getChildren().addAll(boardNodes);
        */
        //SubScene ss = new SubScene();
        /*
        ImageView image = new ImageView(new Image("ttt.jpg"));
        image.setFitHeight(100);
        image.setFitWidth(100);
        ImageView image2 = new ImageView(new Image("ttt.jpg"));
        image2.setFitHeight(100);
        image2.setFitWidth(100);
        ImageView image3 = new ImageView(new Image("ttt.jpg"));
        image3.setFitHeight(100);
        image3.setFitWidth(100);
        boards.getChildren().addAll(image, image2, image3);
        StackPane stack = new StackPane(boards);
        RotateTransition rt = new RotateTransition(Duration.seconds(1), image);
        RotateTransition rt2 = new RotateTransition(Duration.seconds(1), image2);
        RotateTransition rt3 = new RotateTransition(Duration.seconds(1), image3);
        rt.setAxis(Rotate.X_AXIS);
        rt.setByAngle(45);
        rt.play();
        rt2.setAxis(Rotate.X_AXIS);
        rt2.setByAngle(45);
        rt2.play();
        rt3.setAxis(Rotate.X_AXIS);
        rt3.setByAngle(45);
        rt3.play();
         */
        //return stack;

        // for each sub board
        int s = 50;
        for (int z = 0; z < n; z++) {
            // Constraints
            ColumnConstraints cMax = new ColumnConstraints(s,s,s);
            RowConstraints rMax = new RowConstraints(s,s,s);
            ClassicBoard currentBoard = this.boards[z];
            ImageView tttBoard = new ImageView(new Image("ttt.jpg"));
            tttBoard.setFitHeight(50*n);
            tttBoard.setFitWidth(50*n);
            StackPane substack = new StackPane(tttBoard);
            GridPane board = new GridPane();
            for (int i = 0; i < n; i++)
                board.getColumnConstraints().add(cMax);
            for (int i = 0; i < n; i++)
                board.getRowConstraints().add(rMax);
            board.setAlignment(Pos.CENTER);
            substack.getChildren().add(board);

            // for each tile, add its image to the board
            for (int row=0; row<n; row++) for (int col=0; col<n; col++){
                Tile t = currentBoard.getTile(new int[]{row, col});
                ImageView emoji;
                String emojiID = t.getMark();
                if (emojiID.equals("")) emojiID = "blank";
                emoji = new ImageView(new Image(emojiID + ".png"));
                emoji.setFitWidth(s-5);
                emoji.setFitHeight(s-5);

                // action
                String command = String.format("select %d %d %d", row, col, z);
                emoji.setOnMouseClicked(e -> Controller.execute(command));
                board.add(emoji, col, row);
            }
            // Make 3D
            Rotate r = new Rotate(45, Rotate.X_AXIS);

            /*
            RotateTransition rt = new RotateTransition(Duration.ONE, substack);
            rt.setAxis(Rotate.X_AXIS);
            rt.setByAngle(45);
            rt.play();
            */
            substack.getTransforms().add(r);
            substack.getChildren().forEach(child -> StackPane.setAlignment(child, Pos.CENTER));
            boards.getChildren().add(substack);
            // spin
            if (spin){
                RotateTransition spin = new RotateTransition();
                spin.setByAngle(360);
                spin.setDuration(Duration.seconds(1));
                spin.setCycleCount(Animation.INDEFINITE);
                spin.setNode(substack);
                spin.play();
            }
        }
        StackPane stack = new StackPane(boards);
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
        return stack;
    }

    @Override
    public Tile selectTile(int[] coords, Marker m) {
        int team = m.getTeamNumber()-1;
        System.out.println("Woah. you selected a tile, team " + team);
        //selections[team].add(new Point(coords));
        if (selections[0][coords[0]][coords[1]][coords[2]] || selections[1][coords[0]][coords[1]][coords[2]])
            return null;
        selections[team][coords[0]][coords[1]][coords[2]] = true;
        return boards[coords[2]].selectTile(coords, m);
    }

    @Override
    public String selectRandomTile() {
        List<Point> possiblePoints = new ArrayList<>();
        for(int i=0; i<n; i++) for (int j=0; j<n; j++) for (int k=0; k<n; k++){
            if (selections[TEAM1][i][j][k] || selections[TEAM2][i][j][k] || selections[BLOCKED][i][j][k]) continue;
            else possiblePoints.add(new Point(i,j,k));
        }
        Point r = possiblePoints.get((int) Math.floor(Math.random() * possiblePoints.size()));
        return String.format("%d %d %d", r.x(), r.y(), r.z());
    }


    @Override
    public int determineWinner() {
        System.out.println("determine winner");
        // checking each XY square for winner
        /*
        for (ClassicBoard subboard : this.boards) {
            int code = subboard.determineWinner();
            if (code == 1 || code == 2) return code;
        }
         */

        // check if team 1 (i=0) has won
        for (int team = 1; team <= 2; team++){
            boolean[][][] selections = this.selections[team-1];
            // checking each XY square
            for (int z = 0; z < n; z++){
                // check x's
                //System.out.println("0");
                for (int x = 0; x < n; x++) {
                    boolean winByXs = true;
                    for (int y = 0; y < n; y++) if (!selections[x][y][z]) winByXs = false;
                    if (winByXs) return team;
                }
                // check z's
                //System.out.println("1");
                for (int y = 0; y < n; y++) {
                    boolean winByZs = true;
                    for (int x = 0; x < n; x++) if (!selections[x][y][z]) winByZs = false;
                    if (winByZs) return team;
                }
                // check diagonals
                //System.out.println("2");
                boolean winByXZs1 = true;
                for (int xy = 0; xy < n; xy++){
                    if (!selections[xy][xy][z]) winByXZs1 = false;
                }
                if (winByXZs1) return team;

                //System.out.println("3");
                boolean winByXZs2 = true;
                for (int x = 0; x < n; x++){
                    int y = n-x-1;
                    if (!selections[x][y][z]) winByXZs2 = false;
                }
                if (winByXZs2) return team;
            }
            // checking each XZ square
            for (int y = 0; y < n; y++){
                // check x's
                //System.out.println("0");
                for (int x = 0; x < n; x++) {
                    boolean winByXs = true;
                    for (int z = 0; z < n; z++) if (!selections[x][y][z]) winByXs = false;
                    if (winByXs) return team;
                }
                // check z's
                //System.out.println("1");
                for (int z = 0; z < n; z++) {
                    boolean winByZs = true;
                    for (int x = 0; x < n; x++) if (!selections[x][y][z]) winByZs = false;
                    if (winByZs) return team;
                }
                // check diagonals
                //System.out.println("2");
                boolean winByXZs1 = true;
                for (int xz = 0; xz < n; xz++){
                    if (!selections[xz][y][xz]) winByXZs1 = false;
                }
                if (winByXZs1) return team;

                //System.out.println("3");
                boolean winByXZs2 = true;
                for (int x = 0; x < n; x++){
                    int z = n-x-1;
                    if (!selections[x][y][z]) winByXZs2 = false;
                }
                if (winByXZs2) return team;
            }
            // checking each YZ square
            for (int x = 0; x < n; x++){
                // check y's
                //System.out.println("4");
                for (int y = 0; y < n; y++) {
                    boolean winYs = true;
                    for (int z = 0; z < n; z++) if (!selections[x][y][z]) winYs = false;
                    if (winYs) return team;
                }
                // check z's
                //System.out.println("5");
                for (int z = 0; z < n; z++) {
                    boolean winZs = true;
                    for (int y = 0; y < n; y++) if (!selections[x][y][z]) winZs = false;
                    if (winZs) return team;
                }
                // check diagonals
                //System.out.println("6");
                boolean winYZs1 = true;
                for (int yz = 0; yz < n; yz++){
                    if (!selections[x][yz][yz]) winYZs1 = false;
                }
                if (winYZs1) return team;

                boolean winYZs2 = true;
                //System.out.println("7");
                for (int z = 0; z < n; z++){
                    int y = n-x-1;
                    if (!selections[x][y][z]) winYZs2 = false;
                }
                if (winYZs2) return team;
            }
            // check each diagonal
            boolean winXYZs = true;
            //System.out.println("8");
            for (int xyz = 0; xyz < n; xyz++){
                if (!selections[xyz][xyz][xyz]) winXYZs = false;
            }
            if (winXYZs) return team;

            //System.out.println("9");
            boolean winXYZs2 = true;
            for (int xy = 0; xy < n; xy++){
                int z = n - xy - 1;
                if (!selections[xy][xy][z]) winXYZs2 = false;
            }
            if (winXYZs2) return team;

            //System.out.println("10");
            winXYZs = true;
            for (int yz = 0; yz < n; yz++){
                int x = n - yz -1;
                if (!selections[x][yz][yz]) winXYZs = false;
            }

            //System.out.println("11");
            winXYZs = true;
            for (int y = 0; y < n; y++){
                int xz = n - y - 1;
                if (!selections[xz][y][xz]) winXYZs = false;
            }
            if (winXYZs) return team;
        }
        if (!hasAvailableTiles()) return 3;
        return 0;
    }

    private boolean hasAvailableTiles(){
        for (ClassicBoard b : boards)
            if (b.hasAvailableTiles()) return true;
        return false;
    }

    @Override
    public Tile getTile(int[] coords) {
        return this.boards[coords[2]].getTile(new int[]{coords[0], coords[1]});
    }

    @Override
    public void spin() {
        this.spin = true;
    }

    @Override
    public void rebound() {
        this.rebound = true;
    }

    @Override
    public void clear() {

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
                this.rotations--;
                break;
            }
            case "ccw":
            {
                this.rotations++;
                break;
            }
        }
    }
}

class Point{
    int coords[];
    //int x, y, z;
    public Point(int x, int y, int z){
        coords = new int[3];
        coords[0] = x;
        coords[1] = y;
        coords[2] = z;
    }
    @Override
    public String toString(){
        return String.format("(%d , %d, %d)", coords[0], coords[1], coords[2]);
    }
    public Point(int[] coords){
        this.coords = coords;
    }
    public int x(){
        return coords[0];
    }
    public int y(){
        return coords[1];
    }
    public int z(){
        return coords[2];
    }
    public Point copy(){
        Point p = new Point(coords);
        return p;
    }

    public static boolean winningSolution(List<Point> tiles, int n){
        System.out.println("nandefuckdesuka");
        System.out.println(tiles);
        if (tiles.size() != n) return false;
        //assert tiles.size() == n;
        int coords[][] = new int[n][3];
        // collect X's, Y's, Z's
        int[] xs = new int[n];
        int[] ys = new int[n];
        int[] zs = new int[n];
        int[][] axes = new int[3][n];
        for (int i=0; i <n ;i++){
            axes[0][i] = tiles.get(i).x();
            axes[1][i] = tiles.get(i).y();
            axes[2][i] = tiles.get(i).z();
        }
        for (int iAxis = 0; iAxis < 3; iAxis++)
            {
                int[] axis = axes[iAxis];
            Arrays.sort(axis);
            boolean allsame = true;
            //System.out.println("Checking coords[if all same")
            int same = axis[0];
            for (int i : axis){
                if (i != same) allsame = false;
            }

            boolean increasing = true;
            int prev = axis[0];
            for (int i = 1; i < n; i++){
                if (axis[i] - prev != 1) {
                    increasing = false;
                    break;
                }
                prev = axis[i];
            }

            boolean decreasing = true;
            prev = axis[0];
            for (int i = 1; i < n; i++){
                if (axis[i] - prev != -1) {
                    decreasing = false;
                    break;
                }
                prev = axis[i];
            }
            if (!allsame && !increasing && !decreasing) return false;
        }
        return true;
    }
}
