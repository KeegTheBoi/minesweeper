package controller;

import java.util.Objects;

import view.GameUI;
import view.Level;
import view.cores.game.GameFactory;
import view.cores.game.GameFactoryImpl;
import view.cores.game.GameView;

public class MenuController implements Controller{

    private Level stats;
    private GameFactory game = new GameFactoryImpl();

    public MenuController() {}

    @Override
    public void start() {
        if(Objects.isNull(stats)) {
            throw new IllegalStateException();
        }
        GameView view = game.swingGameView(stats);
        view.startController();
    }

    @Override
    public void reset() {
    }

    public void getInfo(Level stats) {
        this.stats = stats;
    }
    
}
