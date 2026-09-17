package project_hangman;

import javax.swing.*;
import java.awt.*;

public class HangmanPanel extends JPanel {
    private int wrongGuesses = 0;

    public void setWrong(int wrong) {
        this.wrongGuesses = wrong;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3)); // Thickness of lines
        g2.setColor(Color.BLACK);

        // === 1. Draw Gallows ===
        g2.drawLine(50, 250, 200, 250); // Base
        g2.drawLine(100, 250, 100, 50);  // Vertical pole
        g2.drawLine(100, 50, 200, 50);   // Top bar
        g2.drawLine(200, 50, 200, 80);   // Rope

        if (wrongGuesses >= 1) {
            // === 2. Head Outline ===
            g2.drawOval(175, 80, 50, 50);

            if (wrongGuesses < 3) {
                // Normal eyes (while alive)
                g2.fillOval(190, 95, 5, 5);
                g2.fillOval(205, 95, 5, 5);
            } else {
                // === 3. Game Over: Double 'X' Eyes ===
                // Left 'X' eye
                g2.drawLine(187, 92, 195, 100);
                g2.drawLine(195, 92, 187, 100);

                // Right 'X' eye
                g2.drawLine(205, 92, 213, 100);
                g2.drawLine(213, 92, 205, 100);

                // Flat mouth
                g2.drawLine(190, 115, 210, 115);

                // === 4. Stick-out Tongue (U-Shape) ===
                g2.drawArc(200, 115, 8, 10, 180, 180);
            }
        }

        // === 5. Body & Limbs ===
        if (wrongGuesses >= 2) {
            g2.drawLine(200, 130, 200, 190); // Body
        }
        if (wrongGuesses >= 3) {
            // Hands and Legs (Game Over state)
            g2.drawLine(200, 145, 175, 165); // Left arm
            g2.drawLine(200, 145, 225, 165); // Right arm
            g2.drawLine(200, 190, 175, 220); // Left leg
            g2.drawLine(200, 190, 225, 220); // Right leg
        }
    }
}