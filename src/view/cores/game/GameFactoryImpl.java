package view.cores.game;

import java.awt.*;
import javax.swing.*;

import view.components.mybuttons.Button;
import view.containers.Size;
import view.cores.ViewImpl;
import view.cores.windows.Window;

public class GameFactoryImpl implements GameFactory {

    @Override
    public GameView swingGameView() {
        return new SwingGameImpl();
    }

    private class SwingGameImpl extends ViewImpl implements GameView{

        private Window window;
        private JPanel main = new JPanel();

        @Override
        public <C> void addComponentsToGrid(Button<C> compo) {
            JButton button = (JButton)compo.unwrap();
            // button.addActionListener(e -> System.out.println("DD"));
            this.main.add(button);
        }


        @Override
        public void setGridPane() {
            Size s = window.getSize();
            this.main.setLayout(new GridLayout(s.rows(), s.columns()));
        }

        @Override
        public <C> void modifyButton(Button<C> btn) {
            JButton button = (JButton)btn.unwrap();
            button.setText(btn.getText());
            button.setEnabled(!btn.isDisable());
            
            button.setBackground(Color.decode(btn.getBGColor()));
        }

    }
    
}
