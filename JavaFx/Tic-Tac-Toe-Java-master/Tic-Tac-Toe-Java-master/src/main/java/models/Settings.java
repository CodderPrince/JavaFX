package models;

public class Settings {

    private Player firstPlayer;
    private Boolean firstPlayerIsRandom;
    private Boolean isOnePlayerMode;
    private int difficultyLevel;

    public Settings(Player firstPlayer) {
        this.firstPlayer = firstPlayer;
        this.firstPlayerIsRandom = false;
        this.isOnePlayerMode = true;
        this.difficultyLevel = 2;
    }

    // Retourne le joueur qui commence
    public Player getFirstPlayer() {
        return firstPlayer;
    }
    
    // Retourne true si le joueur qui commence est choisi aléatoirement et false sinon
    public Boolean getFirstPlayerIsRandom() {
        return firstPlayerIsRandom;
    }

    // Change le joueur qui commence
    public void setFirstPlayer(Player firstPlayer) {
        this.firstPlayer = firstPlayer;
    }

    // Change si le joueur qui commence est choisi aléatoirement
    public void setFirstPlayerIsRandom(Boolean firstPlayerIsRandom) {
        this.firstPlayerIsRandom = firstPlayerIsRandom;
    }

    // Retourne true si le mode de jeu est 1 joueur et false sinon
    public Boolean getIsOnePlayerMode() {
        return isOnePlayerMode;
    }

    // Change si le mode de jeu est 1 joueur
    public void setIsOnePlayerMode(Boolean isOnePlayerMode) {
        this.isOnePlayerMode = isOnePlayerMode;
    }

    // Retourne le niveau de difficulté du Bot
    public int getDifficultyLevel() {
        return difficultyLevel;
    }

    // Change le niveau de difficulté du Bot
    public void setDifficultyLevel(int difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }
}
