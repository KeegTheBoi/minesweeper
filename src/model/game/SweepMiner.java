package model.game;

import model.Coord;

public interface SweepMiner {

    void seedBombs(int diffuculty);

    void fillRemaining();

    void unveil(Coord pos);

    int bombsSize();

    boolean isOver(Coord c);

    public void recursiveDiscoveryOf(Coord c);

    int hitCount();

}
