package BackEnd;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Tester Class for the class Crash
 */

public class CrashTest {
    Game game;
    int fieldSize;

    @Before
    public void setUp() throws Exception {
        game= new Game(null, null);
        game.newMazeSetUp();
        fieldSize=100;
    }

    /**
     * Tests for safe coordinates inside of the Maze
     */
    @Test
    public void getSafeCoorXTest(){
        boolean value=game.getCrash().getSafeCoorX()>162&&game.getCrash().getSafeCoorX()<1203;
        Assert.assertTrue(value);
    }

    @Test
    public void getSafeCoorYTest(){
        boolean value=game.getCrash().getSafeCoorY()>68&&game.getCrash().getSafeCoorY()<568;
        Assert.assertTrue(value);
    }

}