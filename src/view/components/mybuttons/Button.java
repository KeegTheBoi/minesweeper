package view.components.mybuttons;

import java.util.function.*;

import javax.swing.JButton;

import java.util.*;

import view.components.ViewComponentImpl;

public class Button<B> extends ViewComponentImpl<B>{

    public Button(Supplier<B> supplier) {
        this.supplier = supplier;
    }

    public void triggerEvent(Consumer<B> action) {
        if(Objects.isNull(action)) {
            throw new IllegalStateException();
        }
        // JButton button = (JButton)this.unwrap();
        // button.setText("text");
        // System.out.println("Triggered");
        action.accept(this.unwrap());
    }



    
}
