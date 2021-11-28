package GUI;

import javafx.scene.layout.Pane;

/**
 *SuperClass for every visual class
 */
public  abstract class GUI {
    protected static Pane root;
    protected double coorX;
    protected double coorY;
    protected static int fieldSize=80;

    public GUI(double x, double y){
        coorX=x;
        coorY=y;
    }

    /**
     * getter and setter
     * @return
     */

    public static int getFieldSize() {
        return fieldSize;
    }

    public static void setRoot(Pane root) {
        GUI.root = root;
    }
}
