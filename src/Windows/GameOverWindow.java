package Windows;

import BackEnd.Game;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Class for a secondary window, when the battle is over. Shows the results
 */

public class GameOverWindow {
    private final Stage secondaryStage;
    private final Game game;
    private final Scene gameOverScene;
    private final Label results;
    private final Label name0;
    private final Label name1;

    public GameOverWindow(Game game, Stage secondaryStage) throws IOException {
        this.game=game;
        this.secondaryStage=secondaryStage;
        name0=new Label();
        name1=new Label();
        results= new Label();
        Label gameOverLabel= new Label("Game Over");
        name0.setStyle("-fx-font-size:20; " +
                "-fx-font-weight: bold; " +
                "-fx-text-fill: #666666;  " +
                "-fx-font: 30px Verdana");
        name1.setStyle("-fx-font-size:20; " +
                "-fx-font-weight: bold; " +
                "-fx-text-fill: #666666;  " +
                "-fx-font: 30px Verdana");
        results.setStyle("-fx-font-size:20; " +
                "-fx-font-weight: bold; " +
                "-fx-text-fill: #666666;  " +
                "-fx-font: 30px Verdana");
        gameOverLabel.setStyle("-fx-font-size:30; " +
                "-fx-font-weight: bold; " +
                "-fx-text-fill: #666666;  " +
                "-fx-font: 30px Verdana");

        BorderPane borderPane=new BorderPane();
        GridPane gridPane= new GridPane();
        StackPane stackPaneBottom=new StackPane();
        StackPane stackPaneTop=new StackPane();
        borderPane.setCenter(gridPane);
        borderPane.setTop(stackPaneTop);
        borderPane.setBottom(stackPaneBottom);
        gridPane.add(tankLoad(0),0,0);
        gridPane.add(tankLoad(1),1,0);
        gridPane.add(name0,0,1);
        gridPane.add(name1,1,1);

        stackPaneBottom.getChildren().add(results);
        stackPaneTop.getChildren().add(gameOverLabel);

        gridPane.setPadding(new Insets(20 ,20,20,20));
        gameOverScene =new Scene(borderPane, 220, 250);
        secondaryStage.setTitle("Game over");
        secondaryStage.setResizable(false);

    }

    /**
     * Starts the secondary window
     */

    public void start() {
        results.setText(game.getBattleData().getPoints()[0]+"  -  "+game.getBattleData().getPoints()[1]);
        name0.setText(game.getBattleData().getNames().get(0));
        name1.setText(game.getBattleData().getNames().get(1));
        secondaryStage.setScene(gameOverScene);
        secondaryStage.show();
    }

    /**
     * only for visual purposes loads the image of one of the tanks
     * @param i tank index
     * @return
     * @throws FileNotFoundException
     */

    private ImageView tankLoad(int i) throws FileNotFoundException {
        ImageView tankView=new ImageView(new Image(new FileInputStream("resources\\newtank"+ i +".png")));
        tankView.setFitWidth(90);
        tankView.setFitHeight(90);
        return tankView;
    }
}