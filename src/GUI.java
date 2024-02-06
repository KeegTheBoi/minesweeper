import view.cores.menu.MenuFactory;
import view.cores.menu.MenuFactoryImpl;

public class GUI {

    public static void start() {
        MenuFactory menuFactory = new MenuFactoryImpl();
        menuFactory.swingMenuView();
    }
}
