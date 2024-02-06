package view.cores.windows;

import view.containers.Container;
import view.containers.Size;

public interface Window {

    void close();

    void display();

    void message(String string);

    void exit();

    void setScene(Container container);

    Size getSize();

    void init();

    
} 
