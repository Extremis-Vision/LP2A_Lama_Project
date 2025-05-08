package Player;

import Card.Card;
import User.User;
import Deck.Deck;
import Rocks.Rocks;

public class Player extends User {
    private Deck deck;
    protected Rocks rocks;

    public Player(String name) {
        super(name);
        this.deck = new Deck();
        this.rocks = new Rocks();
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + super.name + '\'' +
                ", deck=" + deck +
                ", rocks=" + rocks +
                '}';
    }

    public Deck getDeck() {
        return deck;
    }

    public Rocks getRocks() {
        return rocks;
    }

    public int getTotalPoints() {
        return rocks.getValue();  // Utiliser l'objet "rocks", pas la classe
    }

    public void clearDeck() {
        this.deck = new Deck();
    }

    public void addCard(Card card) {
        deck.addCard(card);
    }

    public void showDeck() {
        System.out.println(deck.toString());
    }

    public int getDeckSize() {
        return deck.getDeckSize();
    }

    public Card getCard(int index) {
        return deck.getCard(index);
    }

    public void removeCard(int index) {
        deck.removeCard(index);
    }

    public int calculatePoints() {
        return rocks.getValue();
    }

    public void addPoints(int points) {
        rocks.getRocks(points);
    }

    public boolean hasPlayableCard(Card currentCard) {
        for (int i = 0; i < deck.getDeckSize(); i++) {
            Card card = deck.getCard(i);
            if (card.getValue() >= currentCard.getValue()) {
                return true;
            }
        }
        return false;
    }
}
