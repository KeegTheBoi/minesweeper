package view.cores.panels;

import view.cores.panels.panes.Pane;

public class PanelFactoryImpl implements PanelFactory {

    @Override
    public <P> Pane<P> simplePanel() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'simplePanel'");
    }

    @Override
    public <G> Pane<G> gridPanel() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'gridPanel'");
    }

    @Override
    public <B> Pane<B> borderPanel() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'borderPanel'");
    }
    
}
