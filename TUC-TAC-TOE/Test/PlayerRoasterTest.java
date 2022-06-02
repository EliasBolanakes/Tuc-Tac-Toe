import Model.PlayerRoaster;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerRoasterTest {

    @Test
    public void whitespaceInBeginningAndEndIsFalse(){

        PlayerRoaster playerRoaster=new PlayerRoaster();
        assertFalse(playerRoaster.validName(" ilias "));
    }

    @Test
    public void whitespaceInBeginningIsFalse(){

        PlayerRoaster playerRoaster=new PlayerRoaster();
        assertFalse(playerRoaster.validName(" markos"));
    }

    @Test
    public void whitespaceInEndIsFalse(){

        PlayerRoaster playerRoaster=new PlayerRoaster();
        assertFalse(playerRoaster.validName("ilias "));
    }

    @Test
    public void noProblemWithSpaceInBetween(){

        PlayerRoaster playerRoaster=new PlayerRoaster();
        assertTrue(playerRoaster.validName("ilias bolanakis"));
    }

    @Test
    public void cantAddPlayerWithExistingName(){

        PlayerRoaster playerRoaster=new PlayerRoaster();
        playerRoaster.addPlayer("ilias");
        assertFalse(playerRoaster.addPlayer("ilias"));
    }

    @Test
    public void cantAddPlayerWithSpaceInStartOrEnd(){

        PlayerRoaster playerRoaster=new PlayerRoaster();
        assertFalse(playerRoaster.addPlayer(" ilias"));
    }

    @Test
    public void cantAddPlayerWithLongerThanAllowedName(){

        PlayerRoaster playerRoaster=new PlayerRoaster();
        assertFalse(playerRoaster.addPlayer("qwertyuiopasdfghjklzxcvbnm"));
    }
}