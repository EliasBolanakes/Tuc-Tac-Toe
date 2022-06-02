import Model.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    public void ifAllGamesWonScoreIs100(){

        Player player=new Player("SomePlayer");
        player.setWins(10);
        player.setGamesPlayed(10);
        assertEquals(100,player.score());
    }
    @Test
    public void ifAllGamesLostScoreIs0(){

        Player player=new Player("SomePlayer");
        player.setGamesPlayed(10);
        assertEquals(0,player.score());
    }
    @Test
    public void ifAllGamesDrawScoreIs50(){

        Player player=new Player("SomePlayer");
        player.setDraws(10);
        player.setGamesPlayed(10);
        assertEquals(50,player.score());
    }

    @Test void threeWinsTwoLossesAndOneDrawIs58_33(){

        Player player=new Player("SomePlayer");
        player.setGamesPlayed(6);
        player.setDraws(1);
        player.setWins(3);
        assertEquals(58.333333333333336,player.score());
    }
}