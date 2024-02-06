package view.containers;

import java.awt.Component;

import view.components.ViewComponent;

public interface Container<G> {

    <C> void addToContainer(ViewComponent<C> compo);

    <C> void addToContainer(Container<C> container);

    void initializeContainer();

    void setCostrains(int rows, int columns);

    Size getDimension();

    void setContainer(G contains);

    G getContainer();

    Component unwrap();


}
