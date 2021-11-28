package Buttons;

import javafx.scene.control.Button;

/**
 * Class for the buttons during menu scene
 */

public class MenuButton extends Button {
    private String name;

    public MenuButton(int nameID, int coorX, int coorY) {
        relocate(coorX, coorY);
        switch (nameID){
            case 0:
                this.name="New Game";
                break;
            case 1:
                this.name="Load Game";
                break;
            case 2:
                this.name="Closed Battles";
                break;
            case 3:
                this.name="Exit";
                break;
            default:
                break;
        }
        setText(this.name);
        setPrefSize(300, 50);
        setStyle("-fx-faint-focus-color: transparent; " +
                "-fx-font-size:30; " +
                "-fx-font-weight: bold; " +
                "-fx-text-fill: #666666;  " +
                "-fx-border-color: " +
                "#666666; -fx-border-width: 2px;" +
                "-fx-font: 30px Verdana");
    }



}