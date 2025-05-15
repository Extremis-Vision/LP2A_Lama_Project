package Lama;
import java.time.LocalDate;

/**
 * This class represents a player in the game.
 * It extends the User class and includes a deck of cards and rocks.
 */
public class Player extends User {
    private Deck deck; // The player's deck of cards
    protected Rocks rocks; // The player's collection of rocks

    /**
     * Constructor for the Player class.
     * Initializes the player's name, deck, and rocks.
     * @param name The name of the player.
     */
    public Player(String name, LocalDate dateofbirth) {
        super(name, dateofbirth);
        this.deck = new Deck();
        this.rocks = new Rocks();
    }

    /**
     * Returns a string representation of the player.
     * @return String representation of the player.
     */
    @Override
    public String toString() {
        return "Player{" +
                "name='" + super.name + '\'' +
                ", deck=" + deck +
                ", rocks=" + rocks +
                '}';
    }

    /**
     * Gets the player's deck of cards.
     * @return The player's deck of cards.
     */
    public Deck getDeck() {
        return deck;
    }

    /**
     * Gets the player's collection of rocks.
     * @return The player's collection of rocks.
     */
    public Rocks getRocks() {
        return rocks;
    }

    /**
     * Adds points to the player's rocks based on the score.
     * @param game The game instance to get rocks from.
     * @param score The score to convert to rocks.
     */
    public void addPoints(RocksGame game, int score) {
        int currentWhites = 0;
        for (Rock rock : rocks.getRocksList()) {
            if (rock.getValue() == 1) {
                currentWhites++;
            }
        }

        int numberWhite = score % 10;
        int totalWhites = currentWhites + numberWhite;

        if (totalWhites >= 10) {
            for(int i = 0; i < currentWhites; i++) {
                rocks.getRocks(1);
            }

            Rock blackRock = game.getRocks(10);
            if (blackRock != null) {
                rocks.addRock(blackRock);
            }
            numberWhite = totalWhites - 10;
        }

        for (int i = 0; i < numberWhite; i++) {
            Rock rock = game.getRocks(1);
            if (rock != null) {
                rocks.addRock(rock);
            }
        }

        for (int i = 0; i < score / 10; i++) {
            Rock rock = game.getRocks(10);
            if (rock != null) {
                rocks.addRock(rock);
            }
        }
    }

    /**
     * Clears the player's deck of cards.
     */
    public void clearDeck() {
        this.deck = new Deck();
    }

    /**
     * Checks if the player has a playable card based on the current card.
     * @param currentCard The current card to compare against.
     * @return true if the player has a playable card, false otherwise.
     */
    public boolean hasPlayableCard(Card currentCard) {
        if (deck.isEmpty()) {
            return false;
        }
        for (int i = 0; i < deck.getDeckSize(); i++) {
            Card card = deck.getCard(i);
            if ((card.getValue() >= currentCard.getValue()) ||
                    (card.getValue() == 1 && currentCard.getValue() == 10) ||
                    (card.getValue() == 10 && currentCard.getValue() == 10) ||
                    (card.getValue() == 6 && currentCard.getValue() == 10)) {
                return true;
            }
        }
        return false;
    }
}
