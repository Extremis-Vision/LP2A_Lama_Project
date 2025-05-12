package Llama;
import java.util.ArrayList;
import java.util.List;

public class Rocks {
    private List<Rock> rocks;
    private int score;

    public Rocks() {
        rocks =  new ArrayList<>();
        score = 0;
    }

    public Rock getRocks(int value) {
        for (int i=0; i < rocks.size(); i++) {
            if (rocks.get(i).getValue() == value) {
                score -= value;
                return rocks.remove(i);
            }
        }

        return null;
    }

    public void addRock(Rock rock) {
        if (rock == null) {
            System.out.println("Rock est null, aucun ajout effectué.");
            return;
        }
        rocks.add(rock);
        score += rock.getValue();
    }

    public int getScore() {
        return score;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Deck{ size :").append(this.rocks.size()).append(",deck=\n");
        for (Rock rock : this.rocks) {
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