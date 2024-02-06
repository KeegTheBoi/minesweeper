package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.util.Arrays;

import controller.MenuController;


public class MenuView extends Page{

    public enum Difficulty{
        EASY, INTERMIDATE, HARD, VIETNAM
    }

    MenuController menuController;
    private GridBagConstraints gbc;

    public MenuView(int size) {
        super(size);
        panel.setLayout(new GridBagLayout());
        this.gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.getContentPane().add(panel);

        createMenu();
        menuController = new MenuController(this);
        this.display();
    }

    private void createMenu() {
        Arrays.stream(Difficulty.values()).map(FireButton::new)
            .peek(fb -> fb.addActionListener(onClick()))
            .forEach(b -> panel.add(b, gbc));
            
    }

    private ActionListener onClick() {
        return e -> {
            final FireButton bt = (FireButton)e.getSource();
            menuController.start();
            this.close();
        };
    }

    
    
}
