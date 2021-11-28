package GUI;

import BackEnd.Main;
import Buttons.MenuButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * class for menu
 */
public class MenuGUI {
    private final Main main;
    private final Stage primaryStage;

    public class NewGameHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent actionEvent) {
            main.getNewGameWindow().start();
        }
    }
    public class LoadGameHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent actionEvent) {
            try {
                main.getLoadGameWindow().start();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    public class ClosedBattlesHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent actionEvent) {
            try {
                main.getClosedBattlesWindow().start();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
    }
    public static class ExitHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent actionEvent) {
            System.exit(0);
        }
    }

    public Main getMain(){
        return main;
    }


    public void start() throws Exception {
        primaryStage.setScene(createMenuScene());
        primaryStage.show();
    }

    public MenuGUI(Main main, Stage primaryStage) throws IOException, ClassNotFoundException {
        this.primaryStage=primaryStage;
        this.main=main;
    }

    /**
     * returns with the scene for menu
     */
    public Scene createMenuScene () throws FileNotFoundException {
        MenuButton[] menuButtons = new MenuButton[4];
        Pane root = new Pane();
        BackgroundImage hatter= new BackgroundImage (new Image(new FileInputStream("resources\\hatter.png")),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        root.setBackground(new Background(hatter));
        root.setMaxSize(1365,768);
        root.setMinSize(1365, 768);
        root.setPrefSize(1365, 768);
        for (int i=0; i<4; i++){
            menuButtons[i]=new MenuButton(i ,532, 380+i*90);
            root.getChildren().add(menuButtons[i]);
        }
        menuButtons[0].setOnAction(new NewGameHandler());
        menuButtons[1].setOnAction(new LoadGameHandler());
        menuButtons[2].setOnAction(new ClosedBattlesHandler());
        menuButtons[3].setOnAction(new ExitHandler());
        return new Scene(root, 1365,768);
    }

}