package Lama;

/**
 * This class represents a card in the game.
 * Each card has a value, a name, and a path to an image.
 */
public enum Card {
    ONE(1, "One"),
    TWO(2, "Two"),
    THREE(3, "Three"),
    FOUR(4, "Four"),
    FIVE(5, "Five"),
    SIX(6, "Six"),
    LLAMA(10, "Llama");

    private final int value; // Numerical value of the card
    private final String name; // Name of the card
    private String pathToImage; // Path to the card's image

    /**
     * Constructor for the Card class.
     * Initializes the value and name of the card based on the value passed as a parameter.
     * @param value Numerical value of the card.
     * @param name Name of the card.
     */
    Card(int value, String name) {
        this.value = value;
        this.name = name;
    }

    /**
     * Gets the value of the card.
     * @return Numerical value of the card.
     */
    public int getValue() {
        return value;
    }

    /**
     * Gets the name of the card.
     * @return Name of the card.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the path to the card's image.
     * @return Path to the card's image.
     */
    public String getPathToImage() {
        return pathToImage;
    }

    /**
     * Sets the path to the card's image.
     * @param pathToImage New path to the card's image.
     */
    public void setPathToImage(String pathToImage) {
        this.pathToImage = pathToImage;
    }

    /**
     * Creates a Card from a numerical value.
     * @param value The value to convert to a Card
     * @return The corresponding Card
     * @throws IllegalArgumentException if the value is invalid
     */
    public static Card fromValue(int value) {
        for (Card card : values()) {
            if (card.value == value) {
                return card;
            }
        }
        throw new IllegalArgumentException("Valeur de carte invalide : " + value);
    }

    /**
     * Returns a string representation of the card.
     * @return String representation of the card.
     */
    @Override
    public String toString() {
        return String.format("Card{value=%d, name='%s'}", value, name);
    }
}