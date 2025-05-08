import java.io.*;
import Deck.Deck;

public class testDeck {

    public static void main(String[] args) {
        Deck deck = new Deck(true);
        System.out.println(deck);
        deck.shuffleDeck();
        System.out.println("Deck size: " + deck);
        Deck utilisateurDeck = new Deck();
        utilisateurDeck.addCard(deck.getCard());
        System.out.println("Deck" + utilisateurDeck);
    }
}
