package dev.cheercode;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import dev.cheercode.board.Coordinates;
import dev.cheercode.board.SnakeBoard;
import dev.cheercode.entity.Snake;
import dev.cheercode.factory.BoardFactory;
import dev.cheercode.gui.SwingGameFrame;

public class MainWithGUI {
    public static void main(String[] args) {

        // Ask user for game mode
        String[] options = {"Console", "GUI (Swing)"};
        int choice = JOptionPane.showOptionDialog(
            null,
            "Выберите режим игры:",
            "Snake Game",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[1]
        );
        
        // Create game components
        Snake snake = new Snake(
            new Coordinates(0, 0),
            new Coordinates(1, 0),
            new Coordinates(1, 1)
        );
        SnakeBoard board = BoardFactory.create(10, 12, snake);
        
        if (choice == 0) {
            // Console mode
            Game game = new Game(board, snake, 2);
            game.start();
        } else {
            // GUI mode
            SwingUtilities.invokeLater(() -> {
                SwingGameFrame frame = new SwingGameFrame(board, snake, 2);
                frame.startGame();
            });
        }
    }
}