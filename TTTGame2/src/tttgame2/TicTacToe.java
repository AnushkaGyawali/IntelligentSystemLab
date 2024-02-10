package tttgame2;
public class TicTacToe {
    char[][] board; // TicTacToe board
    char PLAYER_1 = 'X';
    char PLAYER_2 = 'O';
    char turn; // Whose turn is it?

    /*
     * Initialize the n x n board. X always starts first.
     */
    public TicTacToe(int n) {
        board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = ' ';
            }
        }
        turn = PLAYER_1; // Player 1 starts first
    }

    /*
     * Check rows, columns, and diagonals for a win based on the new rule.
     * If there is a winner, return the winner (X or O).
     * Otherwise, return a blank space character.
     */
    public char getWinner() {
        int n = board.length;

        // Count three-in-a-row lines for each player
        int[] count = new int[2];
        for (int i = 0; i < n; i++) {
            // Check rows
            count[0] += countConsecutiveMarks(i, 0, 0, 1); // Player 1 count
            count[1] += countConsecutiveMarks(i, 0, 0, -1); // Player 2 count

            // Check columns
            count[0] += countConsecutiveMarks(0, i, 1, 0); // Player 1 count
            count[1] += countConsecutiveMarks(0, i, -1, 0); // Player 2 count
        }

        // Check diagonals
        count[0] += countConsecutiveMarks(0, 0, 1, 1); // Player 1 count
        count[1] += countConsecutiveMarks(n - 1, 0, -1, 1); // Player 2 count

        if (count[0] > count[1])
            return PLAYER_1;
        else if (count[1] > count[0])
            return PLAYER_2;
        else
            return ' '; // Draw
    }

    /*
     * Count consecutive marks (X or O) in the specified direction (dx, dy)
     * starting from the position (x, y).
     */
    private int countConsecutiveMarks(int x, int y, int dx, int dy) {
        char mark = board[x][y];
        int n = board.length;
        int count = 0;

        while (x >= 0 && x < n && y >= 0 && y < n && board[x][y] == mark) {
            count++;
            x += dx;
            y += dy;
        }

        return count >= 3 ? 1 : 0;
    }

    /*
     * Return the size of the board.
     */
    public int getBoardSize() {
        return board.length;
    }

    /*
     * Display the TicTacToe board.
     */
    public void displayBoard() {
        int n = board.length;
        System.out.println("-------------");
        for (int i = 0; i < n; i++) {
            System.out.print("| ");
            for (int j = 0; j < n; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println("\n-------------");
        }
    }

    /*
     * Return the player whose turn it is.
     */
    public char whoseTurn() {
        return turn;
    }

    /*
     * Fill the board at [row, col] with X or O
     * depending on whose turn it is,
     * then change the turn from X to O or O to X.
     */
    public void putMark(int row, int col) {
        if (turn == PLAYER_1) {
            board[row][col] = PLAYER_1;
            turn = PLAYER_2;
        } else {
            board[row][col] = PLAYER_2;
            turn = PLAYER_1;
        }
    }

    /*
     * Return the mark at [row, col] in the board.
     */
    public char getMark(int row, int col) {
        return board[row][col];
    }
}
