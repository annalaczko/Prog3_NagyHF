package BackEnd;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class for making the maze solid, and because of that handling the moving objects interaction with the walls
 */

public class Crash {
    private int mazeCoorXbegin;
    private int mazeCoorYbegin;
    private int mazeCoorXend;
    private int mazeCoorYend;
    private int fieldSize;
    private final Game game;
    private ArrayList<SolidWall> walls;

    public Crash(Game game, int fieldSize){
        this.game=game;
        this.fieldSize=fieldSize;
        mazeCoorXbegin=0;
        mazeCoorYbegin=0;
        mazeCoorXend=0;
        mazeCoorYend=0;
        //initWalls();
    }

    /**
     * updating the walls and the coordinates, if a new maze is loaded
     */

    public void update(){
        mazeCoorXbegin=(1365-game.getMaze().getLength()*fieldSize)/2;
        mazeCoorYbegin=68+(500-game.getMaze().getHeight()*fieldSize)/2;
        mazeCoorXend=1362-mazeCoorXbegin;
        mazeCoorYend=765-mazeCoorYbegin+68-200;
        initWalls();
    }

    /**
     * Deletes the "walls" ArrayList
     */

    public void removeSolidWalls(){
        walls.clear();
    }

    /**
     * initiates the walls ArrayList
     */

    private void initWalls(){
        int type;
        walls= new ArrayList<>();
        for (int i=0; i<game.getMaze().getHeight(); i++){
            for (int j=0; j<game.getMaze().getLength(); j++){
                type=game.getMaze().getField(i,j).getType();

                if (type /8==1){
                    for (int width=0; width<3; width++) {
                        for (int x = 0; x < fieldSize; x++) {
                            walls.add(new SolidWall(x+mazeCoorXbegin+j*fieldSize, mazeCoorYbegin+fieldSize*i+width));
                        }
                    }
                    type-=8;
                }

                if (type /4==1){
                    for (int y = 0; y < fieldSize; y++) {
                        for (int width=0; width<3; width++) {
                            walls.add(new SolidWall(mazeCoorXbegin+fieldSize*(j+1)-width-1, mazeCoorYbegin+fieldSize*i+y));
                        }
                    }
                    type-=4;
                }

                if (type /2==1){
                    for (int width=0; width<3; width++) {
                        for (int x = 0; x < fieldSize; x++) {
                            walls.add(new SolidWall(x+mazeCoorXbegin+fieldSize*j, mazeCoorYbegin+fieldSize*(i+1)-width-1));
                        }
                    }
                    type-=2;
                }

                if (type==1){
                    for (int y = 0; y < fieldSize; y++) {
                        for (int width=0; width<3; width++) {
                            walls.add(new SolidWall(mazeCoorXbegin+fieldSize*j+width, mazeCoorYbegin+fieldSize*i+y));
                        }
                    }
                    type-=1;
                }

                if(type!=0){
                    System.out.println("Baj van, crashel a Crash");
                }

            }
        }
    }

    /**
     * checks so the tank cannot go through walls
     * @param x tank's x coordinate
     * @param y tank's y coordinate
     * @param tanksize the size of the tank in pixels
     * @return returns true if tank reached a wall
     */
    public boolean checkTankWallCrash(double x, double y, int tanksize){
        for(SolidWall wall: walls){
            if( reachedTank(x,y,tanksize,wall.getCoorX(), wall.getCoorY())){
                return true;
            }
        }
        return false;
    }

    /**
     * checks if the bullet hits a wall
     * @param x bullets's x coordinate
     * @param y bullet's y coordinate
     * @param radius bullet's radius
     * @return boolean 2-element array, name vertical_horizontal vertical for idx 0, horizontal for idx 1
     */
    public boolean [] checkBulletWallCrash(int x, int y, double radius){
        boolean [] xy=new boolean[2];
        for(SolidWall wall: walls){
            xy[0]= Math.abs(x - wall.getCoorX()) < radius && y == wall.getCoorY(); //vertical
            xy[1]= Math.abs(y - wall.getCoorY()) < radius && x == wall.getCoorX(); //horizontal
            if(xy[0]||xy[1]){
                return xy;
            }
        }
        return xy;
    }

    /**
     * checks if one of the bullets hits one of the tanks
     * @param tanksize size of the tank
     */

    public void checkHitByBullet(int tanksize){
        int tank=-1, bullet=-1;
        for(int i=0; i<game.getTanks().size(); i++){
            for(int j=0; j<game.getBullets().size(); j++){
                if(reachedTank(game.getTanks().get(i).getCoorX(),game.getTanks().get(i).getCoorY(),tanksize, game.getBullets().get(j).getCoorX(), game.getBullets().get(j).getCoorY())){
                    tank=i;
                    bullet=j;
                }
            }
        }
        if (tank>-1){
            game.getTanks().get(tank).die();
            game.getBullets().get(bullet).die(0);
        }
    }

    /**
     * bullet dies if somehow gets out of maze
     * @param b bullet
     * @param diedBullets its necessary for bullet.die()
     */
    public void bulletOutOfMaze(Bullet b, int diedBullets){
        if (b.getCoorX()>mazeCoorXend||b.getCoorX()<mazeCoorXbegin||b.getCoorY()>mazeCoorYend|| b.getCoorY()<mazeCoorYbegin){
            b.die(diedBullets);
        }
    }

    /**
     * checks of an object reached the tank
     * @param tankX coordinate
     * @param tankY coordinate
     * @param tanksize dize
     * @param x objects's coordinate
     * @param y object's coordinate
     * @return true if reached
     */
    private boolean reachedTank(double tankX, double tankY, int tanksize, double x, double y){
        return Math.abs(tankX - x) < tanksize / 2 && Math.abs(tankY - y) < tanksize / 2;
    }

    /**
     * checks if a tank finds a droplet
     * @param tank
     * @param tanksize
     */
    public void dropletReached(Tank tank, int tanksize){
        for (Droplet droplet: game.getDroplets()){
            if (reachedTank(tank.getCoorX(),tank.getCoorY(),tanksize,droplet.getCoorX(), droplet.getCoorY())){
                tank.setBonus(droplet.getBonus());
                droplet.reappear();
            }
        }

    }

    /**
     * special getters
     * @returns with a safe coordinate for a tank or a droplet (safe= in the maze and not in the wall)
     */

    public double getSafeCoorX(){
        return new Random().nextInt(game.getMaze().getLength())*fieldSize+fieldSize/2+mazeCoorXbegin;
    }

    public double getSafeCoorY(){
        return new Random().nextInt(game.getMaze().getHeight())*fieldSize+fieldSize/2+mazeCoorYbegin;
    }
}