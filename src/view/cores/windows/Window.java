package view.cores.windows;

import view.containers.Size;

public interface Window {

    void close();

    void display();

    void message(String string);

    void exit();

    void setScene();

    Size getSize();


    
} 
