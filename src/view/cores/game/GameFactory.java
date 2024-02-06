package view.cores.game;

import view.Level;

public interface GameFactory {
    
    GameView swingGameView();

    GameView swingGameView(Level stats);

    // GameView javaFXGameView();
}
