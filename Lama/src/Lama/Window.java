package Lama;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Window {
    private JFrame frame;
    private JSlider slider;
    private JTextField nameField;
    private JTextField birthDateField;
    private int sliderValue;
    private int nbPlayers = 0;
    private Game game;

    public Window(Game game) {
        this.game = game;
        initialize();
    }

    private void initialize() {
        // Créer une nouvelle fenêtre
        frame = new JFrame("Jeu du Lama LP2A");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Utiliser un layout pour organiser les composants
        frame.setLayout(new BorderLayout());

        // Panel pour le slider et son étiquette
        JPanel sliderPanel = new JPanel(new FlowLayout());

        // Ajouter une étiquette devant le slider
        JLabel sliderLabel = new JLabel("Nombre de joueurs: ");
        sliderPanel.add(sliderLabel);

        // Créer un slider
        slider = new JSlider(1, 6, 3); // Min: 1, Max: 6, Valeur initiale: 3
        slider.setMajorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                sliderValue = slider.getValue(); // Stocker la valeur du slider dans une variable
                System.out.println("Valeur du slider: " + sliderValue);
            }
        });

        sliderPanel.add(slider);

        // Panel pour ajouter un joueur
        JPanel addPlayerPanel = new JPanel(new FlowLayout());
        nameField = new JTextField(15);
        birthDateField = new JTextField(15);

        addPlayerPanel.add(new JLabel("Nom du joueur:"));
        addPlayerPanel.add(nameField);
        addPlayerPanel.add(new JLabel("Date de naissance (YYYY-MM-DD):"));
        addPlayerPanel.add(birthDateField);

        // Créer un bouton pour ajouter un joueur
        JButton addPlayerButton = new JButton("Ajouter Joueur");
        addPlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String birthDateStr = birthDateField.getText();
                try {
                    LocalDate birthDate = LocalDate.parse(birthDateStr);
                    // Ajouter le joueur au jeu
                    game.addPlayer(new Player(name, birthDate));
                    System.out.println("Joueur ajouté: " + name + ", Date de naissance: " + birthDate);
                    nbPlayers++;
                    // Clear fields after adding
                    nameField.setText("");
                    birthDateField.setText("");
                } catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(frame, "Format de date invalide. Utilisez YYYY-MM-DD.");
                }
            }
        });

        addPlayerPanel.add(addPlayerButton);

        // Créer un bouton "Play"
        JButton playButton = new JButton("Play!");
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    for (int i = 0; i < sliderValue - nbPlayers; i++) {
                        game.addPlayer(new Bot("Joueur " + (i + 1), 1));
                        System.out.println("Bot ajouté: Joueur " + (i + 1));
                    }
                    game.startGame();
                    // Close the window after starting the game
                    frame.dispose(); // This will close the window
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Erreur lors du démarrage du jeu: " + ex.getMessage());
                }
            }
        });

        // Ajouter les composants à la fenêtre
        frame.add(sliderPanel, BorderLayout.NORTH);
        frame.add(addPlayerPanel, BorderLayout.CENTER);
        frame.add(playButton, BorderLayout.SOUTH);

        // Centrer la fenêtre sur l'écran
        frame.setLocationRelativeTo(null);

        // Rendre la fenêtre visible
        frame.setVisible(true);
    }
}
