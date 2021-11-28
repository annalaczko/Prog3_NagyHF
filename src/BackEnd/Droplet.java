package BackEnd;

import GUI.DropletGUI;
import GUI.GUI;

import java.io.FileNotFoundException;
import java.util.Random;

/**
 * Class for droplets, and its behaviour
 */
public class Droplet {
    private double coorX;
    private double coorY;
    private double bonus;
    private final Game game;
    private DropletGUI dropletGUI;

    public Droplet(Game game) throws FileNotFoundException {
        this.game=game;
        coorX=game.getCrash().getSafeCoorX();
        coorY=game.getCrash().getSafeCoorY();
        dropletGUI=new DropletGUI(coorX-40, coorY-40);
        dropletGUI.loadPicture();
        dropletGUI.updatePicture(coorX- GUI.getFieldSize()/2, coorY- GUI.getFieldSize()/2);
        bonus=new Random().nextDouble()+1;
    }

    /**
     * droplets gets new coordinates, and updates visual
     */
    public void reappear(){
        coorX=game.getCrash().getSafeCoorX();
        coorY=game.getCrash().getSafeCoorY();
        bonus=new Random().nextDouble()+1;
        dropletGUI.updatePicture(coorX- GUI.getFieldSize()/2, coorY- GUI.getFieldSize()/2);
        dropletGUI.readdPicture();
    }

    /**
     * getters
     * @return
     */

    public double getCoorX() {
        return coorX;
    }

    public double getCoorY() {
        return coorY;
    }

    public double getBonus() {
        return bonus;
    }
}