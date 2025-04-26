package PLayer;

public class Player {
    private String name;
    private int wins;

    public Player(String name){
        this.name = name;
    }

    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", wins=" + wins +
                '}';
    }

    public void changeName(String name) {
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void addWin(){
        this.wins++;
    }


}
