package Windows;

import Files.BattleData;
import GUI.MenuGUI;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * class for the secondary window where we can start a new battle
 */

public class NewGameWindow {
    private final Stage secondaryStage;
    private final MenuGUI menu;
    private final TextField p1;
    private final TextField p2;
    private final Scene newGameWindow;

    public class PlayButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent actionEvent) {
            try {
                menu.getMain().getGame().setBattleData(new BattleData(p1.getText(), p2.getText(),0,0));
                menu.getMain().getGame().start();
                secondaryStage.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public NewGameWindow(MenuGUI menu, Stage secondaryStage) throws FileNotFoundException {
        this.menu=menu;
        this.secondaryStage=secondaryStage;
        GridPane gridPane= new GridPane();
        gridPane.setPadding(new Insets(10 ,10,10,10));
        Button play=new Button("Play!");
        play.setOnAction(new PlayButtonHandler());
        p1=new TextField("Fire");
        p2=new TextField("Water");
        gridPane.add(tankLoad(0),1,1);
        gridPane.add(p1, 1,2);
        gridPane.add(vsLoad(),2,1);
        StackPane pn=new StackPane();
        pn.getChildren().add(play);
        gridPane.add(pn, 2,2);
        gridPane.add(tankLoad(1),3,1);
        gridPane.add(p2, 3,2);
        newGameWindow=new Scene(gridPane, 570, 200);
        secondaryStage.setTitle("New Game");
    }

    /**
     * starts secondary window
     */

    public void start(){
        secondaryStage.setScene(newGameWindow);
        secondaryStage.show();
    }

    /**
     * classes for loading images
     * @return
     * @throws FileNotFoundException
     */
    private ImageView vsLoad() throws FileNotFoundException {
        ImageView vsView=new ImageView(new Image(new FileInputStream("resources\\vs.png")));
        vsView.setFitWidth(250);
        vsView.setFitHeight(150);
        return vsView;
    }

    private ImageView tankLoad(int i) throws FileNotFoundException {
        ImageView tankView=new ImageView(new Image(new FileInputStream("resources\\newtank"+ i +".png")));
        tankView.setFitWidth(150);
        tankView.setFitHeight(150);
        return tankView;
    }

}
