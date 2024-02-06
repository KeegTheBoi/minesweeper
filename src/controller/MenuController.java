package controller;

import view.GameUI;
import view.Level;
import view.MenuView;
import view.cores.GameView;

public class MenuController implements Controller{

    private MenuView menuView;

    public MenuController(MenuView view) {
        this.menuView = view;
    }

    @Override
    public void start() {
        new GameUI(10, new Level(10, 3));
    }

    @Override
    public void reset() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'reset'");
    }
    
}
