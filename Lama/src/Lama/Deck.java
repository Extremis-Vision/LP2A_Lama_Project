package Lama;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class represents a deck of cards in the game.
 */
public class Deck {
    protected List<Card> deck = new ArrayList<>(); // List to store the cards in the deck

    /**
     * Constructor for the Deck class.
     * Initializes an empty deck.
     */
    public Deck() {
        this.deck = new ArrayList<>();
    }

    /**
     * Gets the card at the specified index.
     * @param indice Index of the card to retrieve.
     * @return The card at the specified index, or null if the deck is empty.
     */
    public Card getCard(int indice) {
        if (deck.isEmpty()) {
            return null;
        }
        return deck.get(indice);
    }

    /**
     * Places the card at the specified index.
     * @param indice Index of the card to place.
     * @return The card that was placed, or null if the deck is empty.
     */
    public Card placeCard(int indice) {
        System.out.println("PlaceCard : " + this.deck.get(indice).getValue());
        if (deck.isEmpty()) {
            return null;
        }
        return deck.remove(indice);
    }

    /**
     * Adds a card to the deck.
     * @param card The card to add.
     */
    public void addCard(Card card) {
        deck.add(card);
    }

    /**
     * Shuffles the deck.
     */
    public void shuffle() {
        Collections.shuffle(deck);
    }

    /**
     * Checks if the deck is empty.
     * @return true if the deck is empty, false otherwise.
     */
    public boolean isEmpty() {
        return deck.isEmpty();
    }

    /**
     * Draws a card from the deck.
     * @return The card that was drawn, or null if the deck is empty.
     */
    public Card draw() {
        if (deck.isEmpty()) {
            return null;
        }
        return deck.remove(0);
    }

    /**
     * Gets the size of the deck.
     * @return The size of the deck.
     */
    public int getDeckSize() {
        return deck.size();
    }

    /**
     * Gets the last card in the deck.
     * @return The last card in the deck.
     */
    public Card getLastCard() {
        return getCard(getDeckSize() - 1);
    }

    /**
     * Removes the card at the specified index.
     * @param indice Index of the card to remove.
     */
    public void removeCard(int indice) {
        deck.remove(indice);
    }

    /**
     * Calculates the score of the deck.
     * @return The score of the deck.
     */
    public int getScore() {
        int score = 0;
        List<Card> temp = new ArrayList<>();
        for (Card card : deck) {
            boolean cardExists = false;

            for (Card existingCard : temp) {
                if (existingCard.getValue() == card.getValue()) {
                    cardExists = true;
                    break;
                }
            }

            if (!cardExists) {
                temp.add(card);
                score += card.getValue();
            }
        }
        return score;
    }

    /**
     * Returns a string representation of the deck.
     * @return String representation of the deck.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Deck{ size :").append(this.getDeckSize()).append(",deck=\n");
        for (Card card : deck) {
            sb.append(card).append(", \n");
        }
        if (!deck.isEmpty()) {
            sb.setLength(sb.length() - 2);
        }
        sb.append("}");
        return sb.toString();
    }
}
