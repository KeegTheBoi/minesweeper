package model.cells;

public interface CellFactory {

    Cell mine();

    Cell ground(int value);
}
