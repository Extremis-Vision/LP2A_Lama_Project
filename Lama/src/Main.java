import Llama.*;

public class Main {
    public static void main(String[] args) {

        Game game = new Game();



        // Création des joueurs
        game.addPlayer(new Bot("Bob",1));
        game.addPlayer(new Bot("Alice", 1));
        game.addPlayer(new Bot("Charlie",1));

        game.startGame();

    }
}
