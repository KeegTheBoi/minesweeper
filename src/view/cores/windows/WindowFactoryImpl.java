package view.cores.windows;


import javax.swing.*;
import view.containers.Size;

public class WindowFactoryImpl implements WindowFactory {

    @Override
    public Window<JFrame> swingFrame(Size size) {
        return new SwingWindow<JFrame>(size).build();
    }
    
    private class SwingWindow<F> implements Window<F> {

        private JFrame frame;
        private JPanel panel;
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
        public void setScene(Object scene) {
            this.panel = (JPanel)scene;
            frame.getContentPane().add(this.panel);
        }


        private SwingWindow<F> build() {
            this.frame = new JFrame("Minesweeper");
            this.setDimension();
            return this;
        }

        @Override
        public Size getSize() {
            return this.size;
        }

    }
}
