package Lama;

/**
 * This class represents a card in the game.
 * Each card has a value, a name, and a path to an image.
 */
public class Card {
    private int value; // Numerical value of the card
    private String name; // Name of the card
    private String PathtoImage; // Path to the card's image

    /**
     * Constructor for the Card class.
     * Initializes the value and name of the card based on the value passed as a parameter.
     * @param value Numerical value of the card.
     */
    public Card(int value) {
        if (value == 1) {
            this.value = value;
            this.name = "One";
        } else if (value == 2) {
            this.value = value;
            this.name = "Two";
        } else if (value == 3) {
            this.value = value;
            this.name = "Three";
        } else if (value == 4) {
            this.value = value;
            this.name = "Four";
        } else if (value == 5) {
            this.value = value;
            this.name = "Five";
        } else if (value == 6) {
            this.value = value;
            this.name = "Six";
        } else if (value == 10) {
            this.value = 10;
            this.name = "llama";
        } else {
            System.out.println("Error");
        }
    }

    /**
     * Gets the value of the card.
     * @return Numerical value of the card.
     */
    public int getValue() {
        return value;
    }

    /**
     * Sets the value of the card.
     * @param value New numerical value of the card.
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Gets the name of the card.
     * @return Name of the card.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the card.
     * @param name New name of the card.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the path to the card's image.
     * @return Path to the card's image.
     */
    public String getPathtoImage() {
        return PathtoImage;
    }

    /**
     * Sets the path to the card's image.
     * @param PathtoImage New path to the card's image.
     */
    public void setPathtoImage(String PathtoImage) {
        this.PathtoImage = PathtoImage;
    }

    /**
     * Returns a string representation of the card.
     * @return String representation of the card.
     */
    @Override
    public String toString() {
        return "Card{" +
                "value=" + value +
                ", name='" + name + '\'' +
                '}';
    }
}
