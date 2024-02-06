package view.cores;

import view.containers.Container;
import view.cores.windows.*;

public class ViewImpl implements View {

    protected Window window;
    protected Container panel;
    private boolean build;

    @Override
    public void buildWindow() {
        if(window == null || build) {
            throw new IllegalStateException();
        }
        this.window.display();
        build = true;
    }

    @Override
    public void closeWithMessage(String string) {
        this.messageBox(string);
        this.window.exit();
    }

    @Override
    public void messageBox(String string) {
        this.window.message(string);
    }

    @Override
    public void addMainPane(Container panel) {
        this.panel = panel;
    }

    @Override
    public void setWindow(Window window) {
        this.window = window;
    }
    
}
