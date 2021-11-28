package Windows;

import Files.BattleData;
import Files.TankFile;
import GUI.MenuGUI;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class for a secondary window, where we can load an unclosed battle or delete one
 */

public class LoadGameWindow {
    private final Stage secondaryStage;
    private final MenuGUI menu;
    private final ChoiceBox choiceBox;
    private BattleData choosenBattleData;
    private int choosenBattleID;
    private boolean choosenBattleExists =false;
    private ArrayList<String> stringArrayList;
    private final Button play;
    private final Button delete;
    private final Scene loadGameWindowScene;

    private class deleteButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle (ActionEvent actionEvent) {
            if (choosenBattleExists) {
                TankFile.deleteUnclosedBattle(choosenBattleID);
                choosenBattleExists = false;
                try {
                    stringArrayList = TankFile.unclosedBattlesToString();
                    choiceBox.setItems(FXCollections.observableArrayList(stringArrayList));
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }

            }
        }
    }
    private class playButtonHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent actionEvent) {
            if (choosenBattleExists) {
                menu.getMain().getGame().setBattleData(choosenBattleData);
                try {
                    TankFile.deleteUnclosedBattle(choosenBattleID);
                    stringArrayList = new ArrayList<>(TankFile.unclosedBattlesToString());
                    choiceBox.setItems(FXCollections.observableArrayList(stringArrayList));
                    choosenBattleExists=false;
                    menu.getMain().getGame().start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                secondaryStage.close();
            }
        }
    }
    private class choiceBoxListener implements ChangeListener<Number>{
        @Override
        public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
            try {
                if (t1.intValue()>=0) {
                    choosenBattleData = TankFile.loadAllUnclosedBattle().get(t1.intValue());
                    choosenBattleID = t1.intValue();
                    choosenBattleExists = true;
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

    public LoadGameWindow(MenuGUI menu, Stage secondaryStage) throws IOException, ClassNotFoundException {
        this.menu=menu;
        this.secondaryStage=secondaryStage;
        BorderPane borderPane= new BorderPane();
        borderPane.setPadding(new Insets(10 ,10,10,10));
        StackPane stackPane=new StackPane();
        stackPane.setPadding(new Insets(10 ,10,10,10));
        stringArrayList=TankFile.unclosedBattlesToString();

        choiceBox= new ChoiceBox(FXCollections.observableArrayList(stringArrayList));
        choiceBox.setPrefSize(400, 30);
        stackPane.getChildren().add(choiceBox);
        borderPane.setTop(stackPane);


        GridPane gridPane= new GridPane();
        play=new Button("Play!");
        delete=new Button("Delete");
        play.setPrefSize(180, 30);
        delete.setPrefSize(180, 30);
        gridPane.add(tankLoad(0),0,0);
        gridPane.add(tankLoad(1),1,0);
        gridPane.add(play, 0,1);
        gridPane.add(delete, 1,1);
        gridPane.setPadding(new Insets(10 ,10,10,10));
        borderPane.setCenter(gridPane);
        loadGameWindowScene=new Scene(borderPane, 400, 300);
        secondaryStage.setTitle("Choose a Game");

        secondaryStage.setResizable(false);


    }

    /**
     * starts the secondary window
     * @throws IOException
     * @throws ClassNotFoundException
     */

    public void start() throws IOException, ClassNotFoundException {
        secondaryStage.setScene(loadGameWindowScene);
        stringArrayList=TankFile.unclosedBattlesToString();
        choiceBox.setItems(FXCollections.observableArrayList(stringArrayList));
        choiceBox.getSelectionModel().selectedIndexProperty().addListener(new choiceBoxListener());
        delete.setOnAction(new deleteButtonHandler());
        play.setOnAction(new playButtonHandler());
        choiceBox.getSelectionModel().selectedIndexProperty().addListener(new choiceBoxListener());
        secondaryStage.show();
    }

    /**
     * only for visual purposes loads the image of one of tanks
     * @param i tank index
     * @return
     * @throws FileNotFoundException
     */

    private ImageView tankLoad(int i) throws FileNotFoundException {
        ImageView tankView=new ImageView(new Image(new FileInputStream("resources\\newtank"+ i +".png")));
        tankView.setFitWidth(180);
        tankView.setFitHeight(180);
        return tankView;
    }

}