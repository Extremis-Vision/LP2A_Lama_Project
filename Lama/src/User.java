package User;

public class User {
    public String name;
    public int wins = 0;

    public User(String name){
        this.name = name;
    }

    public String toString() {
        return "User {" +
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
}
