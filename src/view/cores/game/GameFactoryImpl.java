package view.cores.game;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.*;
import java.net.URI;
import java.net.URL;
import java.util.*;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;

import controller.GameController;
import view.Level;
import view.components.mybuttons.*;
import view.containers.Size;
import view.cores.ViewImpl;
import view.cores.panels.panes.*;
import view.cores.windows.*;

public class GameFactoryImpl implements GameFactory {

    public int id;
    private WindowFactory winFactory = new WindowFactoryImpl();
    private PaneFactory paneFactory = new PaneFactoryImpl();
    private GameController<JButton> contr;
    private int size = 5;
    private Level stats = new Level(5, 0);
    private ButtonFactory bFactory = new ButtonFactoryImpl(); 

    @Override
    public GameView swingGameView() {
        return new SwingGameImpl(Optional.empty()).build();
    }

    @Override
    public GameView swingGameView(Level stats) {
        Optional<Level> withStats = Optional.of(stats);
        return new SwingGameImpl(withStats).build();
    }

    private class SwingGameImpl extends ViewImpl implements GameView{

        public SwingGameImpl(Optional<Level> withStats) {
            withStats.ifPresent(l -> stats = l);
        }


        @Override
        public <C> void addComponentsToGrid(ViewButton<C> compo) {
            // JButton button = (JButton)compo.unwrap();
            // // button.addActionListener(e -> System.out.println("DD"));
            // this.main.add(button);
            this.panel.addToContainer(compo);
        }


        private GameView build() {
            contr = new GameController<>();
            size = stats.size();
            this.setWindow(winFactory.swingFrame(new Size(size, size)));
            this.window.init();
            this.setGridPane();
            this.fillGrid();
            
            this.buildWindow();
            return this;
        }

        private void fillGrid() {
            for (int i=0; i<size; i++){
                for (int j=0; j<size; j++){
                    final ViewButton<JButton> jb = newButton();
                    contr.addButton(jb, j, i);
                    this.addComponentsToGrid(jb);
                }
            }
        }

        private ViewButton<JButton> newButton() {
            JButton btn = new JButton();
            ViewButton<JButton> button = bFactory.cellSwingFromButton(btn);
            button.setID(id++);
            button.triggerEvent(b -> b.addMouseListener(this.onRightClick()));
            button.triggerEvent(b -> b.addActionListener(this.onLeftCick()));
            return button;
        }


        private MouseListener onRightClick() {
            return new MouseInputAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    final JButton bt = (JButton)e.getSource();
                    contr.handleRightClick(bFactory.cellSwingFromButton(bt));
                    contr.refresh(true);
                }
            };
        }   
        
        private ActionListener onLeftCick() {
            return (e) ->{
                final JButton bt = (JButton)e.getSource();
                contr.handleLeftClick(bFactory.cellSwingFromButton(bt));
                contr.refresh(false);
            };
        }


        @Override
        public void setGridPane() {
            Size s = window.getSize();
            this.addMainPane(paneFactory.swingGridPane(s.rows(), s.columns()));
        }

        @Override
        public <C> void modifyButton(ViewButton<C> btn) {
            JButton button = (JButton)btn.unwrap();
            // button.setText(btn.getText());
            URL u = getClass().getResource("/resource/empty.png");
            ImageIcon icon = new ImageIcon(getClass().getResource(btn.imagePath()));
            Image img = icon.getImage();
            icon = new ImageIcon(img.getScaledInstance(30, 30, Image.SCALE_SMOOTH));
            button.setIcon(icon);
            button.setDisabledIcon(icon);
            button.setEnabled(!btn.isDisable());
            
            // button.setBackground(Color.decode(btn.getBGColor()));
        }


        @Override
        public void startController() {
            contr.start(this);
        }


        @Override
        public Level getLevel() {
            return stats;
        }
    }

    

    
}
