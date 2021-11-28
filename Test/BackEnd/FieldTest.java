package BackEnd;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Tester class for the class Field
 */

public class FieldTest {

    Field field;

    @Before
    public void setUp(){
        field=new Field();
    }

    /**
     * tests the getter
     */

    @Test
    public void getTypeTest() {
        field.removeWall(Direction.east);
        field.removeWall(Direction.north);
        Assert.assertEquals(3, field.getType(),0.0);
    }
}