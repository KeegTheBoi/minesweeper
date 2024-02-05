package model.cells;
import java.util.*;

public abstract class AbstractCell implements Cell{

    private Visible visibility;
    protected boolean flag;

    public AbstractCell(Visible status) {
        this.visibility = status;
    }

    public abstract Type getType();

    public Visible getStatus() {
        return visibility;
    }

    public boolean isFlagged() {
        return this.flag;
    }

    public void reveal() {
        visibility = Visible.VISIBLE;
    }

    public void changeFlag() {
        this.flag = !this.flag;
    }

    public abstract Optional<Integer> getCount();

}
