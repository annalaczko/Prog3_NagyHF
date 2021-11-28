package Windows;

import Files.TankFile;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Class for showing the closed battles in a secondary window
 */

public class ClosedBattlesWindow {
    private final Stage secondaryStage;
    private final Button clear;
    private ObservableList<String> listItems;
    private final ListView<String> list;
    private final Scene closedBattlesScene;

    private class clearButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle (ActionEvent actionEvent) {
            try {
                TankFile.clearClosedBattleData();
                listItems=FXCollections.observableArrayList (TankFile.closedBattlesToString());
                list.setItems(listItems);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

    public ClosedBattlesWindow(Stage secondaryStage) throws IOException, ClassNotFoundException {
        this.secondaryStage=secondaryStage;
        BorderPane borderPane= new BorderPane();
        borderPane.setPadding(new Insets(10 ,10,10,10));

        StackPane stackPaneTop=new StackPane();
        stackPaneTop.setPadding(new Insets(10 ,10,10,10));
        borderPane.setTop(stackPaneTop);

        Label title= new Label("Closed Battles");
        title.setStyle("-fx-font-size:20; " +
                "-fx-font-weight: bold; " +
                "-fx-text-fill: #666666;  " +
                "-fx-font: 30px Verdana");
        stackPaneTop.getChildren().add(title);

        StackPane stackPaneBottom=new StackPane();
        stackPaneBottom.setPadding(new Insets(10 ,10,10,10));
        borderPane.setBottom(stackPaneBottom);

        clear=new Button("Clear");
        clear.setPrefSize(180, 30);
        stackPaneBottom.getChildren().add(clear);

        list = new ListView<>();
        listItems=FXCollections.observableArrayList (TankFile.closedBattlesToString());
        list.setItems(listItems);
        borderPane.setCenter(list);

        closedBattlesScene=new Scene(borderPane, 400, 600);
        secondaryStage.setTitle("Closed Battles");

        secondaryStage.setResizable(false);
    }

    /**
     * starts the secondary window
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void start() throws IOException, ClassNotFoundException {
        secondaryStage.setScene(closedBattlesScene);
        listItems=FXCollections.observableArrayList (TankFile.closedBattlesToString());
        list.setItems(listItems);
        clear.setOnAction(new clearButtonHandler());
        secondaryStage.show();
    }

}