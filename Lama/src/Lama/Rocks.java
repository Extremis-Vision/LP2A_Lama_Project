package Lama;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a collection of rocks in the game.
 * It includes methods to manage the rocks and calculate the score.
 */
public class Rocks {
    private List<Rock> rocks;
    private int score;

    /**
     * Constructor for the Rocks class.
     * Initializes an empty list of rocks and sets the score to 0.
     */
    public Rocks() {
        rocks = new ArrayList<>();
        score = 0;
    }

    /**
     * Gets a rock with the specified value and removes it from the list.
     * @param value The value of the rock to retrieve.
     * @return The rock with the specified value, or null if not found.
     */
    public Rock getRocks(int value) {
        for (int i = 0; i < rocks.size(); i++) {
            if (rocks.get(i).getValue() == value) {
                score -= value;
                return rocks.remove(i);
            }
        }
        return null;
    }

    /**
     * Adds a rock to the list and updates the score.
     * @param rock The rock to add.
     */
    public void addRock(Rock rock) {
        if (rock == null) {
            System.out.println("Rock is null, no addition performed.");
            return;
        }
        rocks.add(rock);
        score += rock.getValue();
    }

    /**
     * Gets the total score of the rocks.
     * @return The total score of the rocks.
     */
    public int getScore() {
        return score;
    }

    /**
     * Returns a string representation of the rocks.
     * @return String representation of the rocks.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Deck{ size :").append(this.rocks.size()).append(",deck=\n");
        for (Rock rock : this.rocks) {
            sb.append(rock).append(", \n");
        }
        if (!rocks.isEmpty()) {
            sb.setLength(sb.length() - 2);
        }
        sb.append("}");
        return sb.toString();
    }

    public List<Rock> getRocksList() {
        return rocks;
    }
}