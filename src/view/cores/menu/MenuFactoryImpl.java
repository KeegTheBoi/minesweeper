package view.cores.menu;

import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.*;

import controller.MenuController;
import view.components.mybuttons.Button;
import view.components.mybuttons.ButtonFactory;
import view.components.mybuttons.ButtonFactoryImpl;
import view.containers.Container;
import view.containers.Size;
import view.cores.ViewImpl;
import view.cores.panels.panes.PaneFactory;
import view.cores.panels.panes.PaneFactoryImpl;
import view.cores.windows.WindowFactory;
import view.cores.windows.WindowFactoryImpl;

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
        @Override
        public void addMenuPane() {
            JPanel scene = (JPanel)panel.unwrap();
            menuPane = new JPanel();
            scene.add(menuPane);
        }

        public MenuView build() {
            Container main = paneFactory.swingPane();
            
            this.addMainPane(main);
            this.setWindow(windowFactory.swingFrame(new Size(5, 5), this.panel));
            this.buildWindow();
            this.window.setScene();
            
            this.addMenuPane();
            this.fillButtons();
            return this;
        }

        @Override
        public <B> void fillButtons() {
            ButtonFactory factory = new ButtonFactoryImpl();
            Button<JButton> button = factory.cellSwingNewButton();
            
            Arrays.stream(Difficulty.values()).map(Difficulty::toString).peek(button::setText)
                .peek(gb -> button.triggerEvent(b -> b.addActionListener(onClick())))
                .map(r -> (JButton)button.unwrap())
                .forEach(b -> menuPane.add(b));
        }

        private ActionListener onClick() {
            return e -> {
                final FireButton bt = (FireButton)e.getSource();
                menuController.getInfo(bt.getStats());
                menuController.start();
                this.window.close();
            };
        }

    }
    
}
