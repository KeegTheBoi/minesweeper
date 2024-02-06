package view.components.mybuttons;

import java.util.function.*;

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
        action.accept(this.unwrap());
    }



    
}
