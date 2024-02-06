package view.cores.windows;

import javax.swing.*;
import java.awt.*;
import view.containers.Container;
import view.containers.Size;


public class WindowFactoryImpl implements WindowFactory {

    @Override
    public Window swingFrame(Size size, Container defaultPane) {
        return new SwingWindow(size, defaultPane).build();
    }
    
    private class SwingWindow implements Window {

        private JFrame frame;
        private Size size;
        private Container scene;

        public SwingWindow(Size size2, Container scene) {
            this.size = size2;
            this.scene = scene;
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
        public void setScene() {
            JPanel panel = (JPanel)scene.unwrap();
            frame.getContentPane().add(BorderLayout.CENTER, panel);
            panel.add(new JButton("EASY"));
        }


        private SwingWindow build() {
            this.frame = new JFrame("Minesweeper");
            this.frame.setDefaultCloseOperation(3);
            this.setDimension();
            setScene();
            return this;
        }

        @Override
        public Size getSize() {
            return this.size;
        }


    }
}
