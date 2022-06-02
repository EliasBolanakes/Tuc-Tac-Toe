package Model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Game implements Serializable {

    public Player player1;
    public Player player2;
    LocalDateTime date;
    public Player winner;
    public boolean draw;
    double player1Score;
    double player2Score;

    public Game(Player player1, Player player2){

        this.player1=player1;
        this.player2=player2;
        date=LocalDateTime.now();
        player1Score=player1.score();
        player2Score=player2.score();
        draw=false;
    }

    // returning the opponent of a player in a given match

    public Player opponent(Player player){

        if(player.equals(player1))
            return player2;
        else
            return player1;
    }

    // comparing two games with criteria: result -> opponent score -> time

    public static boolean game1BetterThanGame2(Player player,Game game1,Game game2){

        if(player.equals(game1.winner)&&(!player.equals(game2.winner))){
            return true;
        }
        else if(game1.draw && game2.opponent(player).equals(game2.winner)){
            return true;
        }
        else if((game1.draw&&game2.draw)||game1.winner==game2.winner){

            if(game1.opponent(player).score()>game2.opponent(player).score()){
                return true;
            }
            else if(game1.date.isAfter(game2.date))
                return true;
            else
                return false;
        }
        else
            return false;
    }
}
