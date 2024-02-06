package controller;

import java.util.Objects;
import view.Level;
import view.cores.game.*;

public class MenuController implements Controller{

    private GameFactory game = new GameFactoryImpl();
    private Level level;

    public MenuController() {}

    @Override
    public void start(GameView gameView) {
        if(Objects.isNull(level)) {
            throw new IllegalStateException();
        }
        GameView view = game.swingGameView(level);
        view.startController();
    }

    @Override
    public void reset() {
    }

    public void setLevel(Level stats) {
        this.level = stats;
    }
    
}
