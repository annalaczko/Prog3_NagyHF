package KeyEventHandlers;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import BackEnd.Game;

/**
 * Class for handling pressing down a key
 */

public class PressKeyHandler implements EventHandler<KeyEvent> {
    private final Game game;
    public PressKeyHandler(Game game){
        this.game=game;
    }
    @Override
    public void handle(KeyEvent keyEvent) {
        switch (keyEvent.getCode()){
            case A:
                game.getTanks().get(0).setMovement(0, true);
                break;
            case W:
                game.getTanks().get(0).setMovement(1,true);
                break;
            case S:
                game.getTanks().get(0).setMovement(2, true);
                break;
            case D:
                game.getTanks().get(0).setMovement(3, true);
                break;
            case C:
                game.getTanks().get(0).shoot();
                break;
            case J:
                game.getTanks().get(1).setMovement(0, true);
                break;
            case I:
                game.getTanks().get(1).setMovement(1, true);
                break;
            case K:
                game.getTanks().get(1).setMovement(2, true);
                break;
            case L:
                game.getTanks().get(1).setMovement(3, true);
                break;
            case M:
                game.getTanks().get(1).shoot();
                break;
            default:
                break;
        }
    }
}