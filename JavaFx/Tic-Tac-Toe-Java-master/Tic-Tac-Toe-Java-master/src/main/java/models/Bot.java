package models;

public class Bot extends Player {

    private Symbol opponentSymbol;

    public Bot(String name, Symbol symbol) {
        super(name, symbol);
    }

    // Returns an array of 2 integers representing the coordinates of the move to play
    public int[] play(Grid grid, Symbol opponentSymbol, int difficultyLevel) {
        if (difficultyLevel == 1) {
            return playEasy(grid);
        } else {
            return playHard(grid, opponentSymbol);
        }
    }

    // Returns the coordinates of the move to play in easy mode
    private int[] playEasy(Grid grid) {
        int[] move = new int[2];
        do {
            move[0] = (int) (Math.random() * grid.getSize());
            move[1] = (int) (Math.random() * grid.getSize());
        } while (!grid.isEmptyCell(move[0], move[1]));
        return move;
    }

    // Returns the coordinates of the move to play in hard mode
    private int[] playHard(Grid grid, Symbol opponentSymbol) {
        this.opponentSymbol = opponentSymbol;
        int bestVal = Integer.MIN_VALUE;
        int[] bestMove = new int[] { -1, -1 };

        for (int i = 0; i < grid.getSize(); i++) {
            for (int j = 0; j < grid.getSize(); j++) {
                if (grid.isEmptyCell(i, j)) {
                    grid.setSymbol(i, j, this.getSymbol());
                    int moveVal = minimax(grid, 0, false);
                    grid.setSymbol(i, j, null);

                    if (moveVal > bestVal) {
                        bestMove[0] = i;
                        bestMove[1] = j;
                        bestVal = moveVal;
                    }
                }
            }
        }
        return bestMove;
    }

    // Returns the value of the best possible move
    private int minimax(Grid grid, int depth, boolean isMaximizing) {
        int score = evaluate(grid);

        if (score == 10 || score == -10) {
            return score;
        }

        if (grid.isFull()) {
            return 0;
        }

        int best;
        if (isMaximizing) {
            best = Integer.MIN_VALUE;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (grid.isEmptyCell(i, j)) {
                        grid.setSymbol(i, j, this.getSymbol());
                        best = Math.max(best, minimax(grid, depth + 1, false));
                        grid.setSymbol(i, j, null);
                    }
                }
            }
        } else {
            best = Integer.MAX_VALUE;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (grid.isEmptyCell(i, j)) {
                        grid.setSymbol(i, j, this.getOpponentSymbol());
                        best = Math.min(best, minimax(grid, depth + 1, true));
                        grid.setSymbol(i, j, null);
                    }
                }
            }
        }
        return best;
    }

    // Returns the value of the board
    private int evaluate(Grid grid) {
        int score = 0;
        Symbol[][] board = grid.getGrid();

        // Check rows
        for (int row = 0; row < 3; row++) {
            if (board[row][0] == board[row][1] && board[row][1] == board[row][2]) {
                if (board[row][0] == this.getSymbol()) {
                    return 10;
                } else if (board[row][0] == this.getOpponentSymbol()) {
                    return -10;
                }
            }
        }

        // Check columns
        for (int col = 0; col < 3; col++) {
            if (board[0][col] == board[1][col] && board[1][col] == board[2][col]) {
                if (board[0][col] == this.getSymbol()) {
                    return 10;
                } else if (board[0][col] == this.getOpponentSymbol()) {
                    return -10;
                }
            }
        }

        // Check one diagonal
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            if (board[0][0] == this.getSymbol()) {
                return 10;
            } else if (board[0][0] == this.getOpponentSymbol()) {
                return -10;
            }
        }

        // Check the other diagonal
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            if (board[0][2] == this.getSymbol()) {
                return 10;
            } else if (board[0][2] == this.getOpponentSymbol()) {
                return -10;
            }
        }

        return score;
    }

    // Returns the opponent's symbol
    private Symbol getOpponentSymbol() {
        return opponentSymbol;
    }
}
