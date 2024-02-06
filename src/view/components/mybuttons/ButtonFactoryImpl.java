package view.components.mybuttons;

import java.util.*;

import javax.swing.*;

public class ButtonFactoryImpl implements ButtonFactory{

    Map<JButton, ViewButton<JButton>> cache = new HashMap<>();

    @Override
    public ViewButton<JButton> cellSwingNewButton() {
        return new ViewButton<>(JButton::new);
    }

    @Override
    public ViewButton<JButton> cellSwingFromButton(JButton button) {
        if(cache.containsKey(button)) {
            return cache.get(button);
        }
        var added = new ViewButton<>(() -> button);
        this.cache.put(button, added);
        return added;
    }

    @Override
    public ViewButton<JButton> cellSwingTextButton(String text) {
        return new ViewButton<>(() -> new JButton(text));
    }
    
}
