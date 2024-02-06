package view.components;

import java.awt.*;

public interface ViewComponentFactory {

    ViewComponent<Component> swingComponent();

    // ViewComponent javafxComponent();
}
