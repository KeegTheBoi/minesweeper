package model.cells;

import java.util.Optional;

public class CellFactoryImpl implements CellFactory {

    @Override
    public Cell mine() {
        return new CellImpl(Type.MINE, Optional.empty());
    }

    @Override
    public Cell ground(int value) {
        return new CellImpl(Type.GROUND, Optional.of(value));
    }

    private class CellImpl extends AbstractCell{

        private Type type;
        private Optional<Integer> count;

        private CellImpl(Type t, Optional<Integer> count) {
            super(Visible.INVISIBLE);
            this.type = t;
            this.count = count;
        }
        @Override
        public Type getType() {
            return this.type;
        }

        @Override
        public Optional<Integer> getCount() {
            return this.count;
        }

    }
    
}
