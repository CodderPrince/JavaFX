package models;

public class Player {

    private String name;
    public Symbol symbol;
    private int score;

    public Player(String name, Symbol symbol) {
        this.name = name;
        this.symbol = symbol;
        this.score = 0;
    }
    
    // Retourne le nom du joueur
    public String getName() {
        return name;
    }

    // Change le nom du joueur
    public void setName(String name) {
        this.name = name;
    }

    // Retourne le symbole du joueur
    public Symbol getSymbol() {
        return symbol;
    }

    // Change le symbole du joueur
    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    // Retourne le score du joueur
    public int getScore() {
        return score;
    }

    // Ajoute 1 au score du joueur
    public void incrementScore() {
        this.score++;
    }

    // Remet le score du joueur à 0
    public void resetScore() {
        this.score = 0;
    }
}
