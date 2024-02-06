package view;

import javax.swing.*;
import javax.swing.event.*;

import java.awt.Window;
import java.awt.event.*;

import controller.GameController;
import view.components.*;
import view.components.mybuttons.*;
import view.containers.Size;
import view.cores.*;
import view.cores.game.GameFactory;
import view.cores.game.GameFactoryImpl;
import view.cores.game.GameView;
import view.cores.windows.WindowFactory;
import view.cores.windows.WindowFactoryImpl;

public class GameUI extends Page{

    private int size;
    private GameController<JButton> contr;
    private Level stats;
    
    ButtonFactory bFactory = new ButtonFactoryImpl(); 
    ViewComponentFactory compoFactory = new ViewComponentFactoryImpl();
    GameView game;
    private int id;


    public Level getStats() {
        return stats;
    }

    public GameUI(int size, Level stats) {
        super(size);
        this.stats = stats;
        this.size = size;
        GameFactory gameFactory = new GameFactoryImpl();
        WindowFactory winFactory = new WindowFactoryImpl();
        this.game = gameFactory.swingGameView();
        this.contr = new GameController<>(game);
        this.game.setWindow(winFactory.swingFrame(new Size(size, size), null));
        this.game.buildWindow();
        this.game.setGridPane();
        this.fillGrid();
        this.contr.start();
    }


    private Button<JButton> newButton() {
        JButton btn = new JButton();
        Button<JButton> button = bFactory.cellSwingFromButton(btn);
        button.setID(id++);
        button.triggerEvent(b -> b.addMouseListener(this.onRightClick()));
        button.triggerEvent(b -> b.addActionListener(this.onLeftCick()));
        return button;
    }

    private void fillGrid() {
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                final Button<JButton> jb = newButton();
                contr.addButton(jb, j, i);
                game.addComponentsToGrid(jb);
            }
        }
    }

    private ActionListener onLeftCick() {
        return (e) ->{
            final JButton bt = (JButton)e.getSource();
            contr.handleLeftClick(bFactory.cellSwingFromButton(bt));
            contr.refresh();
        };
    }

    private MouseListener onRightClick() {
        return new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                final JButton bt = (JButton)e.getSource();
                contr.handleRightClick(bFactory.cellSwingFromButton(bt));
                contr.refresh();
            }
        };
    }    
}
