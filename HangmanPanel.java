ackage project_hangman;

import javax.swing.*;
import java.awt.*;

public class HangmanPanel extends JPanel {
    int wrong = 0;

    public void setWrong(int w) {
        this.wrong = w;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));
        g2.setColor(Color.BLACK);

        // Gallows structure
        g2.drawLine(50, 250, 150, 250); 
        g2.drawLine(100, 250, 100, 50);  
        g2.drawLine(100, 50, 200, 50);   
        g2.drawLine(200, 50, 200, 80);   

        // 3-Stage Hangman (Hard Mode)
        if (wrong >= 1) {
            g2.drawOval(180, 80, 40, 40);      // Stage 1: Head
        }
        if (wrong >= 2) {
            g2.drawLine(200, 120, 200, 170);   // Stage 2: Upper Body / Torso
        }
        if (wrong >= 3) {
            // Stage 3: Complete Limbs (Game Over)
            g2.drawLine(200, 130, 170, 150);   // Left Arm
            g2.drawLine(200, 130, 230, 150);   // Right Arm
            g2.drawLine(200, 170, 170, 200);   // Left Leg
            g2.drawLine(200, 170, 230, 200);   // Right Leg
        }
    }
}