package Lama;

import java.awt.Color;

/**
 * This class represents a rock in the game.
 * Each rock has a value and a color.
 */
public enum Rock {
    BLACK(10, Color.BLACK),
    WHITE(1, Color.WHITE);

    private final int value;
    private final Color color;

    /**
     * Constructor for the Rock enum.
     * Initializes the value and color of the rock.
     * @param value Numerical value of the rock
     * @param color Color of the rock
     */
    Rock(int value, Color color) {
        this.value = value;
        this.color = color;
    }

    /**
     * Gets the value of the rock.
     * @return Numerical value of the rock.
     */
    public int getValue() {
        return value;
    }

    /**
     * Gets the color of the rock.
     * @return Color of the rock.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Creates a Rock from a string color value.
     * @param color Color name ("black" or "white")
     * @return The corresponding Rock
     * @throws IllegalArgumentException if the color is invalid
     */
    public static Rock fromString(String color) {
        return switch (color.toLowerCase()) {
            case "black" -> BLACK;
            case "white" -> WHITE;
            default -> throw new IllegalArgumentException("Couleur invalide : " + color);
        };
    }

    /**
     * Returns a string representation of the rock.
     * @return String representation of the rock.
     */
    @Override
    public String toString() {
        return String.format("Rock{value=%d, color=%s}", value, color);
    }
}