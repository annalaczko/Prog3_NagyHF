package BackEnd;

import Files.BattleData;
import Files.TankFile;
import GUI.GUI;
import GUI.GameGUI;
import javafx.animation.AnimationTimer;
import javafx.stage.Stage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class for handling the whole game
 */

public class Game {
    private Maze maze;
    private Crash crash;
    private static GameGUI gameGUI;
    private final ArrayList<Tank>tanks;
    private final ArrayList<Bullet> bullets;
    private final ArrayList<Droplet> droplets;
    private AnimationTimer timer;
    private boolean isGameOver;
    private boolean closedGame;
    private BattleData battleData;
    private int waitingTime;

    public Game(Main main, Stage primaryStage) throws IOException, ClassNotFoundException {
        tanks= new ArrayList<>();
        bullets= new ArrayList<>();
        droplets= new ArrayList<>();
        battleData= new BattleData(null,null,0,0);
        crash=new Crash(this, GUI.getFieldSize());
        gameGUI =new GameGUI(this, main, primaryStage);
        maze= new Maze();
    }

    /**
     * starts game
     * @throws Exception
     */
    public void start() throws Exception {
        newMazeSetUp();
        gameGUI.updatePlayerPoints();
        int dropletsCount = 2;
        for (int i = 0; i< dropletsCount; i++){
            droplets.add(new Droplet(this));
        }
        newTanksSetUp();
        gameGUI.start();
        isGameOver=false;
        timer=new AnimationTimer() {
            @Override
            public void handle(long l) {
                try {
                    if (isGameOver){
                        timer.stop();
                        gameOver();
                    } else {
                        update();
                        if (tanks.size()<2) {
                            if (waitingTime>0){
                                waitingTime--;
                            }else{
                                if (tanks.size() == 1) {
                                    battleData.winFor(tanks.get(0).getPlayer_id());
                                    tanks.get(0).die();
                                    tanks.remove(0);
                                }
                                removeBullets();
                                newMazeSetUp();
                                newTanksSetUp();
                                gameGUI.updatePlayerPoints();
                            }
                        }

                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        timer.start();

    }

    /**
     * updates in every frame
     */
    private void update() {
        int id=-1;
        for (Tank tank: tanks) {
            if (!tank.isAlive()) {
                id=tank.getPlayer_id();
            } else{
                tank.update();
            }

        }if (id!=-1) tanks.remove(id);

        int diedBullets=0;
        for (int i=0;i<bullets.size(); i++ ) { //itt kozben lehet hogy fogy a bullet szám és az megzavarja
            bullets.get(i-diedBullets).update(diedBullets);
        }
        crash.checkHitByBullet(gameGUI.getFieldSize()/5*2);
    }

    /**
     * the 2 tanks get new coordinates
     */
    private void spawn(){
        tanks.get(0).setCoorX(crash.getSafeCoorX());
        tanks.get(0).setCoorY(crash.getSafeCoorY());
        tanks.get(1).setCoorX(crash.getSafeCoorX());
        tanks.get(1).setCoorY(crash.getSafeCoorY());
    }

    /**
     * handling if the players want to close the game
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void gameOver() throws IOException, ClassNotFoundException {
        tanks.clear();
        droplets.clear();
        crash.removeSolidWalls();
        if (battleData.getPoints()[0]!=0 || battleData.getPoints()[1]!=0) {
            if(closedGame){
                ArrayList<BattleData> closedBattleData = TankFile.loadClosedBattleData();
                closedBattleData.add(battleData);
                TankFile.saveClosedBattleData(closedBattleData);
            } else {
                TankFile.saveUnclosedBattle(battleData);
            }
        }
    }

    /**
     * calls the methods connected to a new maze
     * @throws FileNotFoundException
     */

    public void newMazeSetUp() throws FileNotFoundException{
        maze.update();
        crash.update();
        waitingTime=100;
        dropletsReset();
    }

    /**
     * gives the game 2 new tanks
     * and calls spawn
     * @throws FileNotFoundException
     */

    private void newTanksSetUp() throws FileNotFoundException {
        tanks.add(new Tank(this,0));
        tanks.add(new Tank(this, 1));
        spawn();
    }

    /**
     * makes the droplets reapper
     * @throws FileNotFoundException
     */
    private void dropletsReset() throws FileNotFoundException {
        for (Droplet droplet: droplets){
            droplet.reappear();
        }
    }

    /**
     * removes all the bullets from the maze
     */
    private void removeBullets(){
        while(bullets.size()>0){
            bullets.get(0).die(0);
        }
    }

    /**
     * setters and getters
     */

    public void setBattleData(BattleData battleData) {
        this.battleData = battleData;
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public ArrayList<Tank> getTanks() {
        return tanks;
    }

    public Crash getCrash() {
        return crash;
    }

    public static GameGUI getGameGUI() {
        return gameGUI;
    }

    public Maze getMaze() {
        return maze;
    }

    public void setGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }

    public void setClosedGame(boolean closedGame) {
        this.closedGame = closedGame;
    }

    public BattleData getBattleData() {
        return battleData;
    }

    public ArrayList<Droplet> getDroplets() {
        return droplets;
    }
}