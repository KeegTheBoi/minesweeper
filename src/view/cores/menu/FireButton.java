package view.cores.menu;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.border.Border;

import view.Level;
import view.MenuUI.Difficulty;

public class FireButton extends JButton {
    private static int WIDTH = 4;
    private static int VIETNAM_SIZE = 2;
    private Difficulty level;

    public FireButton(Difficulty level) {
        this.level = level;
        this.setBorder(new RoundedBorder(15));
        this.setContentAreaFilled(false);
        this.setText(level.name());
    }

    public Level getStats() {
        int size = (level.ordinal() + 1) * (level == Difficulty.VIETNAM ? VIETNAM_SIZE : 4);
        double diff = (level.ordinal() + 1) * (10.0 / Difficulty.values().length);
        return new Level(size, (int)Math.ceil(diff));
    }

    private static class RoundedBorder implements Border {

        private final int radius;

        public RoundedBorder(int radius) {
            this.radius = radius;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.drawRoundRect(x, y, width -1 , height -1 , radius, radius);
            g.setColor(Color.ORANGE);
            
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(WIDTH, WIDTH, WIDTH, WIDTH);
        }

        @Override
        public boolean isBorderOpaque() {
            return true;
        }
        
    }
}
