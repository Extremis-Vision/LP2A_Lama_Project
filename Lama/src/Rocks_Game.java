package Rocks_Game;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import Rock.Rock;
import Rocks.Rocks;

public class Rocks_Game extends Rocks {
    public Rocks_Game() {
        super();
        for (int rockBlack = 1; rockBlack <= 20; rockBlack++) {
            rocks.add(new Rock("black"));
        }
        for (int rockWhite = 1; rockWhite <= 1; rockWhite++) {
            rocks.add(new Rock("white"));
        }
    }
}