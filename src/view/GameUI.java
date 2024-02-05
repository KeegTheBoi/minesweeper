package view;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;

import controller.Controller;

public class GameUI extends Page{

    private int size;
    private Controller contr;
    private Level stats;
    private Map<Integer, Color> mapColor = new HashMap<>(Map.of(
         0, Color.DARK_GRAY,
         1, Color.decode("#006400"),
         2, Color.decode("#8B0000"),
         3, Color.BLUE,
         4, Color.PINK,
         5, Color.CYAN
    ));

    public Map<Integer, Color> getMapColor() {
        return mapColor;
    }

    public Level getStats() {
        return stats;
    }

    public GameUI(int size, Controller cont, Level stats) {
        super(size, cont);
        this.stats = stats;
        this.size = size;
        this.contr = cont;
        this.panel.setLayout(new GridLayout(size,size));
        this.getContentPane().add(BorderLayout.CENTER,panel);
        this.fillGrid();
    }


    private JButton newButton() {
        JButton button = new JButton(" ");
        button.setForeground(Color.WHITE);
        button.setBackground(Color.BLACK);
        button.addMouseListener(this.onRightClick());
        button.addActionListener(this.onLeftCick());
        return button;
    }

    private void fillGrid() {
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                final JButton jb = newButton();
                contr.addButton(jb, j, i);
                panel.add(jb);
            }
        }
    }

    private ActionListener onLeftCick() {
        return (e) ->{
            final JButton bt = (JButton)e.getSource();
            contr.handleLeftClick(bt);
            contr.refresh();
        };
    }


    private MouseListener onRightClick() {
        return new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                final JButton bt = (JButton)e.getSource();
                contr.handleRightClick(bt);
                contr.refresh();
            }
        };
    }


    public void modifyButton(JButton k, String txt, Color bgColor, boolean enable) {
        k.setText(txt);
        k.setBackground(bgColor);
        k.setEnabled(enable);
    }

    public void modifyButton(JButton btn, String text) {
        btn.setText(text);
    }



    
}
