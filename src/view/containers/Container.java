package view.containers;


import view.components.ViewComponent;

public interface Container {

    <C> void addToContainer(ViewComponent<C> compo);

    void addToContainer(Container container);

    void initializeContainer();

    void setCostrains(int rows, int columns);

    Size getDimension();

    Object unwrap();

}
