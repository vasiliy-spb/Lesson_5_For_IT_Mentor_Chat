package dev.cheercode.factory;

import dev.cheercode.board.Coordinates;
import dev.cheercode.board.SnakeBoard;
import dev.cheercode.entity.Apple;
import dev.cheercode.entity.Rock;
import dev.cheercode.entity.Snake;

public final class BoardFactory {
    private BoardFactory() {

    }

    public static SnakeBoard create(int height, int width, Snake snake) {
        SnakeBoard board = new SnakeBoard(height, width);

        board.put(snake);
        board.put(new Apple(), new Coordinates(3, 4));
        board.put(new Apple(), new Coordinates(5, 5));
        board.put(new Apple(), new Coordinates(7, 3));
        board.put(new Rock(), new Coordinates(2, 8));
        board.put(new Rock(), new Coordinates(3, 3));
        board.put(new Rock(), new Coordinates(4, 9));

        return board;
    }
}
