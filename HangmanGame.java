import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class HangmanGame extends JFrame {

    public HangmanGame() {
        setTitle("Hangman Game - Project Development");
        setSize(600, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                HangmanGame gameWindow = new HangmanGame();
                gameWindow.setVisible(true);
            }
        });
    }
}
