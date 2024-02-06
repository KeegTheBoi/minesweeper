package view.cores.windows;

import javax.swing.*;
import java.awt.*;
import view.containers.Container;
import view.containers.Size;
import view.cores.panels.panes.PaneFactory;
import view.cores.panels.panes.PaneFactoryImpl;


public class WindowFactoryImpl implements WindowFactory {

    @Override
    public Window swingFrame(Size size) {
        return new SwingWindow(size);
    }
    
    private class SwingWindow implements Window {

        private JFrame frame;
        private Size size;

        public SwingWindow(Size size2) {
            this.size = size2;
        }

        @Override
        public void close() {
            this.frame.dispose();
        }

        private void setDimension() {
            frame.setSize(size.rows() * 50, size.columns() * 50);
        }

        @Override
        public void display() {
            this.frame.setVisible(true);
        }

        @Override
        public void message(String string) {
            JOptionPane.showMessageDialog(frame, string);
        }

        @Override
        public void exit() {
            System.exit(0);
        }

        @Override
        public void setScene(Container scene) {
            JPanel panel = (JPanel)scene.unwrap();
            frame.getContentPane().add(BorderLayout.CENTER,panel);
        }


        public void init() {
            this.frame = new JFrame("Minesweeper");
            this.frame.setDefaultCloseOperation(3);
            this.setDimension();
        }

        @Override
        public Size getSize() {
            return this.size;
        }


    }
}
