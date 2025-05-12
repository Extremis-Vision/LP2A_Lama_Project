package Llama;

import java.util.ArrayList;
import java.util.Scanner;


public class Game {
    private ArrayList<Player> players_list;
    private ArrayList<Player> players_game;
    private Rocks_Game game_rocks;

    public Game() {
        players_list = new ArrayList<>();
        game_rocks = new Rocks_Game();
    }

    public void addPlayer(Player player) {
        players_list.add(player);
    }

    public void startGame() {
        boolean gameOver = false;
        Player loser = null;

        while (!gameOver) {
            initializeGame();
            pointAdding();

            for (Player player : players_list) {
                if (player.getRocks().getScore() >= 40) {
                    gameOver = true;
                    if (loser == null || player.getRocks().getScore() > loser.getRocks().getScore()) {
                        loser = player;
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

    private void initializeGame() {
        Stack pioche = new Stack();

        for (Player player : players_list) {
            player.clearDeck();
        }

        for (int i = 0; i < 6; i++) {
            for (Player player : players_list) {
                if (!pioche.isEmpty()) {
                    player.getDeck().addCard(pioche.draw());
                }
            }
        }

        Deck currentDeck = new Deck();
        currentDeck.addCard(pioche.draw());

        Scanner sc = new Scanner(System.in);
        players_game = new ArrayList<>(players_list);

        while (!isFinished(currentDeck)) {
            for (int player_id = 0; player_id < players_game.size(); player_id++) {
                boolean player_played = false;
                Player player = players_game.get(player_id);
                while (!player_played) {

                    String input;
                    if (player instanceof Bot) {
                        input = ((Bot) player).chooseAction(currentDeck, pioche);
                        System.out.println("Le bot " + player.getName() + " à choisie l'action : " + input);
                        System.out.println("\n" + player.getName() + ", voici votre deck :");
                        System.out.println("Carte jouée actuelle : " + currentDeck.getLastCard());
                    } else {
                        System.out.println("\n" + player.getName() + ", voici votre deck :");
                        System.out.println(player.getDeck());
                        System.out.println("Carte jouée actuelle : " + currentDeck.getLastCard());
                        System.out.println("Actions : Poser carte (P), Piocher (PC), Quitter (Q)");

                        input = sc.nextLine().toUpperCase();
                    }

                    switch (input) {
                        case "P":
                            int index;
                            if (player instanceof Bot) {
                                index = ((Bot) player).getPlayableCard(currentDeck.getLastCard());
                            } else {
                                System.out.println("Index de carte à jouer (0 à " + (player.getDeck().getDeckSize() - 1) + "):");
                                index = sc.nextInt();
                                sc.nextLine();
                            }

                            if (index >= 0 && index < player.getDeck().getDeckSize()) {
                                Card selected = player.getDeck().getCard(index);
                                if (selected.getValue() >= currentDeck.getLastCard().getValue() || (selected.getValue() == 1 && currentDeck.getLastCard().getValue() == 10) || (selected.getValue() == 10 && currentDeck.getLastCard().getValue() == 10) || (selected.getValue() == 6 && currentDeck.getLastCard().getValue() == 10)) {
                                    currentDeck.addCard(selected);
                                    player.getDeck().placeCard(index);
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
                                player.getDeck().addCard(pioche.draw());
                                System.out.println("Carte piochée !");
                                player_played = true;
                            } else {
                                System.out.println("La pioche est vide.");
                            }
                            break;

                        case "Q":
                            System.out.println(player.getName() + " a quitté la manche.");
                            players_game.remove(player_id);
                            player_id--;
                            player_played = true;
                            break;

                        default:
                            System.out.println("Choix invalide.");
                            break;
                    }

                    if (isFinished(currentDeck)) {
                        break;
                    }
                }
            }
        }

        System.out.println("Fin de la manche.");
        System.out.println("Nb carte restantes  " + pioche.getDeckSize());


        for (Player player : players_list) {
            System.out.println("\n" + player.getName() + ", voici votre deck :");
            System.out.println(player.getDeck());
        }


    }

    private void pointAdding() {
        for (Player player : players_list) {
            int score = player.getDeck().getScore();
            player.addPoints(game_rocks, score);
            System.out.println(player.getName() + " a maintenant " + player.getRocks().getScore());
        }
    }

    private boolean isFinished(Deck currentDeck) {
        boolean player_played = false;
        if (players_game.isEmpty()) player_played = true;

        for (Player player : players_game) {
            if (player.hasPlayableCard(currentDeck.getLastCard())) {
                player_played = false;
            }
        }


        return player_played;
    }
}