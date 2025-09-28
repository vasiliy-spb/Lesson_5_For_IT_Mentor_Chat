package dev.cheercode;

import dev.cheercode.board.Coordinates;
import dev.cheercode.board.SnakeBoard;
import dev.cheercode.entity.Snake;
import dev.cheercode.factory.BoardFactory;


public class Main {
    public static void main(String[] args) {

        Snake snake = new Snake(
                new Coordinates(0, 0),
                new Coordinates(1, 0),
                new Coordinates(1, 1)
        );
        SnakeBoard board = BoardFactory.create(10, 12, snake);

        Game game = new Game(board, snake, 2);
        game.start();

    }
}
