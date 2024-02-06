package view.cores.game;

import view.Level;
import view.components.mybuttons.ViewButton;
import view.cores.View;


public interface GameView extends View{

    void setGridPane();

    <C> void addComponentsToGrid(ViewButton<C> compo);

    <C> void modifyButton(ViewButton<C> btn);

    void startController();

    Level getLevel();

    
} 
