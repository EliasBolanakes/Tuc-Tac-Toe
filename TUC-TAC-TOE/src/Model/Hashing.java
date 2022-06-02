package Model;

import java.security.SecureRandom;

public class Hashing {

    public static final int ROWS=3;
    public static final int COLS=3;
    public static final int SIGNS=2;


    public static long[][][] zobristTable=new long[ROWS][COLS][SIGNS];

    public static long randomGenerator(){

        SecureRandom random=new SecureRandom();
        return random.nextLong();
    }

    // converting position sign to distinct number

    public static byte signToIndex(char sign){

        if(sign=='X')
            return 1;
        else if(sign=='O')
            return 0;
        else
            return -1;
    }

    // assigning a secure random value(to avoid collision) to each possible position for every possible sign

    public void tableInit(){

        for(int row=0; row < ROWS; row++)
            for(int col=0; col < COLS; col++)
                for(int sign=0; sign < SIGNS; sign++)
                    zobristTable[row][col][sign]=randomGenerator();
    }

    //calculation of hash key for a given board based on zobrist table (bitwise calculation)

    public static long computeHash(char[][] board) {

        long hash=0;

        for (int row = 0; row < ROWS; row++)
            for (int col = 0; col < COLS; col++)
                if (!Board.isAvailable(board,row,col)) {

                    byte sign = signToIndex(board[row][col]);
                    hash ^= zobristTable[row][col][sign];
                }
        return hash;
    }
}

