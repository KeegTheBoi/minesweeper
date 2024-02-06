package view.components.mybuttons;

import java.util.*;

import javax.swing.*;

public class ButtonFactoryImpl implements ButtonFactory{

    Map<JButton, Button<JButton>> cache = new HashMap<>();

    @Override
    public Button<JButton> cellSwingNewButton() {
        return new Button<>(JButton::new);
    }

    @Override
    public Button<JButton> cellSwingFromButton(JButton button) {
        if(cache.containsKey(button)) {
            return cache.get(button);
        }
        var added = new Button<>(() -> button);
        this.cache.put(button, added);
        return added;
    }

    @Override
    public Button<JButton> cellSwingTextButton(String text) {
        return new Button<>(() -> new JButton(text));
    }
    
}
