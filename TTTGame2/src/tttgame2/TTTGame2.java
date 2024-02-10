/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tttgame2;

/**
 *
 * @author Dell
 */
import java.util.Scanner;

public class TTTGame2 {
    TicTacToe game = new TicTacToe(4); // For all TTT board related tasks.
    char winner; // Who won?

    /*
     * Start the game
     * Display the results after it is completed.
     */
    public void startGame() {
        game.displayBoard();
        playGame();
        winner = game.getWinner();
        printMessage();
    }
   
    /**
     * Scanner class is used to get [row,col] from standard input
     * Game is completed if there is a winner or all cells are filled.
     */
    public void playGame() {
        Scanner in = new Scanner(System.in);
        int count = 0; // Count number of turns. If it is n^2 it is a draw.
        char turn; // Is it X's turn or O's turn?
        int row, col; // Hold board position.

        // while no one has won and not yet a draw
        while (game.getWinner() == ' ' && count < game.getBoardSize() * game.getBoardSize()) {
            turn = game.whoseTurn();
            System.out.println(turn + "'s turn. Type row and col:");
            do {
                row = in.nextInt();
                col = in.nextInt();
            } while (game.getMark(row, col) != ' '); // Is this cell empty?

            game.putMark(row, col);
            game.displayBoard();
            count++;
        }
        in.close();
    }

    /*
     * Print Win or Draw message.
     */
    public void printMessage() {
        if (winner == 'X')
            System.out.println("X has won!");
        else if (winner == 'O')
            System.out.println("O has won!");
        else
            System.out.println("It's a draw!");
    }

    public static void main(String[] args) {
        TTTGame2 ttt = new TTTGame2();
        ttt.startGame();
    }
} 
