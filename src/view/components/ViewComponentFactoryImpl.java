package view.components;

import java.awt.*;

public class ViewComponentFactoryImpl implements ViewComponentFactory {


    @Override
    public ViewComponent<Component> swingComponent() {
        return new ViewComponentImpl<Component>();
    }
    
}
