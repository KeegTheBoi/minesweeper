package view.components;

import java.util.function.Supplier;

public class ViewComponentImpl<V> implements ViewComponent<V>{
    protected String text;
    private boolean enable;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((text == null) ? 0 : text.hashCode());
        result = prime * result + (enable ? 1231 : 1237);
        result = prime * result + ((bgColor == null) ? 0 : bgColor.hashCode());
        result = prime * result + ((fgColor == null) ? 0 : fgColor.hashCode());
        result = prime * result + ((supplier == null) ? 0 : supplier.hashCode());
        return result;
    }

    public boolean isDisable() {
        return enable;
    }

    private String bgColor;
    public String getBGColor() {
        return bgColor;
    }

    private String fgColor;
    protected Supplier<V> supplier;
    private int id;

    public int getId() {
        return id;
    }

    public String getFGColor() {
        return fgColor;
    }

    public ViewComponentImpl(){ 
    }

    public void setID(int i) {
        this.id = i;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public void setDisable(boolean enable) {
        this.enable = enable;
    }

    @Override
    public void setBGColor(String color) {
        this.bgColor = color;
    }

    @Override
    public void setFGColor(String color) {
        this.fgColor = color;
    }

    @Override
    public String getText() {
        return this.text;
    }

    @Override
    public V unwrap() {
        return supplier.get();
    }
    
    
}
