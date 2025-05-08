package Deck;
import java.util.ArrayList;
import java.util.List;
import Card.Card;

public class Deck {
    List<Card> deck = new ArrayList<>();


    public Deck(boolean true_false){
        if (true_false){
            for (int diffCard = 1; diffCard <= 7; diffCard++) {
                for (int numberCard = 1; numberCard <= 8; numberCard++){
                    if( diffCard == 7){
                        diffCard = 10;
                    }
                    deck.add(new Card(diffCard));
                }
            }
        }
    }

    public List<Card> getCards(){
        return this.deck;
    }

    public void addCard(Card card){
        deck.add(card);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Deck{deck=");
        for (Card card : deck) {
            sb.append(card).append(", ");
        }
        if (!deck.isEmpty()) {
            // Enlever la dernière virgule et espace
            sb.setLength(sb.length() - 2);
        }
        sb.append("}");
        return sb.toString();
    }
}
