import Player.Player;
import Stack.Stack;
import Rocks_Game.Rocks_Game;
import Rock.Rock;

public class testPlayer {
    public static void main(String[] args){
        Player p1 = new Player("Léonard");

        System.out.println(p1);


        Stack deck = new Stack();
        for (int i = 0; i <=6 ; i++) {
            p1.getDeck().addCard(deck.getCard());
        }

        Rocks_Game rocksGame = new Rocks_Game();
        System.out.println(rocksGame);

        Rock rockWhite = rocksGame.getRocks(1);

        System.out.println(rockWhite);

        p1.getRocks().addRock(rockWhite);


        System.out.println(p1);
    }
}
