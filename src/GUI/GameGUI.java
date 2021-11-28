package GUI;

import BackEnd.Game;
import BackEnd.Main;
import Buttons.GameButton;
import KeyEventHandlers.PressKeyHandler;
import KeyEventHandlers.ReleaseKeyHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * visual game window
 */
public class GameGUI {
    private final Pane root;
    private final int fieldSize;
    private ArrayList<ImageView> playersTankView;
    private ArrayList<Label> playersNameField;
    private final ArrayList<Label> playersPoints= new ArrayList<>();
    private final Game game;
    private final Stage primaryStage;
    private final Main main;
    private final Scene gameScene;

    /**
     * inner classes for button action
     */
    public class InterruptButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent actionEvent) {
            game.setGameOver(true);
            game.setClosedGame(false);
            try {
                main.getMenu().start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public class CloseButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent actionEvent) {
            game.setGameOver(true);
            game.setClosedGame(true);
            try {
                main.getMenu().start();
                main.getGameOverWindow().start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public GameGUI(Game game, Main main, Stage primaryStage) throws IOException{
        this.main=main;
        this.primaryStage=primaryStage;
        this.game=game;
        fieldSize=80;
        root=backgroundLoad();
        gameScene = new Scene(root);
        GUI.setRoot(root);
        if (primaryStage!=null){
            GameButton interrupt = new GameButton("Interrupt", 582, 620);
            GameButton close = new GameButton("Close", 582, 690);
            root.getChildren().addAll(interrupt, close);
            interrupt.setOnAction(new InterruptButtonHandler());
            close.setOnAction(new CloseButtonHandler());
            gameScene.setOnKeyReleased(new ReleaseKeyHandler(game));
            gameScene.setOnKeyPressed(new PressKeyHandler(game));
            setPlayerPoints();
            setPlayerNamesAndPictures();
        }
    }

    /**
     * starts game window
     */
    public void start() {
        updatePlayerPoints();
        updatePlayerNames();
        if (primaryStage!=null){
            primaryStage.setScene(gameScene);
        }

    }

    /**
     * sets root dimensions
     * @return
     * @throws FileNotFoundException
     */
    private Pane backgroundLoad() throws FileNotFoundException {
        Pane root=new Pane();
        BackgroundImage background = new BackgroundImage(new Image(new FileInputStream("resources\\hattersima.png")),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        root.setBackground(new Background(background));
        root.setMaxSize(1365,768);
        root.setMinSize(1365, 768);
        root.setPrefSize(1365, 768);
        return root;
    }

    /**
     * setters and updates for refreshing the appearing data on game window
     * @throws FileNotFoundException
     */

    public void setPlayerNamesAndPictures() throws FileNotFoundException {
        playersNameField= new ArrayList<>();
        playerTankImport();
        for (int i =0; i<2; i++){
            playersTankView.get(i).setPreserveRatio(true);
            playersTankView.get(i).setFitHeight(150);
            playersTankView.get(i).relocate(20+i*1175, 548);
            Label name= new Label(game.getBattleData().getNames().get(i));
            name.relocate(20+i*1175, 698);
            name.setPrefSize(150, 50);
            name.setStyle("-fx-font-size:30; " +
                    "-fx-font-weight: bold; " +
                    "-fx-text-fill: #666666;  " +
                    "-fx-font: 30px Verdana");
            playersNameField.add(name);
            root.getChildren().addAll(playersTankView.get(i), playersNameField.get(i));
        }
    }

    public void updatePlayerNames(){
        playersNameField.get(0).setText(game.getBattleData().getNames().get(0));
        playersNameField.get(1).setText(game.getBattleData().getNames().get(1));
    }

    public void setPlayerPoints() throws FileNotFoundException {
        playerTankImport();
        for (int i =0; i<2; i++){
            playersPoints.add(new Label(Integer.toString(game.getBattleData().getPoints()[i])));
            playersPoints.get(i).relocate(200+i*815, 668);
            playersPoints.get(i).setPrefSize(150, 50);
            playersPoints.get(i).setStyle("-fx-font-size:30; " +
                    "-fx-font-weight: bold; " +
                    "-fx-text-fill: #666666;  " +
                    "-fx-font: 30px Verdana; text-align: center;"
            );
            root.getChildren().addAll(playersPoints.get(i));
        }
    }

    public void updatePlayerPoints() {
        for (int i =0; i<2; i++){
            playersPoints.get(i).setText(Integer.toString(game.getBattleData().getPoints()[i]));
        }
    }

    private void playerTankImport() throws FileNotFoundException {
        playersTankView = new ArrayList<>();
        playersTankView.add(new ImageView(new Image(new FileInputStream("resources\\newtank0.png"))));
        playersTankView.add(new ImageView(new Image(new FileInputStream("resources\\newtank1.png"))));
    }

    /**
     * getter
     * @return
     */
    public int getFieldSize() {
        return fieldSize;
    }
}