package Model;

public class Board {

    private final char[][] board;

    public static final int ROWS=3;
    public static final int COLS=3;

    public Board(){
        board=new char[ROWS][COLS];
        for(int i=0;i<ROWS;i++){
            for(int j=0;j<COLS;j++){
                board[i][j]=' ';
            }
        }
    }

    /** Checking for winning combo and Returning:
     * +10 for maximizing player win
     * -10 for minimizing player turn
     * 0 for draw or non-terminal game state  **/

    public static double checkIfWon(char[][] board, char maximizerChar) {

        char minimizerChar=(maximizerChar=='X'?'O':'X');

        for (int i = 0; i < Board.ROWS; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                if (board[i][0] == maximizerChar)
                    return +10;

                else if (board[i][0] == minimizerChar)
                    return -10;
            }
        }

        for (int i = 0; i < Board.COLS; i++) {
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                if (board[0][i] == maximizerChar)
                    return +10;

                else if (board[0][i] == minimizerChar)
                    return -10;
            }
        }

        if (board[0][0] == board[1][1] && board[1][1] == board[2][2]){
            if (board[0][0] == maximizerChar)
                return +10;

            else if (board[0][0] == minimizerChar)
                return -10;
        }

        else if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            if (board[0][2] == maximizerChar)
                return +10;

            else if (board[0][2] == minimizerChar)
                return -10;
        }
        return 0;
    }


    public static boolean isAvailable (char[][] board,int row,int col){

        return board[row][col] == ' ';
    }

    public static void undoMove(char[][] board,int row, int col){

        board[row][col]=' ';
    }

    // returning true if board has moves left

    public static boolean hasMovesLeft(char[][] board){

        for(int i=0;i<ROWS;i++){
            for (int j=0;j<COLS;j++){
                if(board[i][j]==' ')
                    return true;
            }
        }
        return false;
    }

    public char[][] getBoard() {
        return board;
    }
}

