package BackEnd;

/**
 * class for one pixel (in class crash i have a big array for this)
 */

public class SolidWall {
    private final int coorX;
    private final int coorY;

    public SolidWall(int x, int y){
        coorX=x;
        coorY=y;
    }

    /**
     * getters
     * @return
     */
    public int getCoorY() {
        return coorY;
    }

    public int getCoorX() {
        return coorX;
    }
}