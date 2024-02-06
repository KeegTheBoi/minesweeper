package view.components.mybuttons;

import javax.swing.*;

public interface ButtonFactory {

    ViewButton<JButton> cellSwingNewButton();

    ViewButton<JButton> cellSwingTextButton(String text);

    ViewButton<JButton> cellSwingFromButton(JButton button);

    // <E> Button<E> cellJavaFXButton();

    
}
