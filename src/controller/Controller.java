package controller;

import javax.swing.JButton;
import view.FireButton;

public interface Controller{
    
    void loadMenu();

    void initGame();

    void addButton(JButton jb, int x, int y);

    void handleRightClick(JButton bt);

    void refresh();

    void getGameLevel(FireButton bt);

    void handleLeftClick(JButton bt);
}
