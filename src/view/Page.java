package view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Page extends JFrame{
    
    protected JPanel panel;

    public Page(int size) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(48*size, 48*size);
        panel = new JPanel();
    }

    public void exit() {
        System.exit(0);
    }

    public void closeWithMessage(String message) {
        this.messageBox(message);
        this.close();
    }

    public void close() {
        this.dispose();
    }

    public void messageBox(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void reset() {

    }

    public void display() {
        this.setVisible(true);
    }
}
