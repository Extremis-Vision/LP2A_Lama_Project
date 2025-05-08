package Player;
import User.User;

public class Player extends User {
    private int wins;

    public Player(String name) {
        super(name);
    }

    public String toString() {
        return "Player{" +
                "name='" + super.name + '\'' +
                ", wins=" + super.wins +
                '}';
    }

    public void newGame(){
        this.wins++;
    }

    public int getWins(){
        return this.wins;
    }
}
