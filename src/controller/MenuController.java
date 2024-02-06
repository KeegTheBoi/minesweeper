package controller;

import java.util.Objects;

import view.GameUI;
import view.Level;

public class MenuController implements Controller{

    private Level stats;

    public MenuController() {}

    @Override
    public void start() {
        if(Objects.isNull(stats)) {
            throw new IllegalStateException();
        }
        new GameUI(stats.size(), stats);
    }

    @Override
    public void reset() {
    }

    public void getInfo(Level stats) {
        this.stats = stats;
    }
    
}
