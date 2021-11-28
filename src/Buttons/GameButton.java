package Buttons;

import javafx.scene.control.Button;

/**
 * Class for the buttons during game
 */

public class GameButton extends Button {

    public GameButton(String name, int coorX, int coorY) {

        relocate(coorX, coorY);
        setText(name);
        setPrefSize(200, 50);
        setStyle("-fx-faint-focus-color: transparent; " +
                "-fx-font-size:30; " +
                "-fx-font-weight: bold; " +
                "-fx-text-fill: #666666;  " +
                "-fx-border-color: " +
                "#666666; -fx-border-width: 2px;" +
                "-fx-font: 30px Verdana");
    }
}