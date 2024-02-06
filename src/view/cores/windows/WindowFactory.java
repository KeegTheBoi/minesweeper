package view.cores.windows;

import view.containers.Container;
import view.containers.Size;

public interface WindowFactory {

    Window swingFrame(Size size, Container defaultPane);
    
}
