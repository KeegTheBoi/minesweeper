package view.cores.panels.panes;

import view.containers.Container;

public interface PaneFactory {
    
    Container swingPane();

    Container swingGridPane(int rows, int columns);

    Container swingBorder();
}
