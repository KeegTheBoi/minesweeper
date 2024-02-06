package view.cores;

import view.components.ViewComponent;
import view.components.mybuttons.Button;
import view.containers.Size;


public interface GameView<W, P> extends View{

    void initWindow(Size size);
    
    void addMainPane();

    void setGridPane();

    <C> void addComponentsToGrid(Button<C> compo);

    <C> void modifyButton(Button<C> btn);

    
} 
