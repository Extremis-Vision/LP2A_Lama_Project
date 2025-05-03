package Card;

public class Card {
    private int value;
    private String name;

    public Card(int value, String name){
        this.value = value;
        this.name = name;
    }

    public String toString() {
        return "Card{" +
                "value=" + value +
                ", name='" + name + '\'' +
                '}';
    }
}
