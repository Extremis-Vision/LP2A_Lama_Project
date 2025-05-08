import java.io.*;
import Stack.Stack;
import Deck.Deck;

public class testDeck {
    public static void main(String[] args) {
        Stack deck = new Stack();
        System.out.println("Deck size: " + deck);
        Deck utilisateurDeck = new Deck();
        utilisateurDeck.addCard(deck.getCard());
        System.out.println("Deck" + utilisateurDeck);
    }
}
