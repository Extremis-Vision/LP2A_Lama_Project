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
        for (int diffCard = 1; diffCard <= 7; diffCard++) {
            for (int numberCard = 1; numberCard <= 8; numberCard++) {
                if (diffCard == 7) {
                    diffCard = 10;
                }
                deck.add(new Card(diffCard));
            }
        }
        this.shuffle();
    }
}
