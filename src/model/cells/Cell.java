package model.cells;

import java.util.*;

public interface Cell {

    Type getType();

    Visible getStatus();

    Optional<Integer> getCount();

    boolean isFlagged();

    void reveal();

    void changeFlag();
}
