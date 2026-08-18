import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class HangmanGame extends JFrame {
    
    private ArrayList<String> wordList = new ArrayList<>();
    private ArrayList<String> hintList = new ArrayList<>();
    private String targetWord;
    private String targetHint;

    public HangmanGame() {
        setTitle("Hangman Game - Project Development");
        setSize(600, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        
        loadWords("words.txt");
        selectRandomWord();
    }

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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new HangmanGame().setVisible(true);
            }
        });
    }
}