package view.components.mybuttons;

import javax.swing.*;

public interface ButtonFactory {

    Button<JButton> cellSwingNewButton();

    Button<JButton> cellSwingTextButton(String text);

    Button<JButton> cellSwingFromButton(JButton button);

    // <E> Button<E> cellJavaFXButton();

    
}
