package model.game;

import model.Coord;
import model.cells.Cell;
import model.cells.CellFactory;
import model.cells.CellFactoryImpl;
import model.cells.CellsUtils;
import model.gameBoard.Board;

import java.util.*;
import java.util.stream.*;
import static java.util.function.Predicate.not;

public class SweepMinerImpl implements SweepMiner{
    
    private Board<Coord, Cell> board;
    private final CellFactory cf = new CellFactoryImpl();
    private Double limit;
    private int hitted;

    public SweepMinerImpl(Board<Coord, Cell> board) {
        this.board = board;
    }


    @Override
    public void seedBombs(int difficulty) {
        double ratio = (difficulty / 10.0);
        double filler = board.bound() / 2.0;
        ratio = ratio < 0.5 ? -ratio : ratio;
        double scount = ratio * board.bound();
        limit = 1.5 * board.bound() - (filler - scount);
        while(board.mapSize() < (int)Math.ceil(limit)) {
            board.setValue(this.randomCoord(), cf.mine());
        }
        System.out.println((int)Math.ceil(limit));
    }

    public void fillRemaining() {
        // alternative using iterable
        this.all().filter(c -> Objects.isNull(board.getCell(c)))
            .forEach(pos -> board.setValue(pos, cf.ground(countAdjaxBombs(pos))));
    }

    @Override
    public int bombsSize() {
        return (int)Math.ceil(limit);
    }

    private int countAdjaxBombs(Coord pos) {
        return (int)this.adjaxOf(pos).stream().map(board::getCell)
            .filter(Objects::nonNull)
            .filter(CellsUtils::isBomb).count();
    }

    public void unveil(Coord pos) {
        this.hitted++;
        board.getCell(pos).reveal();
    }

    public void recursiveDiscoveryOf(Coord c) {
        unveil(c);
        Optional.of(c).map(board::getCell).filter(CellsUtils::isEmpty).filter(e -> !this.isOver(c))
            .ifPresent(h -> this.adjaxOf(c).stream()
                .map(board::getCell)
                .filter(CellsUtils::isVeiled)
                .filter(CellsUtils::isValuable)
                .map(board::getCoord)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .forEach(this::recursiveDiscoveryOf)
            );
    }

    private Set<Coord> adjaxOf(Coord pos) {
        return all().filter(not(pos::equals)).filter(pos::isAdjax).collect(Collectors.toSet());
    }

    private Stream<Coord> all() {
        return IntStream.range(0, board.bound())
            .boxed().flatMap(i -> IntStream.range(0, board.bound())
                .mapToObj(j -> new Coord(i, j)));
    }

    @Override
    public boolean isOver(Coord c) {
        return CellsUtils.isBomb(board.getCell(c));
    }

    @Override
    public int hitCount() {
        return this.hitted;
    }

    private Coord randomCoord() {
        Random rand = new Random();
        return new Coord(rand.nextInt(board.bound()), rand.nextInt(board.bound()));
    }


    
}
