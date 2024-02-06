package view.cores.menu;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controller.MenuController;
import view.components.mybuttons.*;
import view.containers.Size;
import view.cores.ViewImpl;
import view.cores.panels.panes.*;
import view.cores.panels.panes.PaneFactoryImpl;
import view.cores.windows.*;

public class MenuFactoryImpl implements MenuFactory{

    public enum Difficulty{
        EASY, INTERMIDATE, HARD, VIETNAM
    }

    private MenuController menuController = new MenuController();
    PaneFactory paneFactory = new PaneFactoryImpl();
    private WindowFactory windowFactory = new WindowFactoryImpl();

    @Override
    public MenuView swingMenuView() {
        return new SwingMenuView().build();
    }

    private class SwingMenuView extends  ViewImpl implements MenuView{

        private JPanel menuPane;
        private GridBagConstraints gbc;
        @Override
        public void addMenuPane() {
            JPanel scene = (JPanel)panel.unwrap();
            menuPane = new JPanel(new GridBagLayout());
            menuPane.setBorder(new EmptyBorder(20, 20, 20, 20));
            this.gbc = new GridBagConstraints();
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.weighty = 1;
            menuPane.add(new JLabel("MENU"), gbc);
            gbc.weighty = 0;
            gbc.anchor = GridBagConstraints.NORTH;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            scene.add(menuPane);
        }

        public MenuView build() {
            
            this.setWindow(windowFactory.swingFrame(new Size(5, 5)));
            this.window.init();
            this.addMainPane(paneFactory.swingBorder());
            this.addMenuPane();
            this.fillButtons();

            this.buildWindow();
            return this;
        }

        @Override
        public <B> void fillButtons() {
            ButtonFactory factory = new ButtonFactoryImpl();
            
            Arrays.stream(Difficulty.values())
                .map(FireButton::new)
                .map(factory::cellSwingFromButton)
                .peek(gb -> gb.triggerEvent(b -> b.addActionListener(onClick())))
                .map(r -> (FireButton)r.unwrap())
                .forEach(b -> menuPane.add(b, gbc));
        }

        private ActionListener onClick() {
            return e -> {
                final FireButton bt = (FireButton)e.getSource();
                menuController.setLevel(bt.getStats());
                menuController.start(null);
                this.window.close();
            };
        }

    }
    
}
