package Card;

public class Card {
    private int value;
    private String name;
    private String PathtoImage;

    public Card(int value, String name) {
        this.value = value;
        this.name = name;
    }

    // Getter pour value
    public int getValue() {
        return value;
    }

    // Setter pour value
    public void setValue(int value) {
        this.value = value;
    }

    // Getter pour name
    public String getName() {
        return name;
    }

    // Setter pour name
    public void setName(String name) {
        this.name = name;
    }

    // Getter pour PathtoImage
    public String getPathtoImage() {
        return PathtoImage;
    }

    // Setter pour PathtoImage
    public void setPathtoImage(String PathtoImage) {
        this.PathtoImage = PathtoImage;
    }

    @Override
    public String toString() {
        return "Card{" +
                "value=" + value +
                ", name='" + name + '\'' +
                ", PathtoImage='" + PathtoImage + '\'' +
                '}';
    }
}
