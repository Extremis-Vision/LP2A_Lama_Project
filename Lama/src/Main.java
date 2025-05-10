package Card;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import Bot.Bot;
import Player.Player;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static Game game;

    private static void createAndShowGUI() {
        // Créer la fenêtre principale
        JFrame frame = new JFrame("Gestion des Joueurs");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        // Créer un panneau principal
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Liste pour stocker les joueurs
        ArrayList<Player> players = new ArrayList<>();

        // Panneau pour ajouter des joueurs
        JPanel addPlayerPanel = new JPanel();
        addPlayerPanel.setLayout(new GridLayout(5, 2));

        JTextField nameField = new JTextField();
        JTextField birthDateField = new JTextField();
        JComboBox<Integer> playerCountComboBox = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5, 6});

        addPlayerPanel.add(new JLabel("Nom du joueur:"));
        addPlayerPanel.add(nameField);
        addPlayerPanel.add(new JLabel("Date de naissance (YYYY-MM-DD):"));
        addPlayerPanel.add(birthDateField);
        addPlayerPanel.add(new JLabel("Nombre total de joueurs:"));
        addPlayerPanel.add(playerCountComboBox);

        JButton addButton = new JButton("Ajouter Joueur");
        addPlayerPanel.add(addButton);

        JButton startGameButton = new JButton("Lancer le Jeu");
        addPlayerPanel.add(startGameButton);

        // Zone de texte pour afficher les joueurs ajoutés
        JTextArea playersArea = new JTextArea();
        playersArea.setEditable(false);

        // Ajouter les composants au panneau principal
        panel.add(addPlayerPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(playersArea), BorderLayout.CENTER);

        // Action pour ajouter un joueur
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String birthDate = birthDateField.getText();

                if (!name.isEmpty() && !birthDate.isEmpty()) {
                    Player player = new Player(name, birthDate);
                    players.add(player);
                    updatePlayersArea(playersArea, players);
                    nameField.setText("");
                    birthDateField.setText("");
                } else {
                    JOptionPane.showMessageDialog(frame, "Veuillez entrer un nom et une date de naissance valides.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Action pour lancer le jeu
        startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int totalPlayers = (Integer) playerCountComboBox.getSelectedItem();
                if (players.size() < totalPlayers) {
                    // Ajouter des bots pour atteindre le nombre total de joueurs
                    for (int i = players.size(); i < totalPlayers; i++) {
                        players.add(new Bot("Bot" + (i + 1), 1));
                    }
                }

                // Afficher les noms des joueurs dans la zone de texte
                updatePlayersArea(playersArea, players);
                System.out.println(players.toString());

                // Initialiser et démarrer le jeu
                game = new Game(players);
                game.startGame();
            }
        });

        // Ajouter le panneau à la fenêtre
        frame.add(panel);

        // Centrer la fenêtre sur l'écran
        frame.setLocationRelativeTo(null);

        // Afficher la fenêtre
        frame.setVisible(true);
    }

    private static void updatePlayersArea(JTextArea playersArea, ArrayList<Player> players) {
        playersArea.setText("Liste des joueurs:\n");
        for (Player player : players) {
            playersArea.append("Nom: " + player.getName() + "\n");
        }
    }
}
