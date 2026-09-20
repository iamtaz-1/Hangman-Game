
# Interactive Hangman Game (Java Swing)

A desktop-based interactive Hangman Game built using **Java Swing**, **AWT (Graphics2D)**, and **Object-Oriented Programming (OOP)** concepts as part of a 10-week academic coursework.

## 📌 Project Overview
This application provides a full-featured classic Hangman gameplay experience with dynamic UI graphics, sound effects, input validation, and word loading from external files.

## 🚀 Key Features
- **Graphical UI:** Built with `JFrame`, `JPanel`, `BorderLayout`, and custom Swing components.
- **Custom Graphics2D Drawing:** Dynamically renders the gallows and a 3-stage stickman figure (`paintComponent`).
- **File Handling:** Loads words and hints dynamically from `words.txt` with fallback error handling.
- **Duplicate Letter Prevention:** Uses a `HashSet<Character>` to track guessed letters and block repeated inputs.
- **Timer System:** Integrated 60-second countdown using `javax.swing.Timer`.
- **Sound Effects:** Triggers audio playback (`correct.wav`, `wrong.wav`, `gameover.wav`) without overlapping audio bugs.
- **Game Over Animation:** Custom visual indicators (X-eyes and tongue) drawn upon losing all 3 lives.

## 🛠️ Technologies Used
- **Language:** Java (JDK 8+)
- **GUI & Graphics:** Java Swing & AWT (`Graphics2D`)
- **Audio:** `javax.sound.sampled` Package
- **IDE:** NetBeans / Eclipse / VS Code

## 📁 Repository Structure
├── HangmanGame.java       # Main application window, timer, and core game logic
├── HangmanPanel.java      # Custom Graphics2D panel for stickman and gallows
├── words.txt              # Text file containing word list and hints
├── correct.wav            # Sound effect for correct letter guess
├── wrong.wav              # Sound effect for wrong letter guess
├── gameover.wav           # Sound effect played on losing the game
└── README.md              # Project documentation file

## ⚙️ How to Run
1. Clone this repository or download the source code:
   git clone https://github.com/iamtaz-1/Hangman-Game.git
2. Open the project in **NetBeans** or any Java IDE.
3. Ensure `words.txt` and `.wav` audio files are placed in the root/source folder.
4. Run `HangmanGame.java`.
