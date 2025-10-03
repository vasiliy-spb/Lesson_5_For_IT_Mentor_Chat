package dev.cheercode.gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import dev.cheercode.board.SnakeBoard;
import dev.cheercode.entity.Direction;
import dev.cheercode.entity.Snake;

public class SwingGameFrame extends JFrame {
    private SwingGamePanel gamePanel;
    private JLabel scoreLabel;
    private JLabel statusLabel;
    private final SwingGame swingGame;
    
    public SwingGameFrame(SnakeBoard board, Snake snake, int foodToVictory) {
        this.swingGame = new SwingGame(board, snake, foodToVictory, this);
        initializeUI(board);
        setupKeyListener();
    }
    
    private void initializeUI(SnakeBoard board) {
        setTitle("Snake Game - Swing Version");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        
        // Create game panel
        gamePanel = new SwingGamePanel(board);
        mainPanel.add(gamePanel, BorderLayout.CENTER);
        
        // Create info panel
        JPanel infoPanel = new JPanel(new GridLayout(2, 1));
        scoreLabel = new JLabel("Съедено яблок: 0", SwingConstants.CENTER);
        statusLabel = new JLabel("Используйте WASD для управления", SwingConstants.CENTER);
        
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 14));
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        
        infoPanel.add(scoreLabel);
        infoPanel.add(statusLabel);
        mainPanel.add(infoPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
        pack();
        setLocationRelativeTo(null);
        
        // Make focusable for key events
        setFocusable(true);
        requestFocus();
    }
    
    private void setupKeyListener() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                Direction direction = null;
                
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_W, KeyEvent.VK_UP -> direction = Direction.UP;
                    case KeyEvent.VK_S, KeyEvent.VK_DOWN -> direction = Direction.DOWN;
                    case KeyEvent.VK_A, KeyEvent.VK_LEFT -> direction = Direction.LEFT;
                    case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> direction = Direction.RIGHT;
                }
                
                if (direction != null && swingGame != null) {
                    swingGame.processMove(direction);
                }
            }
        });
    }
    
    public void updateDisplay() {
        gamePanel.repaint();
        scoreLabel.setText("Съедено яблок: " + swingGame.getFoodCounter());
    }
    
    public void showGameOver(boolean isWin) {
        String message = isWin ? "Победа!" : "Игра окончена!";
        statusLabel.setText(message);
        
        int option = JOptionPane.showConfirmDialog(
            this,
            message + " Хотите начать новую игру?",
            "Игра окончена",
            JOptionPane.YES_NO_OPTION
        );
        
        if (option == JOptionPane.YES_OPTION) {
            // Restart game logic would go here
            dispose();
        } else {
            System.exit(0);
        }
    }
    
    public void startGame() {
        setVisible(true);
        swingGame.start();
    }
}