package dev.cheercode.gui;

import dev.cheercode.board.SnakeBoard;
import dev.cheercode.entity.Direction;
import dev.cheercode.entity.Snake;

public class SwingGame {
    private final int foodToVictory;
    private final SnakeBoard board;
    private final Snake snake;
    private final SwingGameFrame frame;
    
    private int foodCounter = 0;
    private boolean isCrush = false;
    private boolean gameStarted = false;
    
    public SwingGame(SnakeBoard board, Snake snake, int foodToVictory, SwingGameFrame frame) {
        this.board = board;
        this.snake = snake;
        this.foodToVictory = foodToVictory;
        this.frame = frame;
        
        snake.setOnEat(c -> {
            foodCounter++;
            frame.updateDisplay();
        });
        
        snake.setOnCrush(c -> {
            isCrush = true;
            frame.showGameOver(false);
        });
    }
    
    public void start() {
        gameStarted = true;
        frame.updateDisplay();
    }
    
    public void processMove(Direction direction) {
        if (!gameStarted || isGameOver()) {
            return;
        }
        
        snake.move(board, direction);
        frame.updateDisplay();
        
        if (isWin()) {
            frame.showGameOver(true);
        }
    }
    
    public int getFoodCounter() {
        return foodCounter;
    }
    
    private boolean isWin() {
        return foodCounter >= foodToVictory;
    }
    
    private boolean isGameOver() {
        return isCrush || isWin();
    }
}