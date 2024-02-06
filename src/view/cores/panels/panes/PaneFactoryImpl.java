package view.cores.panels.panes;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.JPanel;

import view.components.ViewComponent;
import view.containers.Container;
import view.containers.Size;

public class PaneFactoryImpl implements PaneFactory {

    @Override
    public Container swingPane() {
        return new SwingPane(null);
    }

    private class SwingPane implements Container {

        private JPanel panel;
        private Size size;
        private LayoutManager layout;

        public SwingPane(LayoutManager layout) {
            this.layout = layout;
            initializeContainer();
        }

        @Override
        public <C> void addToContainer(ViewComponent<C> compo) {
            Component comp = (Component)compo.unwrap();
            this.panel.add(comp);
        }

        @Override
        public void addToContainer(Container container) {
            Component comp = (Component)container.unwrap();
            this.panel.add(comp);
        }

        @Override
        public void initializeContainer() {
            this.panel = new JPanel(layout);
        }

        @Override
        public void setCostrains(int rows, int columns) {
            this.size = new Size(rows, columns);
        }

        @Override
        public Size getDimension() {
            return this.size;
        }

        @Override
        public Object unwrap() {
            return this.panel;
        }

        
    }

    @Override
    public Container swingGridPane(int rows, int columns) {
        return new SwingPane(new GridLayout(rows, columns));
    }

    @Override
    public Container swingBorder() {
        return new SwingPane(new BorderLayout());
    }

    
    
}
