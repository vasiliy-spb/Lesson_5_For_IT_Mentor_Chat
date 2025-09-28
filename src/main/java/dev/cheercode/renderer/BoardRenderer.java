package dev.cheercode.renderer;

import dev.cheercode.board.Board;
import dev.cheercode.board.Coordinates;
import dev.cheercode.entity.Apple;
import dev.cheercode.entity.Entity;
import dev.cheercode.entity.Rock;
import dev.cheercode.entity.Snake;

public class BoardRenderer {
    private final static String EMPTY = "🟫";
    private final static String APPLE = "🍏";
    private final static String ROCK = "⛰";
    private final static String SNAKE = "🔴";

    public void draw(Board<Entity> board) {
        int height = board.getHeight();
        int width = board.getWidth();
        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                Coordinates coordinates = new Coordinates(row, column);
                if (board.isEmpty(coordinates)) {
                    System.out.print(EMPTY);
                } else {
                    Entity entity = board.get(coordinates);
                    System.out.print(toSprite(entity));
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    private String toSprite(Entity entity) {
        if (entity instanceof Apple) {
            return APPLE;
        }
        if (entity instanceof Rock) {
            return ROCK;
        }
        if (entity instanceof Snake) {
            return SNAKE;
        }
        throw new IllegalArgumentException("Unknown entity: " + entity);
    }
}