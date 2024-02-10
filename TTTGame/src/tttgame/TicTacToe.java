package tttgame;
public class TicTacToe
{
    char[][] board=new char[3][3]; // TicTacToe board has 3 rows and 3 columns.
    char PLAYER_1 = 'X'; 
    char PLAYER_2 = 'O';
    char turn; // Whose turn is it?
   /*
    * Initialize the 2D array. X always start's first.
    */
    public TicTacToe() {
        for(int row=0;row<board.length;row++){
          for(int col=0;col<board[row].length;col++){
          board[row][col]=' ';
          }
        }
        turn=PLAYER_1;
    }

    /*
    * Check 3 rows, 3 cols and 2 diagonals for a win
    * If there is a winner return who won : X or O
    * Otherwise return a blank (space) character. 
    */
    public char getWinner() {
       for(int row=0;row<board.length;row++){
           if(board[row][0]== turn && board[row][1]== turn && board[row][2]==turn){
           return turn;
           }
       }

        for(int col=0;col<board[0].length;col++){
           if(board[0][col]== turn && board[1][col]== turn && board[2][col]==turn){
           return turn;
           }
       }
        if(board[0][0]== turn && board[1][1]== turn && board[2][2]==turn){
           return turn;
           }
        else if(board[2][0]== turn && board[1][1]== turn && board[0][2]==turn){
           return turn;
           }
        else
        return ' ';
    }

    /*
    * Pretty print the TTT board.
    */
    public void displayBoard() {
       for(int row=0;row<board.length;row++){
          for(int col=0;col<board[row].length;col++){
          System.out.print('[');
          System.out.print(board[row][col]);
          System.out.print(']');

       }

          System.out.println();

       }
    }


    /*
    * Return the Player who has to put a mark.
    */
    public char whoseTurn() {

        return turn;
    }

    /*
    * Fill the board at [row,col] with X or O
    * depending on whose turn it is
    * then change turn from X to O or O to X. 
    */
    public void putMark(int row, int col) {
       board[row][col]=turn;
       turn = (turn=='X') ? 'O' : 'X';

    }

    /*
    * Return the mark at [row,col] in the board.
    */
    public char getMark(int row, int col) {
    return board[row][col];
    }
}