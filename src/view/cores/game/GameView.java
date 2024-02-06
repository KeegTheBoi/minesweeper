package view.cores.game;

import view.components.mybuttons.Button;
import view.cores.View;


public interface GameView extends View{

    void setGridPane();

    <C> void addComponentsToGrid(Button<C> compo);

    <C> void modifyButton(Button<C> btn);

    
} 
