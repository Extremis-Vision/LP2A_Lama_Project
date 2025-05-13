package Lama;

/**
 * This class represents a bot player in the game.
 * It extends the Player class and includes a level attribute.
 */
public class Bot extends Player {
    private int level; // Level of the bot

    /**
     * Constructor for the Bot class.
     * Initializes the bot's name and level.
     * @param name Name of the bot.
     * @param level Level of the bot.
     */
    public Bot(String name, int level) {
        super(name, null);
        this.level = level;
    }

    /**
     * Gets the level of the bot.
     * @return Level of the bot.
     */
    public int getLevel() {
        return this.level;
    }

    /**
     * Finds a playable card in the bot's deck based on the current card.
     * @param currentCard The current card to compare against.
     * @return Index of the playable card, or -1 if no playable card is found.
     */
    public int getPlayableCard(Card currentCard) {
        for (int i = 0; i < getDeck().getDeckSize(); i++) {
            Card card = getDeck().getCard(i);
            if ((card.getValue() >= currentCard.getValue()) ||
                    (card.getValue() == 1 && currentCard.getValue() == 10) ||
                    (card.getValue() == 10 && currentCard.getValue() == 10) ||
                    (card.getValue() == 6 && currentCard.getValue() == 10)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Chooses an action for the bot based on the current deck and stack.
     * @param currentDeck The current deck of cards.
     * @param stack The current stack of cards.
     * @return The chosen action: "P" for play, "PC" for pick from stack, "Q" for quit.
     */
    public String chooseAction(Deck currentDeck, Stack stack) {
        if (this.hasPlayableCard(currentDeck.getLastCard())) {
            return "P";
        } else if (stack.getDeckSize() != 0) {
            return "PC";
        } else {
            return "Q";
        }
    }
}
