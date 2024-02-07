package view.components;

public interface ViewComponent<V> {

    void setText(String text);

    void setDisable(boolean enable);

    void setBGColor(String color);

    void setFGColor(String color);

    String getFGColor();

    String getBGColor();

    String imagePath();

    void setImagePath(String string);

    String getText();

    boolean isDisable();

    V unwrap();
}
