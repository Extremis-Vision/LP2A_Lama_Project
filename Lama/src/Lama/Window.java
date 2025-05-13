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

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        // Create a new window
        frame = new JFrame("Lama Games");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Use a layout to organize components
        frame.setLayout(new BorderLayout());

        // Panel for the slider and its label
        JPanel sliderPanel = new JPanel(new FlowLayout());

        // Add a label in front of the slider
        JLabel sliderLabel = new JLabel("Number of players: ");
        sliderPanel.add(sliderLabel);

        // Create a slider
        slider = new JSlider(1, 6, 3); // Min: 1, Max: 6, Initial value: 3
        slider.setMajorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                sliderValue = slider.getValue(); // Store the slider value in a variable
                System.out.println("Slider value: " + sliderValue);
            }
        });

        sliderPanel.add(slider);

        // Panel to add a player
        JPanel addPlayerPanel = new JPanel(new FlowLayout());
        nameField = new JTextField(15);
        birthDateField = new JTextField(15);

        addPlayerPanel.add(new JLabel("Name of the player:"));
        addPlayerPanel.add(nameField);
        addPlayerPanel.add(new JLabel("Birthdate (YYYY-MM-DD):"));
        addPlayerPanel.add(birthDateField);

        // Create a button to add a player
        JButton addPlayerButton = new JButton("Add Player");
        addPlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String birthDateStr = birthDateField.getText();
                try {
                    LocalDate birthDate = LocalDate.parse(birthDateStr);
                    // Add the player to the game
                    game.addPlayer(new Player(name, birthDate));
                    System.out.println("Added Player: " + name + ", Birthdate: " + birthDate);
                    nbPlayers++;
                    // Clear fields after adding
                    nameField.setText("");
                    birthDateField.setText("");
                } catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid Format. Use YYYY-MM-DD.");
                }
            }
        });

        addPlayerPanel.add(addPlayerButton);

        // Create a "Play" button
        JButton playButton = new JButton("Play!");
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    for (int i = 0; i < sliderValue - nbPlayers; i++) {
                        game.addPlayer(new Bot("Player " + (i + 1), 1));
                        System.out.println("Bot added: Player " + (i + 1));
                    }
                    game.startGame();
                    frame.dispose(); // This will close the window
                    // Close the window after starting the game
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Error in the beginning of the game: " + ex.getMessage());
                }
            }
        });

        // Add components to the window
        frame.add(sliderPanel, BorderLayout.NORTH);
        frame.add(addPlayerPanel, BorderLayout.CENTER);
        frame.add(playButton, BorderLayout.SOUTH);

        // Center the window on the screen
        frame.setLocationRelativeTo(null);

        // Make the window visible
        frame.setVisible(true);
    }
}
