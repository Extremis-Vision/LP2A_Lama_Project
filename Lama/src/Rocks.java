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
            return rocks.remove(0);
        }
        else if (value == 10 && rocks.get(rocks.size() - 1).getValue() == 10) {
            return rocks.remove(rocks.size() - 1);
        }
        else {
            return rocks.remove(0);
        }
    }

    public void addRock(Rock rock) {
        if (rock == null) {
            System.out.println("Rock est null, aucun ajout effectué.");
            return;
        }
        rocks.add(rock);
        score += rock.getValue();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Rocks :");
        for (Rock rock : rocks) {
            sb.append(rock).append(", \n");
        }
        if (!rocks.isEmpty()) {
            // Enlever la dernière virgule et espace
            sb.setLength(sb.length() - 2);
        }
        sb.append("}");
        return sb.toString();
    }
}