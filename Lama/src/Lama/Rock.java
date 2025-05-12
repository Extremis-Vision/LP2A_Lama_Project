package Lama;

import java.awt.Color;

/**
 * This class represents a rock in the game.
 * Each rock has a value and a color.
 */
public class Rock {
    private int value; // Numerical value of the rock
    private Color color; // Color of the rock

    /**
     * Constructor for the Rock class.
     * Initializes the value and color of the rock based on the color passed as a parameter.
     * @param color Color of the rock ("black" or "white").
     * @throws IllegalArgumentException if the color is not "black" or "white".
     */
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

    /**
     * Gets the value of the rock.
     * @return Numerical value of the rock.
     */
    public int getValue() {
        return value;
    }

    /**
     * Sets the value of the rock.
     * @param value New numerical value of the rock.
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Gets the color of the rock.
     * @return Color of the rock.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Sets the color of the rock.
     * @param color New color of the rock.
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Returns a string representation of the rock.
     * @return String representation of the rock.
     */
    @Override
    public String toString() {
        return "Rock{" +
                "value=" + value +
                ", color=" + color +
                '}';
    }
}
