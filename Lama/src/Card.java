package Card;

public class Card {
    private int value;
    private String name;
    private String PathtoImage;

    public Card(int value) {
        if (value == 1) {
            this.value = value;
            this.name = "One";
            this.PathtoImage = "../../Llama Ressources/Llama Card Images (added card back)/Card - 1.png";
        } else if (value == 2) {
            this.value = value;
            this.name = "Two";
            this.PathtoImage = "../../Llama Ressources/Llama Card Images (added card back)/Card - 2.png";
        } else if (value == 3) {
            this.value = value;
            this.name = "Three";
            this.PathtoImage = "../../Llama Ressources/Llama Card Images (added card back)/Card - 3.png";
        } else if (value == 4) {
            this.value = value;
            this.name = "Four";
            this.PathtoImage = "../../Llama Ressources/Llama Card Images (added card back)/Card - 4.png";
        } else if (value == 5) {
            this.value = value;
            this.name = "Five";
            this.PathtoImage = "../../Llama Ressources/Llama Card Images (added card back)/Card - 5.png";
        } else if (value == 6) {
            this.value = value;
            this.name = "Six";
            this.PathtoImage = "../../Llama Ressources/Llama Card Images (added card back)/Card - 6.png";
        } else if (value == 10) {
            this.value = 10;
            this.name = "llama";
            this.PathtoImage = "../../Llama Ressources/Llama Card Images (added card back)/Card - 10.png";
        }
        else {
            System.out.println("Error");
        }
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
