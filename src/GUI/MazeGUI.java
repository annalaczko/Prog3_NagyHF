package GUI;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class MazeGUI extends GUI {
    private final ArrayList<Image> fieldsImage;
    private ImageView [] [] mazeView;
    private int height;
    private int length;

    public MazeGUI(double x, double y) throws FileNotFoundException {
        super(x,y);
        height=0;
        length=0;
        mazeView=new ImageView[0][0];
        fieldsImage= new ArrayList<>();
        importPictures();
    }

    /**
     * imports pictures from file to an ArrayList
     */
    private void importPictures() throws FileNotFoundException {
        for (int type=0; type<15; type++){
            fieldsImage.add(new Image(new FileInputStream("resources\\mezo"+type+".png")));
        }
    }

    /**
     * adds pictures to root
     * updates the position of the picture
     * @param i row
     * @param j column
     * @param type: field type. In theory there are 16 types, but only 15 of it can appear
     */

    private void updatePicture(int i, int j, int type){
        mazeView[i][j]=new ImageView(fieldsImage.get(type));
        mazeView[i][j].relocate(coorX+j*fieldSize, coorY+i*fieldSize);
        mazeView[i][j].setFitHeight(fieldSize);
        mazeView[i][j].setFitWidth(fieldSize);
        root.getChildren().add(mazeView[i][j]);
    }

    /**
     * main update method
     * @param coorXbegin
     * @param coorYbegin
     * @param height
     * @param length
     * @param types
     */

    public void update(double coorXbegin, double coorYbegin, int height, int length, int [][] types){
        removePictures();
        coorX=coorXbegin;
        coorY=coorYbegin;
        this.height=height;
        this.length=length;
        mazeView= new ImageView[height][length];
        for (int i=0; i<height; i++){
            for (int j=0; j<length; j++){
                updatePicture(i,j, types[i][j]);
            }
        }
    }

    /**
     * sets the object's picture value to null
     * removes from root
     */
    public void removePictures() {
        for (int i=0; i<height; i++){
            for (int j=0; j<length; j++){
                root.getChildren().remove(mazeView[i][j]);
                mazeView[i][j]=null;
            }
        }
    }

}
