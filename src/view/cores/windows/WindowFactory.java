package view.cores.windows;

import javax.swing.*;

import view.containers.Size;

public interface WindowFactory {

    Window<JFrame> swingFrame(Size size);
    
}
