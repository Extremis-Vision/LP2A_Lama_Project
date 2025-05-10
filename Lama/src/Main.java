import Player.Player;
import Rocks_Game.Rocks_Game;
import Stack.Stack;
import Deck.Deck;
import java.util.ArrayList;
import java.util.Scanner;
import Card.Card;
import Bot.Bot;
import Game.Game;

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
