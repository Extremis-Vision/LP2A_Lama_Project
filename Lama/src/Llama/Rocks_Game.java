package Llama;

public class Rocks_Game extends Rocks {
    public Rocks_Game() {
        super();
        for (int rockWhite = 1; rockWhite <= 50; rockWhite++) {
            this.addRock(new Rock("white"));
        }
        for (int rockBlack = 1; rockBlack <= 20; rockBlack++) {
            this.addRock(new Rock("black"));
        }
    }
}