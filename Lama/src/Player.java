package Player;
import User.User;
import Deck.Deck;
import Rocks.Rocks;

public class Player extends User {
    protected Deck deck;
    protected Rocks rocks;

    public Player(String name) {
        super(name);
        this.deck = new Deck();
        this.rocks = new Rocks();
    }

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
}
