package Rock;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Rock {
    private int value;
    private Color color;

    public Rock(String color) {
        if (color.equals("black")) {
            this.value = 10;
            this.color = Color.BLACK;
        } else if (color.equals("white")) {
            this.value = 1;
            this.color = Color.WHITE;
        } else {
            throw new IllegalArgumentException("Invalid color: " + color);
        }
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
