package com.mycompany.tictactoe;
public class TicTacToe {
    private char[][] board; // TicTacToe board has 3 rows and 3 columns.
    private char PLAYER_1 = 'X';
    private char PLAYER_2 = 'O';
    private char turn; // Whose turn is it?

    public TicTacToe() {
        board = new char[3][3];
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                board[row][col] = ' ';
            }
        }
        turn = PLAYER_1;
    }

    public char getWinner() {
        for (int row = 0; row < board.length; row++) {
            if (board[row][0] == turn && board[row][1] == turn && board[row][2] == turn) {
                return turn;
            }
        }
        for (int col = 0; col < board[0].length; col++) {
            if (board[0][col] == turn && board[1][col] == turn && board[2][col] == turn) {
                return turn;
            }
        }
        if (board[0][0] == turn && board[1][1] == turn && board[2][2] == turn) {
            return turn;
        } else if (board[2][0] == turn && board[1][1] == turn && board[0][2] == turn) {
            return turn;
        } else {
            return ' ';
        }
    }

    public void displayBoard() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                System.out.print("[" + board[row][col] + "]");
            }
            System.out.println();
        }
    }

    public char whoseTurn() {
        return turn;
    }

    public void putMark(int row, int col) {
        board[row][col] = turn;
        turn = (turn == PLAYER_1) ? PLAYER_2 : PLAYER_1;
    }

    public char getMark(int row, int col) {
        return board[row][col];
    }
}
