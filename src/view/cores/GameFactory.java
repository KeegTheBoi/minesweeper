package view.cores;

import javax.swing.*;

public interface GameFactory {
    
    GameView<JFrame, JPanel> swingGameView();

    // GameView javaFXGameView();
}
