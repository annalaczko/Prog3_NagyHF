package BackEnd;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Tester class for the class Maze
 */

@RunWith(Parameterized.class)
public class MazeTest {
    Maze maze;
    int height;
    int length;

    public MazeTest(int height, int length){
        this.height=height;
        this.length=length;
    }

    @Before
    public void setUp() throws FileNotFoundException {
        maze=new Maze();
        maze.generate();
    }

    /**
     * Tests getter methods
     */

    @Test
    public void getHeightTest(){
        Assert.assertTrue(maze.getHeight()<9&&maze.getHeight()>0);
    }

    @Test
    public void getLengthTest(){
        Assert.assertTrue(maze.getLength()<16&&maze.getLength()>0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getFieldTest() {
        maze.getField(height+1, length+1);
    }

    @Parameterized.Parameters
    public static List<Object[]> parameters(){
        List<Object[]> params=new ArrayList<>();
        params.add(new Object[]{7,9});
        params.add(new Object[]{6,5});
        params.add(new Object[]{8,3});
        params.add(new Object[]{12,5});
        return params;
    }


}