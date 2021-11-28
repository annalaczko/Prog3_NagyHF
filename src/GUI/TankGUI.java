package GUI;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * class for the tank's visual appearance
 */

public class TankGUI extends GUImovingThings {

    private int id;

    public TankGUI(double x, double y, int id) throws FileNotFoundException {
        super(x, y);
        this.id=id;
        importPicture();
    }

    /**
     * imports picture from file
     */
    @Override
    public void importPicture() throws FileNotFoundException{
        view=new ImageView(new Image(new FileInputStream("resources\\tank"+id+".png")));
    }

    /**
     * updates the position of the picture
     * @param x coordinate
     * @param y coordinate
     * @param degree for rotation
     */
    public void updatePicture(double x, double y, double degree){
        coorX=x;
        coorY=y;
        view.relocate(coorX,coorY);
        view.setRotate(degree);
    }
}