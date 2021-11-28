package BackEnd;

/**
 * class for the maze's pieces called fields
 */

public class Field {
    /**
     * the 4 boolean are the main directions on the compass
     */
    private boolean n;
    private boolean e;
    private boolean s;
    private boolean w;

    public Field() {
        n=true; e=true; s=true; w=true;
    }

    /**
     * removes one wall
     * @param wall the direction where we need to remove the wall
     */

    public void removeWall(Direction wall) {
        switch(wall) {
            case north:
                n = false;
                break;
            case east:
                e = false;
                break;
            case south:
                s = false;
                break;
            case west:
                w = false;
                break;
            default:
                break;
        }
    }

    /**
     * special getter
     * @return with a int called "type" which determined by the walls.
     * there's 16 different options so the value is between (included both borders) 0 and 15
     */
    public int getType(){
        int type=0;
        if (getWall(Direction.north))  type+=8;
        if (getWall(Direction.east))  type+=4;
        if (getWall(Direction.south))    type+=2;
        if (getWall(Direction.west)) type+=1;
        return type;
    }

    /**
     * @param wall direction of the wall
     * @return true if there's a wall
     */
    public boolean getWall(Direction wall) {
        return switch (wall) {
            case north -> n;
            case east -> e;
            case south -> s;
            case west -> w;
        };
    }
}