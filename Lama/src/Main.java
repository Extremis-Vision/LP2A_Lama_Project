package Card;

import Player.Player;
import Rocks_Game.Rocks_Game;
import Stack.Stack;
import Deck.Deck;
import java.util.ArrayList;
import java.util.Scanner;
import Card.Card;
import Bot.Bot;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();

        // Création des joueurs
        game.addPlayer(new Bot("Bob", 1));
        game.addPlayer(new Player("Alice"));
        game.addPlayer(new Bot("Charlie", 1));

        // Démarrer le jeu
        game.startGame();
    }
}
