package GUI;

import javafx.scene.shape.Circle;

/**
 * Class for bullets' visual appearance
 */
public class BulletGUI extends GUI {
    private Circle bullet;
    private double radius;

    public BulletGUI(double x, double y, double radius) {
        super(x, y);
        this.radius=radius;
        loadPicture();
    }

    /***
     * loads a new circle
     */

    private void loadPicture(){
        bullet=new Circle(coorX,coorY, radius);
        root.getChildren().add(bullet);
    }

    /**
     * updates coordinates
     * @param x
     * @param y
     */

    public void updatePicture(double x, double y){
        coorX=x;
        coorY=y;
        bullet.relocate(coorX,coorY);
    }

    /**
     * removes the pictures
     */
    public void removePicture(){
        root.getChildren().remove(bullet);
        bullet=null;
    }

}
