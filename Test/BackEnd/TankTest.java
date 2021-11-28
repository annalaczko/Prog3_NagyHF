package BackEnd;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Tester class for the class Tank
 */


public class TankTest {
    Tank tank;
    double x;
    double y;

    @Before
    public void setUp() throws Exception {
        Game game=new Game(null, null);
        game.newMazeSetUp();
        tank=new Tank(game,0);
    }

    /**
     * Tests for getters and setters
     */

    @Test
    public void getCoorXTest(){
        tank.setCoorX(x);
        Assert.assertEquals(tank.getCoorX(), x, 0);
    }

    @Test
    public void getCoorYTest(){
        tank.setCoorY(y);
        Assert.assertEquals(tank.getCoorY(), y, 0);
    }

    @Test
    public void setAliveTest(){
        tank.die();
        tank.setAlive(true);
        Assert.assertTrue(tank.isAlive());
    }

    /**
     * Test for checking method die()
     */

    @Test
    public void dieTest() {
        tank.die();
        Assert.assertFalse(tank.isAlive());

    }

    /**
     * Testing the methods for moving and rotating the tank
     */

    @Test
    public void moveTest() {
        tank.setCoorX(x);
        tank.setCoorY(y);
        for (int i=0; i<4; i++){
            tank.setMovement(i, false);
        }
        tank.update();
        Assert.assertEquals(tank.getCoorX(),x,0.0);
        Assert.assertEquals(tank.getCoorY(),x,0.0);

        tank.setMovement(1, true);
        tank.update();
        if (tank.getDirectionDegrees()%90!=0){
            Assert.assertNotEquals(tank.getCoorX(),x,0.0);
            Assert.assertNotEquals(tank.getCoorY(),x,0.0);
        }

        tank.setMovement(3, true);
        tank.update();
        if (tank.getDirectionDegrees()%90!=0){
            Assert.assertNotEquals(tank.getCoorX(),x,0.0);
            Assert.assertNotEquals(tank.getCoorY(),x,0.0);
        }
    }

    @Test
    public void rotateTest() {
        double degrees=tank.getDirectionDegrees();
        tank.setMovement(0, true);
        tank.update();
        Assert.assertNotEquals(tank.getDirectionDegrees(),degrees);
    }

}