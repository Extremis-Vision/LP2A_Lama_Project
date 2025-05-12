package Llama;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    protected List<Card> deck = new ArrayList<>();

    public Deck() {
        this.deck = new ArrayList<>();
    }

    public Card getCard(int indice) {
        if (deck.isEmpty()) {
            return null;
        }
        return deck.get(indice);
    }

    public Card placeCard(int indice) {
        System.out.println("PlaceCard : " + this.deck.get(indice).getValue());
        if (deck.isEmpty()) {
            return null;
        }
        return deck.remove(indice);
    }

    public void addCard(Card card) {
        deck.add(card);
    }

    public void shuffle() {
        Collections.shuffle(deck);
    }

    public boolean isEmpty() {
        return deck.isEmpty();
    }

    public Card draw() {
        if (deck.isEmpty()) {
            return null;
        }
        return deck.remove(0);
    }

    public int getDeckSize() {
        return deck.size();
    }

    public Card getLastCard() {
        return getCard(getDeckSize() - 1);
    }

    public void removeCard(int indice) {
        deck.remove(indice);
    }

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
