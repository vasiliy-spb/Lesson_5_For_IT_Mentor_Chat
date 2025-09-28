package dev.cheercode;

import dev.cheercode.board.SnakeBoard;
import dev.cheercode.dialog.Dialog;
import dev.cheercode.dialog.StringDialog;
import dev.cheercode.entity.Direction;
import dev.cheercode.entity.Snake;
import dev.cheercode.renderer.BoardRenderer;

import java.util.List;

public class Game {
    public final int foodToVictory;
    private final SnakeBoard board;
    private final Snake snake;
    private final BoardRenderer renderer;
    private static final String UP = "w";
    private static final String RIGHT = "d";
    private static final String LEFT = "a";
    private static final String DOWN = "s";
    private int foodCounter = 0;
    private boolean isCrush;

    public Game(SnakeBoard board, Snake snake, int foodToVictory) {
        this.board = board;
        this.snake = snake;
        this.renderer = new BoardRenderer();
        this.foodToVictory = foodToVictory;
    }

    public void start() {
        System.out.println("--- Игра Змейка ---");
        renderer.draw(board);

        String title = "Введите направление (%s, %s, %s, %s)".formatted(UP, RIGHT, DOWN, LEFT);
        String failMessage = "Неправильный ввод";
        Dialog<String> dialog = new StringDialog(title, failMessage, List.of(UP, RIGHT, DOWN, LEFT));

        snake.setOnEat(c -> foodCounter++);
        snake.setOnCrush(c -> isCrush = true);

        while (!isGameOver()) {
            makeMove(dialog);
        }

        if (isWin()) {
            System.out.println("Victory!");
        } else {
            System.out.println("Game over..");
        }
    }

    private boolean isWin() {
        return foodCounter >= foodToVictory;
    }

    private boolean isGameOver() {
        return isCrush || isWin();
    }

    private void makeMove(Dialog<String> dialog) {
        String key = dialog.input();
        Direction direction = getDirection(key);

        snake.move(board, direction);

        renderer.draw(board);
        System.out.println("съедено яблок: " + foodCounter);
    }

    private Direction getDirection(String key) {
        return switch (key) {
            case UP -> Direction.UP;
            case RIGHT -> Direction.RIGHT;
            case DOWN -> Direction.DOWN;
            case LEFT -> Direction.LEFT;
            default -> throw new IllegalArgumentException("Unsupported key: " + key);
        };
    }


}
