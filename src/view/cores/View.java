package view.cores;


import view.containers.Container;
import view.cores.windows.Window;

public interface View {

    void setWindow(Window window);

    void buildWindow();

    void closeWithMessage(String string);

    void messageBox(String string);

    void addMainPane(Container pane);
}
