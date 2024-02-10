package com.mycompany.tictactoe;
import java.util.Scanner;

public class TTTGame {
    private TicTacToe game = new TicTacToe();
    private char winner;

    public void startGame() {
        game.displayBoard();
        playGame();
        winner = game.getWinner();
        printMessage();
    }

    public void playGame() {
        Scanner in = new Scanner(System.in);
        int count = 0;
        int row, col;
        while (game.getWinner() == ' ' && count < 9) {
            char turn = game.whoseTurn();
            System.out.println(turn + "'s turn. Type row and col:");
            do {
                row = in.nextInt();
                col = in.nextInt();
            } while (boundCheck(row, col) || game.getMark(row, col) != ' ');

            game.putMark(row, col);
            game.displayBoard();
            count++;
        }
        in.close();
    }

    public void printMessage() {
        if (winner == 'X')
            System.out.println("X has won!");
        else if (winner == 'O')
            System.out.println("O has won!");
        else
            System.out.println("It's a draw!");
    }

    public boolean boundCheck(int row, int col) {
        return (row < 0 || col < 0 || row >= 3 || col >= 3);
    }

    public static void main(String[] args) {
        TTTGame ttt = new TTTGame();
        ttt.startGame();
    }
}
