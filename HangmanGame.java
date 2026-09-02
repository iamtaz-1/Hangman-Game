import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

public class HangmanGame extends JFrame implements ActionListener {

    // === Week 1 & 2
    private ArrayList<String> wordList = new ArrayList<>();
    private ArrayList<String> hintList = new ArrayList<>();
    private String targetWord;
    private String targetHint;

    // === Week 3: Matching Logic & 3 Lives Variables ===
    private char[] displayArray;
    private HashSet<Character> guessedLetters = new HashSet<>();
    private int remainingLives = 3;
    private final int MAX_LIVES = 3;

    // === Week 4: Window Layout GUI Components ===
    private JLabel hintLabel;
    private JLabel wordLabel;
    private JLabel usedLabel;
    private JLabel statusLabel;
    private JTextField inputField;
    private JButton guessButton;

    public HangmanGame() {
        // --- Week 1: Basic Frame Setup ---
        setTitle("Hangman Game - Hard Mode (3 Lives)");
        setSize(500, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // --- Week 2: Load Data & Initialize ---
        loadWords("words.txt");
        selectRandomWord();
        initDisplayArray();

        // --- Week 4: Arranging Window Layout (Swing Components) ---
        setLayout(new BorderLayout(10, 10));

        // Top Panel: Title, Hint, Word, and Status Labels
        JPanel topPanel = new JPanel(new GridLayout(4, 1));
        topPanel.setBackground(new Color(245, 245, 245));

        hintLabel = new JLabel("Hint: " + targetHint, SwingConstants.CENTER);
        hintLabel.setFont(new Font("SansSerif", Font.ITALIC, 15));

        wordLabel = new JLabel(getFormattedDisplay(), SwingConstants.CENTER);
        wordLabel.setFont(new Font("Monospaced", Font.BOLD, 30));

        usedLabel = new JLabel("Used Letters: []", SwingConstants.CENTER);

        statusLabel = new JLabel("Lives Left: " + remainingLives, SwingConstants.CENTER);
        statusLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        statusLabel.setForeground(Color.BLUE);

        topPanel.add(hintLabel);
        topPanel.add(wordLabel);
        topPanel.add(usedLabel);
        topPanel.add(statusLabel);

        add(topPanel, BorderLayout.CENTER);

        // Bottom Panel: Controls and Input Field
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(new JLabel("Enter Letter: "));
        
        inputField = new JTextField(5);
        guessButton = new JButton("Guess");

        bottomPanel.add(inputField);
        bottomPanel.add(guessButton);

        add(bottomPanel, BorderLayout.SOUTH);

        // Adding Listeners
        guessButton.addActionListener(this);
        inputField.addActionListener(this);
    }

    // === Week 1 & 2 Methods ===
    private void loadWords(String fileName) {
        try {
            Scanner scanner = new Scanner(new File(fileName));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    String[] parts = line.split("\\|");
                    if (parts.length == 2) {
                        wordList.add(parts[0].trim().toUpperCase());
                        hintList.add(parts[1].trim());
                    }
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: words.txt file not found!");
        }
    }

    private void selectRandomWord() {
        if (!wordList.isEmpty()) {
            int randomIndex = new Random().nextInt(wordList.size());
            targetWord = wordList.get(randomIndex);
            targetHint = hintList.get(randomIndex);
        }
    }

    // === Week 3 Logic Helper Methods ===
    private void initDisplayArray() {
        if (targetWord != null) {
            displayArray = new char[targetWord.length()];
            for (int i = 0; i < displayArray.length; i++) {
                displayArray[i] = '_';
            }
        }
    }

    private String getFormattedDisplay() {
        StringBuilder sb = new StringBuilder();
        for (char c : displayArray) {
            sb.append(c).append(" ");
        }
        return sb.toString().trim();
    }

    // === Week 3 Core Logic: Guess Matching & 3 Lives Handling ===
    private void processGuess() {
        String input = inputField.getText().trim().toUpperCase();
        inputField.setText("");

        if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
            return;
        }

        char letter = input.charAt(0);

        if (guessedLetters.contains(letter)) {
            return;
        }

        guessedLetters.add(letter);
        usedLabel.setText("Used Letters: " + guessedLetters.toString());

        boolean isCorrect = false;
        for (int i = 0; i < targetWord.length(); i++) {
            if (targetWord.charAt(i) == letter) {
                displayArray[i] = letter;
                isCorrect = true;
            }
        }

        if (isCorrect) {
            wordLabel.setText(getFormattedDisplay());
        } else {
            remainingLives--;
            statusLabel.setText("Lives Left: " + remainingLives);
            statusLabel.setForeground(Color.RED);
        }

        checkWinLoseCondition();
    }

    // === Week 3: Win / Lose Checks ===
    private void checkWinLoseCondition() {
        if (String.valueOf(displayArray).equals(targetWord)) {
            statusLabel.setText("YOU WIN! Correct Word: " + targetWord);
            statusLabel.setForeground(new Color(0, 128, 0));
            disableInputs();
        } else if (remainingLives <= 0) {
            statusLabel.setText("YOU LOSE! Correct Word was: " + targetWord);
            wordLabel.setText(targetWord);
            disableInputs();
        }
    }

    private void disableInputs() {
        inputField.setEnabled(false);
        guessButton.setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        processGuess();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HangmanGame().setVisible(true));
    }
}