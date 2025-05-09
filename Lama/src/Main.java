package Card;

import Player.Player;
import Rocks_Game.Rocks_Game;
import Stack.Stack;
import Deck.Deck;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Player> players_list = new ArrayList<>();
        // Création des joueurs
        players_list.add(new Player("Alice"));
        players_list.add(new Player("Bob"));
        players_list.add(new Player("Charlie"));

        boolean gameOver = false;
        Player loser = null;

        Rocks_Game game_rocks = new Rocks_Game();

        // Boucle principale du jeu
        while (!gameOver) {
            initialize_game(players_list); // Initialise une nouvelle manche
            Decompter_point(players_list,game_rocks); // Calcule les points des joueurs

            // Vérification de la fin du jeu
            for (Player player : players_list) {
                if (player.getRocks().getScore() >= 40) {
                    gameOver = true;
                    if (loser == null || player.getRocks().getScore() > loser.getRocks().getScore()) {
                        loser = player; // Détermine le perdant
                    }
                }
            }

            if (!gameOver) {
                System.out.println("\n--- Nouvelle manche ! ---\n");
            }
        }

        if (loser != null) {
            System.out.println("Le perdant est " + loser.getName() + " avec " + loser.getRocks().getScore() + " points !");
        }
    }

    public static void initialize_game(ArrayList<Player> players_list) {
        Stack pioche = new Stack();

        // Vider les mains des joueurs
        for (Player player : players_list) {
            player.clearDeck();
        }

        // Distribution des cartes aux joueurs !!! en donnant une cartes après l'autres a chaque Joueur
        for (int i = 0; i < 6; i++) { // 6 cartes par joueur
            for (Player player : players_list) {
                if (!pioche.isEmpty()) {
                    player.getDeck().addCard(pioche.draw()); // Distribue une carte
                }
            }
        }

        Deck currentDeck = new Deck();

        currentDeck.addCard(pioche.draw()); // Ajoute la première carte au deck courant

        Scanner sc = new Scanner(System.in);

        ArrayList<Player> players_game = new ArrayList<>(players_list);

        // Boucle de jeu
        while (!isFinished(players_list, currentDeck)) {
            for (Player player : players_game) {
                boolean player_played = false;
                while (player_played == false) {
                    System.out.println("\n" + player.getName() + ", voici votre deck :");
                    System.out.println(player.getDeck()); // Affiche le deck du joueur

                    System.out.println("Carte jouée actuelle : " + currentDeck.getLastCard());
                    System.out.println("Actions : Poser carte (P), Piocher (PC), Quitter (Q)");
                    String input = sc.nextLine().toUpperCase();

                    switch (input) {
                        case "P":
                            System.out.println("Index de carte à jouer (0 à " + (player.getDeck().getDeckSize() - 1) + "):");
                            int index = sc.nextInt();
                            sc.nextLine();

                            if (index >= 0 && index < player.getDeck().getDeckSize()) {
                                Card selected = player.getDeck().getCard(index);
                                if (selected.getValue() >= currentDeck.getLastCard().getValue() || (selected.getValue() == 1 && currentDeck.getLastCard().getValue() == 10)) {
                                    currentDeck.addCard(selected); // Ajoute la carte au deck courant
                                    player.getDeck().placeCard(index); // Supprime la carte du deck du joueur

                                    player_played = true;
                                } else {
                                    System.out.println("Carte non valide !");
                                }
                            } else {
                                System.out.println("Index invalide.");
                            }
                            break;

                        case "PC":
                            if (!pioche.isEmpty()) {
                                player.getDeck().addCard(pioche.draw()); // Pioche une carte
                                System.out.println("Carte piochée !");
                                player_played = true;

                            } else {
                                System.out.println("La pioche est vide.");
                            }
                            break;

                        case "Q":
                            players_list.remove(player); // Retire le joueur de la liste
                            System.out.println(player.getName() + " a quitté la manche.");
                            player_played = true;
                            break;

                        default:
                            System.out.println("Choix invalide.");
                            break;
                    }

                    if (isFinished(players_list, currentDeck)) {
                        break; // Sort de la boucle si la manche est terminée
                    }
                }
            }
        }

        System.out.println("Fin de la manche.");
    }



    public static void Decompter_point(ArrayList<Player> players_list, Rocks_Game game_rocks) {
        for (Player player : players_list) {
            int score = player.getRocks().getScore(); // Calcule les points du joueur
            player.addPoints(game_rocks , score); // Ajoute les points au joueur
            System.out.println(player.getName() + " a maintenant " + player.getRocks().getScore() + " points.");
        }
    }

    public static boolean isFinished(ArrayList<Player> players_list, Deck currentDeck) {
        if (players_list.isEmpty()) return true; // Si aucun joueur, la manche est terminée

        for (Player player : players_list) {
            if (player.hasPlayableCard(currentDeck.getLastCard())) {
                return false; // Si un joueur a une carte jouable, la manche continue
            }
        }

        return true; // Sinon, la manche est terminée
    }
}
