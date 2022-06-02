import Model.Board;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    @Test
    public void isAvailableWorksIfEmpty(){

        char[][] board={{ ' ', ' ', ' ' },
                        { ' ', ' ', ' ' },
                        { ' ', ' ', ' ' }};
        Assertions.assertTrue(Board.isAvailable(board,1,1));
    }

    @Test
    public void isAvailableWorksForIfNotEmpty(){

        char[][] board={{ 'X', ' ', ' ' },
                        { ' ', ' ', ' ' },
                        { ' ', ' ', ' ' }};
        assertFalse(Board.isAvailable(board,0,0));
    }

    @Test
    public void gameWonWorks(){

        char[][] board={{ ' ', ' ', ' ' },
                        { ' ', ' ', ' ' },
                        { ' ', ' ', ' ' }};
        assertEquals(0,Board.checkIfWon(board,'X'));
    }

    @Test
    public void gameWonWorksForX(){

        char[][] board={{ 'X', 'X', 'X' },
                        { 'O', 'O', ' ' },
                        { ' ', 'O', ' ' }};
        assertEquals(10,Board.checkIfWon(board,'X'));
    }

    @Test
    public void gameWonWorksForO(){

        char[][] board={{ 'O', 'O', 'O' },
                        { 'X', 'X', ' ' },
                        { ' ', 'X', ' ' }};
        assertEquals(-10,Board.checkIfWon(board,'X'));
    }

    @Test
    public void fullBoardIsRecognized(){

        char[][] board={{ 'X', 'O', 'X' },
                        { 'X', 'O', 'O' },
                        { 'O', 'X', 'X' }};
        assertFalse(Board.hasMovesLeft(board));
    }
    @Test
    public void notFullBoardIsRecognized(){

        char[][] board={{ 'X', ' ', ' ' },
                        { 'X', 'O', ' ' },
                        { 'O', ' ', ' ' }};
        assertTrue(Board.hasMovesLeft(board));
    }
}

