package Card;
import java.awt.Color;

public class Rock {
    private int value;
    private Color color;

    public Rock(int value, Color color) {
        this.value = value;
        this.color = color;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Rock{" +
                "value=" + value +
                ", color=" + color +
                '}';
    }
}
