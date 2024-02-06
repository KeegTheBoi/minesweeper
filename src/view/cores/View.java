package view.cores;

import view.containers.Size;

public interface View {

    void initWindow(Size size);

    void closeWithMessage(String string);

    void messageBox(String string);
}
