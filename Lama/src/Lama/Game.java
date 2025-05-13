package Lama;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private ArrayList<Player> players_list; // List of all players in the game
    private ArrayList<Player> players_game; // List of players currently in the game
    private Rocks_Game game_rocks; // Instance of the Rocks_Game
    private Logs logs; // Instance of the Logs class

    /**
     * Constructor for the Game class.
     * Initializes the list of players, the Rocks_Game instance, and the Logs instance.
     */
    public Game() {
        players_list = new ArrayList<>();
        game_rocks = new Rocks_Game();
        logs = new Logs(); // Initialize the Logs instance
    }

    /**
     * Adds a player to the game.
     * @param player The player to add.
     */
    public void addPlayer(Player player) {
        players_list.add(player);
    }

    /**
     * Starts the game.
     */
    public void startGame() {
        boolean gameOver = false;
        Player loser = null;
        ArrayList<String> gameLogs = new ArrayList<>();
        String winner = null;
        int winnerScore = Integer.MAX_VALUE; // Initialize with a high value

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
                System.out.println("\n--- New round! ---\n");
            }
        }

        if (loser != null) {
            String loserMessage = "The loser is " + loser.getName() + " with " + loser.getRocks().getScore() + " points!";
            System.out.println(loserMessage);
            gameLogs.add(loserMessage);

            // Determine the winner (assuming the player with the lowest score is the winner)
            for (Player player : players_list) {
                if (player.getRocks().getScore() < winnerScore) {
                    winner = player.getName();
                    winnerScore = player.getRocks().getScore();
                }
            }

            // Log the winner
            String winnerMessage = "The winner is " + winner + " with " + winnerScore + " points!";
            System.out.println(winnerMessage);
            gameLogs.add(winnerMessage);

            // Log the winner and losers
            logs.writeLogs(gameLogs, "game_logs.json");
        }
    }


    /**
     * Initializes the game by setting up the deck and players.
     */
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
                        System.out.println("The bot " + player.getName() + " chose the action: " + input);
                        System.out.println("\n" + player.getName() + ", here is your deck:");
                        System.out.println("Current card played: " + currentDeck.getLastCard());
                    } else {
                        System.out.println("\n" + player.getName() + ", here is your deck:");
                        System.out.println(player.getDeck());
                        System.out.println("Current card played: " + currentDeck.getLastCard());
                        System.out.println("Actions: Play card (P), Draw (PC), Quit (Q)");

                        input = sc.nextLine().toUpperCase();
                    }

                    switch (input) {
                        case "P":
                            int index;
                            if (player instanceof Bot) {
                                index = ((Bot) player).getPlayableCard(currentDeck.getLastCard());
                            } else {
                                System.out.println("Index of card to play (0 to " + (player.getDeck().getDeckSize() - 1) + "):");
                                index = sc.nextInt();
                                sc.nextLine();
                            }

                            if (index >= 0 && index < player.getDeck().getDeckSize()) {
                                Card selected = player.getDeck().getCard(index);
                                if (selected.getValue() >= currentDeck.getLastCard().getValue() ||
                                        (selected.getValue() == 1 && currentDeck.getLastCard().getValue() == 10) ||
                                        (selected.getValue() == 10 && currentDeck.getLastCard().getValue() == 10) ||
                                        (selected.getValue() == 6 && currentDeck.getLastCard().getValue() == 10)) {
                                    currentDeck.addCard(selected);
                                    player.getDeck().placeCard(index);
                                    player_played = true;
                                } else {
                                    System.out.println("Invalid card!");
                                }
                            } else {
                                System.out.println("Invalid index.");
                            }
                            break;

                        case "PC":
                            if (!pioche.isEmpty()) {
                                player.getDeck().addCard(pioche.draw());
                                System.out.println("Card drawn!");
                                player_played = true;
                            } else {
                                System.out.println("The draw pile is empty.");
                            }
                            break;

                        case "Q":
                            System.out.println(player.getName() + " has left the round.");
                            players_game.remove(player_id);
                            player_id--;
                            player_played = true;
                            break;

                        default:
                            System.out.println("Invalid choice.");
                            break;
                    }

                    if (isFinished(currentDeck)) {
                        break;
                    }
                }
            }
        }

        System.out.println("End of the round.");
        System.out.println("Number of remaining cards: " + pioche.getDeckSize());

        for (Player player : players_list) {
            System.out.println("\n" + player.getName() + ", here is your deck:");
            System.out.println(player.getDeck());
        }
    }

    /**
     * Adds points to the players based on their deck score.
     */
    private void pointAdding() {
        for (Player player : players_list) {
            int score = player.getDeck().getScore();
            player.addPoints(game_rocks, score);
            System.out.println(player.getName() + " now has " + player.getRocks().getScore());
        }
    }

    /**
     * Checks if the game is finished.
     * @param currentDeck The current deck of cards.
     * @return true if the game is finished, false otherwise.
     */
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
