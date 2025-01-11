package models;

public class Grid {
    private Symbol[][] grid;
    private int size;

    public Grid(int size) {
        this.size = size;
        this.grid = new Symbol[size][size];
    }

    // Set le symbole dans la case (row, column)
    public void setSymbol(int row, int column, Symbol symbol) {
        this.grid[row][column] = symbol;
    }

    // Retourne le symbole dans la case (row, column)
    public Symbol getSymbol(int row, int column) {
        return this.grid[row][column];
    }

    // Retourne true si la grille est pleine et false sinon
    public boolean isFull() {
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                if (this.grid[i][j] == null) {
                    return false;
                }
            }
        }
        return true;
    }

    // Retourne true si la case (row, column) est vide et false sinon
    public boolean isEmptyCell(int row, int column) {
        return this.grid[row][column] == null;
    }

    // Retourne true si un joueur a gagné et false sinon
    public boolean checkWin() {
        // Vérifier si un joueur a gagné
        for (int i = 0; i < this.size; i++) {
            if (checkLine(i)) {
                return true;
            }
            if (checkColumn(i)) {
                return true;
            }
        }
        if (checkDiagonals()) {
            return true;
        }
        return false;
    }

    // Retourne les cases gagnantes
    public int[] getWinningCases() {
        // Renvoie les cases gagnantes
        for (int i = 0; i < this.size; i++) {
            if (checkLine(i)) {
                return new int[] { i, 0, i, 1, i, 2 };
            }
            if (checkColumn(i)) {
                return new int[] { 0, i, 1, i, 2, i };
            }
        }
        // ck diagonals without using checkDiagonals()
        Symbol symbol = this.grid[0][0];
        if (symbol != null) {
            for (int i = 1; i < this.size; i++) {
                if (this.grid[i][i] == null || !this.grid[i][i].equals(symbol)) {
                    break;
                }
                if (i == this.size - 1) {
                    return new int[] { 0, 0, 1, 1, 2, 2 };
                }
            }

        }
        symbol = this.grid[0][this.size - 1];
        if (symbol != null) {
            for (int i = 1; i < this.size; i++) {
                if (this.grid[i][this.size - 1 - i] == null || !this.grid[i][this.size - 1 - i].equals(symbol)) {
                    break;
                }
                if (i == this.size - 1) {
                    return new int[] { 0, 2, 1, 1, 2, 0 };
                }
            }
        }
        return null;
    }

    // Retourne true si la ligne est gagnante et false sinon
    private boolean checkLine(int line) {
        Symbol symbol = this.grid[line][0];
        if (symbol == null) {
            return false;
        }
        for (int i = 1; i < this.size; i++) {
            if (this.grid[line][i] == null || !this.grid[line][i].equals(symbol)) {
                return false;
            }
        }
        return true;
    }

    // Retourne true si la colonne est gagnante et false sinon
    private boolean checkColumn(int column) {
        Symbol symbol = this.grid[0][column];
        if (symbol == null) {
            return false;
        }
        for (int i = 1; i < this.size; i++) {
            if (this.grid[i][column] == null || !this.grid[i][column].equals(symbol)) {
                return false;
            }
        }
        return true;
    }

    // Retourne true si une des diagonales est gagnante et false sinon
    private boolean checkDiagonals() {
        Symbol symbol = this.grid[0][0];
        if (symbol != null) {
            for (int i = 1; i < this.size; i++) {
                if (this.grid[i][i] == null || !this.grid[i][i].equals(symbol)) {
                    break;
                }
                if (i == this.size - 1) {
                    return true;
                }
            }
        }
        symbol = this.grid[0][this.size - 1];
        if (symbol != null) {
            for (int i = 1; i < this.size; i++) {
                if (this.grid[i][this.size - 1 - i] == null || !this.grid[i][this.size - 1 - i].equals(symbol)) {
                    break;
                }
                if (i == this.size - 1) {
                    return true;
                }
            }
        }
        return false;
    }

    // Vide la grille
    public void clear() {
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                this.grid[i][j] = null;
            }
        }
    }

    // Retourne la taille de la grille
    public int getSize() {
        return this.size;
    }

    // Retourne le symbole du gagnant
    public Symbol getWinner() {
        for (int i = 0; i < this.size; i++) {
            if (checkLine(i)) {
                return this.grid[i][0];
            }
            if (checkColumn(i)) {
                return this.grid[0][i];
            }
        }
        if (checkDiagonals()) {
            return this.grid[0][0];
        }
        return null;
    }

    // Retourne la grille avec les symboles
    public Symbol[][] getGrid() {
        return this.grid;
    }
}
