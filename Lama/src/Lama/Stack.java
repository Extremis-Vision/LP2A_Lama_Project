package Lama;

/**
 * This class represents a stack of cards in the game.
 * It extends the Deck class and initializes a stack with specific cards.
 */
public class Stack extends Deck {

    /**
     * Constructor for the Stack class.
     * Initializes the stack with specific cards and shuffles them.
     */
    public Stack() {
        super();
        for (Card cardType : Card.values()) {
            for (int numberCard = 1; numberCard <= 8; numberCard++) {
                deck.add(cardType);
            }
        }
        this.shuffle();
    }
}
