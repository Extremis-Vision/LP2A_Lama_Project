package Deck;
import java.util.ArrayList;
import java.util.List;
import Card.Card;

public class Deck {
    protected List<Card> deck = new ArrayList<>();
    public Deck() {

    }

    public Card getCard(){
        if (deck.isEmpty()) {
            return null; // ou lancer une exception personnalisée
        }
        return deck.removeFirst();
    }

    public void addCard(Card card){
        deck.add(card);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Deck{deck=");
        for (Card card : deck) {
            sb.append(card).append(", \n");
        }
        if (!deck.isEmpty()) {
            // Enlever la dernière virgule et espace
            sb.setLength(sb.length() - 2);
        }
        sb.append("}");
        return sb.toString();
    }
}
