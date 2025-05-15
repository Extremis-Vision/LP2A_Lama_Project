package Lama;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    final private ArrayList<Player> playersList; // List of all players in the game
    private ArrayList<Player> playersInGame; // List of players currently in the game
    final private RocksGame rocksGame; // Instance of the RocksGame
    final private Logs logs; // Instance of the Logs class

    /**
     * Constructor for the Game class.
     * Initializes the list of players, the RocksGame instance, and the Logs instance.
     */
    public Game() {
        playersList = new ArrayList<>();
        rocksGame = new RocksGame();
        logs = new Logs(); // Initialize the Logs instance
    }

    /**
     * Adds a player to the game.
     * @param player The player to add.
     */
    public void addPlayer(Player player) {
        if (playersList.size() < 7) {
            System.out.println("Player " + player.getName() + " has joined the game.");
            playersList.add(player);
        } else {
            System.out.println("The game is full. Cannot add more players.");
        }
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
            addPoints();

            for (Player player : playersList) {
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
            for (Player player : playersList) {
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
        Stack drawPile = new Stack();

        for (Player player : playersList) {
            player.clearDeck();
        }

        for (int i = 0; i < 6; i++) {
            for (Player player : playersList) {
                if (!drawPile.isEmpty()) {
                    player.getDeck().addCard(drawPile.draw());
                }
            }
        }

        Deck currentDeck = new Deck();
        currentDeck.addCard(drawPile.draw());

        Scanner scanner = new Scanner(System.in);
        playersInGame = new ArrayList<>(playersList);
        playersInGame.sort((p1, p2) -> Integer.compare(p1.getAge(), p2.getAge()));

        while (!isFinished(currentDeck)) {
            for (int playerId = 0; playerId < playersInGame.size(); playerId++) {
                boolean playerPlayed = false;
                Player player = playersInGame.get(playerId);
                while (!playerPlayed) {

                    String input;
                    if (player instanceof Bot) {
                        input = ((Bot) player).chooseAction(currentDeck, drawPile);
                        System.out.println("The bot " + player.getName() + " chose the action: " + input);
                        System.out.println("\n" + player.getName() + ", here is your deck:");
                        System.out.println("Current card played: " + currentDeck.getLastCard());
                    } else {
                        System.out.println("\n" + player.getName() + ", here is your deck:");
                        System.out.println(player.getDeck());
                        System.out.println("Current card played: " + currentDeck.getLastCard());
                        System.out.println("Actions: Play card (P), Draw (PC), Quit (Q)");

                        input = scanner.nextLine().toUpperCase();
                    }

                    switch (input) {
                        case "P":
                            int index;
                            if (player instanceof Bot) {
                                index = ((Bot) player).getPlayableCard(currentDeck.getLastCard());
                            } else {
                                System.out.println("Index of card to play (0 to " + (player.getDeck().getDeckSize() - 1) + "):");
                                index = scanner.nextInt();
                                scanner.nextLine();
                            }

                            if (index >= 0 && index < player.getDeck().getDeckSize()) {
                                Card selected = player.getDeck().getCard(index);
                                if (selected.getValue() >= currentDeck.getLastCard().getValue() ||
                                        (selected.getValue() == 1 && currentDeck.getLastCard().getValue() == 10) ||
                                        (selected.getValue() == 10 && currentDeck.getLastCard().getValue() == 10) ||
                                        (selected.getValue() == 6 && currentDeck.getLastCard().getValue() == 10)) {
                                    currentDeck.addCard(selected);
                                    player.getDeck().placeCard(index);
                                    playerPlayed = true;
                                } else {
                                    System.out.println("Invalid card!");
                                }
                            } else {
                                System.out.println("Invalid index.");
                            }
                            break;

                        case "PC":
                            if (!drawPile.isEmpty()) {
                                player.getDeck().addCard(drawPile.draw());
                                System.out.println("Card drawn!");
                                playerPlayed = true;
                            } else {
                                System.out.println("The draw pile is empty.");
                            }
                            break;

                        case "Q":
                            System.out.println(player.getName() + " has left the round.");
                            playersInGame.remove(playerId);
                            playerId--;
                            playerPlayed = true;
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
        System.out.println("Number of remaining cards: " + drawPile.getDeckSize());

        for (Player player : playersList) {
            System.out.println("\n" + player.getName() + ", here is your deck:");
            System.out.println(player.getDeck());
        }
    }

    /**
     * Adds points to the players based on their deck score.
     */
    private void addPoints() {
        for (Player player : playersList) {
            int score = player.getDeck().getScore();
            player.addPoints(rocksGame, score);
            System.out.println(player.getName() + " now has " + player.getRocks().getScore());
        }
    }

    /**
     * Checks if the game is finished.
     * @param currentDeck The current deck of cards.
     * @return true if the game is finished, false otherwise.
     */
    private boolean isFinished(Deck currentDeck) {
        boolean playerPlayed = false;
        if (playersInGame.isEmpty()) playerPlayed = true;

        for (Player player : playersInGame) {
            if (player.hasPlayableCard(currentDeck.getLastCard())) {
                playerPlayed = false;
            }
        }

        return playerPlayed;
    }
}
