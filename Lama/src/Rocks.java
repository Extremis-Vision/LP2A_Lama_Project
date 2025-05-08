package Rocks;
import java.util.ArrayList;
import java.util.List;
import Rock.Rock;

public class Rocks {
    protected List<Rock> rocks = new ArrayList<>();
    protected int score = 0;

    public Rocks() {
    }

    public Rock getRocks(int value) {
        if (rocks.isEmpty()) {
            return null; // ou lancer une exception personnalisée
        }
        else if (value == 1 && rocks.get(0).getValue() == 1){
            return rocks.removeFirst();
        }
        else if (value == 10 && rocks.get(rocks.size() - 1).getValue() == 10) {
            return rocks.removeFirst();
        }
        else {
            return null;
        }
    }

    public void addRock(Rock rock) {
        rocks.add(rock);
    }
}