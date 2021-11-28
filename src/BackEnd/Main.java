package BackEnd;

import GUI.*;
import Windows.ClosedBattlesWindow;
import Windows.GameOverWindow;
import Windows.LoadGameWindow;
import Windows.NewGameWindow;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main Class it starts the game, and instantiate the most important objects
 */

public class Main extends Application {
    private MenuGUI menu;
    private Game game;
    private GameOverWindow gameOverWindow;
    private ClosedBattlesWindow closedBattlesWindow;
    private LoadGameWindow loadGameWindow;
    private NewGameWindow newGameWindow;

    /**
     * overridden start method for starting the game
     * @param primaryStage main window
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Stage secondaryStage = new Stage();
        primaryStage.setResizable (false);
        primaryStage.setTitle("Tank Trouble");
        menu=new MenuGUI(this,primaryStage);
        game=new Game(this, primaryStage);
        gameOverWindow=new GameOverWindow(game,secondaryStage);
        closedBattlesWindow=new ClosedBattlesWindow(secondaryStage);
        loadGameWindow=new LoadGameWindow(menu,secondaryStage );
        newGameWindow= new NewGameWindow(menu, secondaryStage);
        menu.start();
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * getters
     */

    public Game getGame() {
        return game;
    }

    public MenuGUI getMenu() {
        return menu;
    }

    public ClosedBattlesWindow getClosedBattlesWindow() {
        return closedBattlesWindow;
    }

    public GameOverWindow getGameOverWindow() {
        return gameOverWindow;
    }

    public LoadGameWindow getLoadGameWindow() {
        return loadGameWindow;
    }

    public NewGameWindow getNewGameWindow() {
        return newGameWindow;
    }

}