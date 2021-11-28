package GUI;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * GUI class for droplets
 */

public class DropletGUI extends GUImovingThings {

    public DropletGUI(double x, double y) throws FileNotFoundException {
        super(x, y);
    }

    /**
     * imports picture from file
     */
    @Override
    public void importPicture() throws FileNotFoundException {
        view= new ImageView(new Image(new FileInputStream("resources\\drop.png")));
    }

    /**
     * deletes picture  from root and then readds (so the picture is on top of everything else)
     */
    public void readdPicture(){
        root.getChildren().remove(view);
        root.getChildren().add(view);
    }
}
