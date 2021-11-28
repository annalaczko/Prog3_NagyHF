package KeyEventHandlers;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import BackEnd.Game;

/**
 * Class for handling releasing a key
 */

public class ReleaseKeyHandler implements EventHandler<KeyEvent> {
    private final Game game;
    public ReleaseKeyHandler(Game game){
        this.game=game;
    }
    @Override
    public void handle(KeyEvent keyEvent) {
        switch (keyEvent.getCode()){
            case A:
                game.getTanks().get(0).setMovement(0,false);
                break;
            case W:
                game.getTanks().get(0).setMovement(1, false);
                break;
            case S:
                game.getTanks().get(0).setMovement(2,false);
                break;
            case D:
                game.getTanks().get(0).setMovement(3,false);
                break;
            case J:
                game.getTanks().get(1).setMovement(0, false);
                break;
            case I:
                game.getTanks().get(1).setMovement(1, false);
                break;
            case K:
                game.getTanks().get(1).setMovement(2, false);
                break;
            case L:
                game.getTanks().get(1).setMovement(3, false);
                break;
            default:
                break;
        }
    }
}