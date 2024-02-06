package view.cores.panels.panes;

import java.awt.Component;

import view.components.ViewComponent;
import view.containers.Container;
import view.containers.Size;

public class Pane<P> implements Container<P>{

    @Override
    public <C> void addToContainer(ViewComponent<C> compo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addToContainer'");
    }

    @Override
    public <C> void addToContainer(Container<C> container) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addToContainer'");
    }

    @Override
    public void initializeContainer() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'initializeContainer'");
    }

    @Override
    public void setCostrains(int rows, int columns) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setCostrains'");
    }

    @Override
    public Size getDimension() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDimension'");
    }

    @Override
    public void setContainer(P contains) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setContainer'");
    }

    @Override
    public P getContainer() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getContainer'");
    }

    @Override
    public Component unwrap() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'unwrap'");
    }

}
