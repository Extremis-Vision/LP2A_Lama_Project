package Player;

import Card.Card;
import User.User;
import Deck.Deck;
import Rocks.Rocks;
import Rocks_Game.Rocks_Game;

import java.util.ArrayList;

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

    public void addPoints(Rocks_Game game, int score) {
        int numberWhite = score%10;
        int numberBlack = score/10;
        for (int whitenumber = 0; whitenumber < numberWhite; whitenumber++) {
            Rock.Rock rock = game.getRocks(1);
            if (rock != null) {
                rocks.addRock(rock);
            }
        }
        for (int blacknumber = 0; blacknumber < numberBlack; blacknumber++) {
            Rock.Rock rock = game.getRocks(10);
            if (rock != null) {
                rocks.addRock(rock);
            }
        }
    }



    public void clearDeck() {
        this.deck = new Deck();
    }

    public boolean hasPlayableCard(Card currentCard) {
        for (int i = 0; i < deck.getDeckSize(); i++) {
            Card card = deck.getCard(i);
            if ((card.getValue() >= currentCard.getValue()) || (card.getValue() == 1 && currentCard.getValue() == 10) || (card.getValue() == 10 && currentCard.getValue() == 10) || (card.getValue() == 6 && currentCard.getValue() == 10)) {
                return true;
            }
        }
        return false;
    }
}
