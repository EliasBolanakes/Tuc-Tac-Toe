import Model.Game;
import Model.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    public void winIsBetterThanLoss(){
        Player player1=new Player("ilias");
        Player player2=new Player("giannis");

        Game game1=new Game(player1,player2);
        Game game2=new Game(player1,player2);

        game1.winner=player1;
        game2.winner=player2;

        assertTrue(Game.game1BetterThanGame2(player1, game1, game2));
    }

    @Test
    public void winIsBetterThanDraw(){
        Player player1=new Player("ilias");
        Player player2=new Player("giannis");

        Game game1=new Game(player1,player2);
        Game game2=new Game(player1,player2);

        game1.winner=player1;
        game2.draw=true;

        assertTrue(Game.game1BetterThanGame2(player1, game1, game2));
    }

    @Test
    public void drawIsBetterThanLoss(){
        Player player1=new Player("ilias");
        Player player2=new Player("giannis");

        Game game1=new Game(player1,player2);
        Game game2=new Game(player1,player2);

        game1.draw=true;
        game2.winner=player2;

        assertTrue(Game.game1BetterThanGame2(player1,game1,game2));
    }

    @Test
    public void lossIsWorseThanWin(){
        Player player1=new Player("ilias");
        Player player2=new Player("giannis");

        Game game1=new Game(player1,player2);
        Game game2=new Game(player1,player2);

        game1.winner=player2;
        game2.winner=player1;

        assertFalse(Game.game1BetterThanGame2(player1,game1,game2));
    }
    @Test
    public void lossIsWorseThanDraw(){
        Player player1=new Player("ilias");
        Player player2=new Player("giannis");

        Game game1=new Game(player1,player2);
        Game game2=new Game(player1,player2);

        game1.winner=player2;
        game2.draw=true;

        assertFalse(Game.game1BetterThanGame2(player1,game1,game2));
    }

    @Test
    public void sameResultBetterOpponent(){
        Player player1=new Player("ilias");

        Player player2=new Player("giannis");
        player2.setGamesPlayed(10);
        player2.setWins(1);

        Player player3=new Player("babis");
        player3.setGamesPlayed(10);
        player3.setWins(3);

        Game game1=new Game(player1,player2);
        Game game2=new Game(player1,player3);


        game1.draw=true;
        game2.draw=true;

        assertFalse(Game.game1BetterThanGame2(player1,game1,game2));
    }

    @Test
    public void sameResultSameOpponentDifferentTime(){
        Player player1=new Player("ilias");

        Player player2=new Player("giannis");
        player2.setGamesPlayed(10);
        player2.setWins(3);

        Player player3=new Player("babis");
        player3.setGamesPlayed(10);
        player3.setWins(3);

        Game game1=new Game(player1,player3);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Game game2=new Game(player1,player2);

        game1.draw=true;
        game2.draw=true;

        assertFalse(Game.game1BetterThanGame2(player1,game1,game2));
    }
}