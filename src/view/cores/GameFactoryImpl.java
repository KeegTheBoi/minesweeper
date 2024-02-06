package view.cores;

import java.awt.*;
import javax.swing.*;

import view.components.ViewComponent;
import view.components.mybuttons.Button;
import view.containers.Size;
import view.cores.windows.Window;
import view.cores.windows.WindowFactory;
import view.cores.windows.WindowFactoryImpl;

public class GameFactoryImpl implements GameFactory {

    @Override
    public GameView<JFrame, JPanel> swingGameView() {
        return new SwingGameImpl<>();
    }

    private class SwingGameImpl<F, P> implements GameView<F, P> {

        private Window<JFrame> window;
        private JPanel main = new JPanel();

        @Override
        public void initWindow(Size size) {
            WindowFactory factory = new WindowFactoryImpl();
            this.window = factory.swingFrame(size);
            this.window.display();
        }

        @Override
        public void addMainPane() {
            this.window.setScene(main);
        }

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

        @Override
        public void closeWithMessage(String string) {
            this.messageBox(string);
            this.window.exit();
        }

        @Override
        public void messageBox(String string) {
            this.window.message(string);
        }
    }
    
}
