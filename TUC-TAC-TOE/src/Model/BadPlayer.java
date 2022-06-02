package Model;

import java.io.Serializable;
import java.util.Random;

public class BadPlayer extends Player implements Serializable {

    public BadPlayer(String name){

        super(name);
    }

    // making random move

    public Move badPlayerMove(char[][] board){

        Random random=new Random();

        while (true){
            int colMove=random.nextInt(3);
            int rowMove=random.nextInt(3);

            if(Board.isAvailable(board,rowMove,colMove)){
                return new Move(rowMove,colMove);
            }
        }
    }
}
