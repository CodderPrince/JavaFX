package course.oop.model;

import course.oop.controller.state.GameState;
import course.oop.model.board.*;
import course.oop.util.Utilities;
import javafx.scene.layout.StackPane;

import java.util.List;

public class Game {

    public static final int TIE = 3;
    public static final int NO_WINNER = 0;

    // Config separates the configuration from the actual game itself.
    // This allows for multiple games to use the same static config (rematches)
    // This also allows for serialization of configurations for faster configurations in the future.
    private final GameConfig config;
    private final GameBoard board;
    private String status;

    public Game(GameConfig config) {
        this.config = config;
        this.status = config.getStatus();
        if (this.config.ultimate) this.board = new UltimateBoard(config.properties(), config.n);
        else if (this.config.three_dimensional) this.board = new ThreeDimensionalBoard(config.properties(), config.n);
        else this.board = new ClassicBoard(config.properties(), config.n);
    }

    public boolean selectTile(String[] coords, int team, int[] players) {
        // Parse int values
        int rowInt = Utilities.parseIntValue(coords[0]);
        int colInt = Utilities.parseIntValue(coords[1]);
        int zInt = 0;
        if (coords.length == 3) zInt = Utilities.parseIntValue(coords[2]);
        int[] coordsInts = {rowInt, colInt, zInt};

        // get marker
        Player p = config.getPlayer(team, players[team]);
        Marker m = new Marker(team+1, p.getMarker());

        Tile selected = board.selectTile(coordsInts, m);
        if (selected == null) return false;
        else {
            status = selected.triggerProperty(this);
            return true;
        }
    }
    public void clearEffects(){
        board.clearEffects();
    }

    public String selectRandomTile() {
        return board.selectRandomTile();
    }
    public void setStatus(String s){
        this.status = s;
    }

    /*
    public void printGameBoard() {
        System.out.println();
        System.out.println(board.getGameBoardDisplay());
    }
     */

    /*
    public String[][] getGameBoard() {
        return this.board.getGameBoard();
    }
    */

    public int determineWinner() {
        return board.determineWinner();
    }

    public StackPane getDisplay(){
        return board.asJavaFXNode();
    }

    public Player getPlayer(int team, int player) {
        Player p = config.getPlayer(team, player);
        if (p == null) return new NullPlayer();
        return p;
    }

    public GameConfig getConfig() {
        return this.config;
    }

    public int randomTeam(){
       return config.randomTeam();
    }

    public int randomPlayer(int team){
        return config.randomPlayer(team);
    }
    public List<Player> getPlayers(int team){
        return config.getTeam(team);
    }
    public List<Player>[] getTeams(){
        return config.getTeams();
    }
    public List<Player> getTeam(int team){
        return config.getTeam(team);
    }
    public void spin(){
        board.spin();
    }
    public void rebound(){
        board.rebound();
    }
    public void clearBoard(){
        board.clear();
    }
    public String getStatus(){
        return status;
    }
      public void rotate(String direction) {
       board.rotate(direction);
    }
}


