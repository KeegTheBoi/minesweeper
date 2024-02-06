package view.cores.windows;

import view.containers.Size;

public interface Window<W> {

    void close();

    void display();

    void message(String string);

    void exit();

    void setScene(Object scene);

    Size getSize();

    
} 
