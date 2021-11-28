package BackEnd;

import GUI.GUI;
import GUI.TankGUI;

import java.io.FileNotFoundException;
import java.util.Random;
import java.lang.Math;

/**
 * class for the behaviour of the tank
 */
public class Tank {
    private final int player_id;
    private double directionDegrees;
    private double coorX;
    private double coorY;
    private final double velocity;
    private final boolean [] movements;
    private final Game game;
    private boolean alive;
    private double bonus;
    private int bulletCount;
    private TankGUI tankGUI;

    public Tank(Game game, int player_id) throws FileNotFoundException {
        bulletCount=1;
        alive=true;
        bonus=1.5;
        this.game=game;
        this.player_id=player_id;
        velocity=3;
        directionDegrees = new Random().nextInt(360);
        movements =new boolean[4];
        tankGUI=new TankGUI(0,0, player_id);
        tankGUI.loadPicture();
    }

    /**
     * updates the tank's state
     */
    public void update(){
        move();
        rotate();
        tankGUI.updatePicture(coorX- GUI.getFieldSize()/2, coorY- GUI.getFieldSize()/2, directionDegrees);
    }

    /**
     * handles the tank dieing
     */
    public void die (){
        alive=false;
        tankGUI.removePicture();
    }

    /**
     * moves the tank
     */

    private void move(){

        double coorXtemp=coorX, coorYtemp=coorY;
        if (movements[1]) {
            coorXtemp+=Math.cos(Math.toRadians(directionDegrees)) * velocity;
            coorYtemp+=Math.sin(Math.toRadians(directionDegrees)) * velocity;
        }
        if (movements[2]) {
            coorXtemp-=Math.cos(Math.toRadians(directionDegrees))*velocity;
            coorYtemp-=Math.sin(Math.toRadians(directionDegrees))*velocity;
        }
        boolean xy=game.getCrash().checkTankWallCrash(coorXtemp, coorYtemp, Game.getGameGUI().getFieldSize()/5*2);
        if (!xy) {
            coorX = coorXtemp;
            coorY = coorYtemp;
        }
        game.getCrash().dropletReached(this, Game.getGameGUI().getFieldSize()/5*2);
    }

    /**
     * rotates the tank
     */
    private void rotate(){
        if(movements[0]) {
            directionDegrees -= velocity*1.25;
            if (directionDegrees <0) directionDegrees +=360;
        }
        if(movements[3]) {
            directionDegrees += velocity*1.25;
            if (directionDegrees >360) directionDegrees -=360;
        }
    }

    /**
     * shoots one bullet
     */
    public void shoot(){
        if (bulletCount>0){
            game.getBullets().add(new Bullet(coorX,coorY, directionDegrees,velocity*bonus, Game.getGameGUI().getFieldSize()/5 ,6.0/bonus, game, this));
            bonus=1.5;
            bulletCount--;
        }

    }

    /**
     * getters and setters
     * @return
     */

    public int getPlayer_id() {
        return player_id;
    }

    public void setMovement(int idx, boolean value) {
        movements[idx]=value;
    }

    public void setCoorX(double coorX) {
        this.coorX = coorX;
    }

    public void setCoorY(double coorY) {
        this.coorY = coorY;
    }

    public double getCoorX() {
        return coorX;
    }

    public double getCoorY() {
        return coorY;
    }

    public double getDirectionDegrees() {
        return directionDegrees;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public void setBulletCount(){
        bulletCount++;
    }
}