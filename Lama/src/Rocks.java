package Rocks;
import java.util.HashMap;
import Rock.Rock;

public class Rocks {
    private HashMap<Integer, Rock> rocks;
    private int score;

    public Rocks() {
        rocks = new HashMap<>();
        score = 0;
    }

    public Rock getRocks(int value) {
        return rocks.remove(value);
    }

    public void addRock(Rock rock) {
        if (rock == null) {
            System.out.println("Rock est null, aucun ajout effectué.");
            return;
        }
        rocks.put(rock.getValue(),rock);
        score += rock.getValue();
    }

    public int getScore() {
        return score;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Rocks :");
        for (Rock rock : rocks.values()) {
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