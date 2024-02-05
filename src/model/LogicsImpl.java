package model;
import model.cells.Cell;
import model.game.*;
import model.gameBoard.*;


public class LogicsImpl implements Logics {

    private Coord current;
    private final Board<Coord, Cell> board;
    private final SweepMiner game;

    public LogicsImpl(int size, int difficulty) {
        this.board = new BoardImpl<Coord, Cell>(size);
        this.game = new SweepMinerImpl(board);
        this.game.seedBombs(difficulty);
        this.game.fillRemaining();
    }

    @Override
    public void hit(Coord pos) {
        this.current = pos;
        game.recursiveDiscoveryOf(current);
    }

    @Override
    public boolean isOver() {
        return game.isOver(current);
    }

    @Override
    public boolean hasWon() {
        return board.size() - game.hitCount() == game.bombsSize();
    }

    @Override
    public void flag(Coord pos) {
        board.getCell(pos).changeFlag();
    }

    @Override
    public Cell getResult(Coord c) {
        return board.getCell(c);
    }

}
