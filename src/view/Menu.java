package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.BoxLayout;
import javax.swing.border.EmptyBorder;

import controller.Controller;


public class Menu extends Page{

    public enum Difficulty{
        EASY, INTERMIDATE, HARD, VIETNAM
    }

    private GridBagConstraints gbc;

    public Menu(int size, Controller contr) {
        super(size, contr);
        panel.setLayout(new GridBagLayout());
        this.gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.getContentPane().add(panel);

        createMenu();
    }

    private void createMenu() {
        Arrays.stream(Difficulty.values()).map(FireButton::new)
            .peek(fb -> fb.addActionListener(onClick()))
            .forEach(b -> panel.add(b, gbc));
            
    }

    private ActionListener onClick() {
        return e -> {
            final FireButton bt = (FireButton)e.getSource();
            contr.getGameLevel(bt);
            this.close();
        };
    }

    
    
}
