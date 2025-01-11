package models;

public class Game {

    private final Player[] players;
    private Player currentPlayer;
    public Grid grid;
    public boolean isOver = false;
    
    public Game(Player player1, Player player2, Player firstPlayer) {
        this.players = new Player[]{player1, player2};
        this.currentPlayer = firstPlayer;
        this.grid = new Grid(3);
    }

    public Game(Player player1, Player player2) {
        this.players = new Player[]{player1, player2};
        this.currentPlayer = (Math.random() < 0.5) ? player1 : player2;
        this.grid = new Grid(3);
    }

    // Retourne true si le joueur a placé un symbole dans une case vide et false sinon
    public boolean makeMove(int row, int col) {
        if (grid.isEmptyCell(row, col) && !isOver) {
            grid.setSymbol(row, col, currentPlayer.getSymbol());
            return true;
        }
        return false;
    }

    // Change le joueur courant
    public void switchPlayer() {
        currentPlayer = (currentPlayer == players[0]) ? players[1] : players[0];
    }

    // Retourne le joueur actuel (celui qui doit jouer)
    public Player getCurrentPlayer() {
        return currentPlayer;
    }


}
