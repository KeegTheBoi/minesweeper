package view;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;

import controller.GameController;
import view.components.ViewComponent;
import view.components.ViewComponentFactory;
import view.components.ViewComponentFactoryImpl;
import view.components.mybuttons.Button;
import view.components.mybuttons.ButtonFactory;
import view.components.mybuttons.ButtonFactoryImpl;
import view.containers.Size;
import view.cores.GameFactory;
import view.cores.GameFactoryImpl;
import view.cores.GameView;

public class GameUI extends Page{

    private int size;
    private GameController<JFrame, JPanel, JButton> contr;
    private Level stats;
    
    ButtonFactory bFactory = new ButtonFactoryImpl(); 
    ViewComponentFactory compoFactory = new ViewComponentFactoryImpl();
    GameView<JFrame, JPanel> game;
    private int id;


    public Level getStats() {
        return stats;
    }

    public GameUI(int size, Level stats) {
        super(size);
        this.stats = stats;
        this.size = size;
        GameFactory gameFactory = new GameFactoryImpl();
        this.game = gameFactory.swingGameView();
        this.contr = new GameController<>(game);
        this.game.initWindow(new Size(size, size));
        this.game.addMainPane();
        this.game.setGridPane();
        this.fillGrid();
        this.contr.start();
    }


    private Button<JButton> newButton() {
        JButton btn = new JButton("DD");
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
            var b = bFactory.cellSwingFromButton(bt);
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


    public void modifyButton(ViewComponent<JButton> k, String txt, String bgColor, boolean enable) {
        k.setText(txt);
        k.setBGColor(bgColor);
        k.setDisable(enable);
    }

    public void modifyButton(ViewComponent<JButton> btn, String text) {
        btn.setText(text);
    }



    
}
