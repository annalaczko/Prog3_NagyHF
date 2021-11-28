package BackEnd;

import GUI.BulletGUI;

/**
 * Backend class for the bullet appearing on screen. Methods for moving and for ricochet
 */

public class Bullet {
    private double coorX;
    private double coorY;
    private double degrees;
    private final double radius;
    private final double velocity;
    private int timeToLive=800;
    private final Game game;
    private static int bulletsCount=0;
    private final int bullet_id;
    private final Tank tank;
    private BulletGUI bulletGUI;


    public Bullet(double x, double y, double degrees, double velocity,int tankradius, double radius, Game game, Tank tank){
        this.tank=tank;
        this.game=game;
        this.degrees=degrees;
        this.radius=radius;
        this.velocity=velocity;
        bullet_id=bulletsCount++;
        coorX= x+Math.cos(Math.toRadians(degrees)) * tankradius*1.41;
        coorY= y+Math.sin(Math.toRadians(degrees)) * tankradius*1.41;
        bulletGUI=new BulletGUI(coorX, coorY, radius);
    }

    /**
     * Public method for updating the bullet's state
     * @param diedBullets during a "for" cycle in class Game the number of bullets can lessen and it could screw up the indexes
     */
    public void update(int diedBullets){
        move();
        bulletGUI.updatePicture(coorX, coorY);
        game.getCrash().bulletOutOfMaze(this,diedBullets);

        timeToLive--;
        if (timeToLive==0){
            die(diedBullets);
        }

    }

    /**
     * Method for handling the bullet dying
     * @param diedBullets same parameter as in update()
     */

    public void die(int diedBullets){
        int idx=-1;
        for (int i=0; i<game.getBullets().size(); i++){
            if (game.getBullets().get(i).getBullet_id()==bullet_id){
                idx=i;
            }
        }
        if(idx<0){
            System.out.println("HIBA BULLET.DIE()");
        }
        bulletGUI.removePicture();
        game.getBullets().remove(idx);
        diedBullets++;
        tank.setBulletCount();
    }

    /**
     * Method for handling direction change if the bullet hits the wall
     */

    private void ricochet(){
        boolean [] vertical_horizontal =game.getCrash().checkBulletWallCrash((int)coorX,(int)coorY,radius);
        if (vertical_horizontal[0]&&vertical_horizontal[1]){
            degrees+=180;
        } else if(vertical_horizontal[0]){ //vertical
            if (degrees==90||degrees==270){
                degrees+=180;
            } else {
                degrees*=-1;
            }
        }else if(vertical_horizontal[1]){ //horizontal
            if (degrees==0||degrees==180){
                degrees+=180;
            } else {
                degrees=180-degrees;
            }
        }
        vertical_horizontal[0]=false;
        vertical_horizontal[1]=false;
        if (degrees <0) degrees +=360;
        if (degrees >360) degrees -=360;
    }

    /**
     * Method for moving the bullet
     */

    private void move(){
        for (int i=0; i<velocity; i++) {
            coorX += Math.cos(Math.toRadians(degrees));
            coorY += Math.sin(Math.toRadians(degrees));
            ricochet();
        }

    }


    /**
     * Only getters methods from here
     * @return returning value for the getter methods
     */

    public int getBullet_id() {
        return bullet_id;
    }

    public double getCoorX() {
        return coorX;
    }

    public double getCoorY() {
        return coorY;
    }
}