package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.util.Arrays;

import controller.MenuController;


public class MenuUI extends Page{

    public enum Difficulty{
        EASY, INTERMIDATE, HARD, VIETNAM
    }

    MenuController menuController;
    private GridBagConstraints gbc;

    public MenuUI(int size) {
        super(size);
        panel.setLayout(new GridBagLayout());
        this.gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.getContentPane().add(panel);

        
        menuController = new MenuController();
        this.display();
    }


    
    
}
