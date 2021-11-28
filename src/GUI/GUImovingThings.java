package GUI;

import javafx.scene.image.ImageView;

import java.io.FileNotFoundException;

/**
 * class for visual appearance
 */

public abstract class GUImovingThings extends GUI {
    protected double coorX;
    protected double coorY;
    protected ImageView view;
    private int fieldSize=80;

    public GUImovingThings(double x, double y) throws FileNotFoundException {
        super(x,y);
        importPicture();
    }

    /**
     * imports picture from file
     */
    public abstract void importPicture() throws FileNotFoundException;

    /**
     * adds picture to root
     */
    public void loadPicture(){
        view.relocate(coorX, coorY);
        view.setFitWidth(fieldSize);
        view.setFitHeight(fieldSize);
        root.getChildren().add(view);
    }

    /**
     * updates the position of the picture
     * @param x coordinate
     * @param y coordinate
     */
    public void updatePicture(double x, double y){
        coorX=x;
        coorY=y;
        view.relocate(coorX,coorY);
    }

    /**
     * sets the object's picture value to null
     */
    public void removePicture() {
        root.getChildren().remove(view);
        view=null;
    }
}
