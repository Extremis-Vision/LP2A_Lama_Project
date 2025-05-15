package Lama;

import java.time.LocalDate;
/**
 * This class represents a user in the game.
 * It includes the user's name and number of wins.
 */
public class User {
    public String name; // Name of the user
    public int wins = 0; // Number of wins by the user
    final private LocalDate dateofbirth;

    /**
     * Constructor for the User class.
     * Initializes the user's name.
     * @param name The name of the user.
     */
    public User(String name, LocalDate dateofbirth) {
        this.name = name;
        this.dateofbirth = dateofbirth;
    }


    /**
     * Calcule l'âge de l'utilisateur à partir de sa date de naissance.
     * @return l'âge de l'utilisateur en années.
     */
    public int getAge() {
        return LocalDate.now().getYear() - dateofbirth.getYear();
    }
    /**
     * Returns a string representation of the user.
     * @return String representation of the user.
     */
    @Override
    public String toString() {
        return "User {" +
                "name='" + name + '\'' +
                ", wins=" + wins +
                '}';
    }

    /**
     * Changes the name of the user.
     * @param name The new name of the user.
     */
    public void changeName(String name) {
        this.name = name;
    }

    /**
     * Gets the name of the user.
     * @return The name of the user.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the number of wins by the user.
     * @return The number of wins by the user.
     */
    public int getWins() {
        return this.wins;
    }

    /**
     * Increments the number of wins by the user.
     */
    public void addWin() {
        this.wins += 1;
    }
}
