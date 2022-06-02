import Model.PerfectPlayer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PerfectPlayerTest {
    @Test
    public void perfectPlayersAlwaysChoosesWellRow(){

        char[][] board={{ 'X', 'X', ' ' },
                        { ' ', 'O', ' ' },
                        { ' ', ' ', ' ' }};

        PerfectPlayer perfectPlayer=new PerfectPlayer("Hal");
        assertEquals(0,perfectPlayer.findBestMove(board,'X').getRow());
    }
    @Test
    public void perfectPlayersAlwaysChoosesWellCol(){

        char[][] board={{ 'X', 'X', ' ' },
                        { ' ', 'O', ' ' },
                        { ' ', ' ', ' ' }};

        PerfectPlayer perfectPlayer=new PerfectPlayer("Hal");
        assertEquals(2,perfectPlayer.findBestMove(board,'O').getCol());
    }
    @Test
    public void perfectPlayersAlwaysChoosesWellRow2(){

        char[][] board={{ 'X', 'X', 'O' },
                        { 'X', 'O', ' ' },
                        { ' ', ' ', ' ' }};

        PerfectPlayer perfectPlayer=new PerfectPlayer("Hal");
        assertEquals(2,perfectPlayer.findBestMove(board,'O').getRow());
    }
    @Test
    public void perfectPlayersAlwaysChoosesWellCol2(){

        char[][] board={{ 'X', 'X', 'O' },
                        { 'X', 'O', ' ' },
                        { ' ', ' ', ' ' }};

        PerfectPlayer perfectPlayer=new PerfectPlayer("Hal");
        assertEquals(2,perfectPlayer.findBestMove(board,'O').getRow());
    }
}