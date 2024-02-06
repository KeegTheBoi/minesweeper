package view.cores.panels;

import view.cores.panels.panes.Pane;

public interface PanelFactory {
    
    <P> Pane<P> simplePanel();

    <P> Pane<P> gridPanel();

    <P> Pane<P> borderPanel();
}
