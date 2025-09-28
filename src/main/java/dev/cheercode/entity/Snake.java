package dev.cheercode.entity;

import dev.cheercode.board.Coordinates;
import dev.cheercode.board.SnakeBoard;

import java.util.Arrays;
import java.util.List;

public class Snake extends LongEntity {
    private static final Class<? extends Entity> FOOD = Apple.class;
    private CallBack onEat;
    private CallBack onCrush;

    public Snake(List<Coordinates> bodyCoordinates) {
        super(bodyCoordinates);
    }

    public Snake(Coordinates... coordinates) {
        super(Arrays.asList(coordinates));
    }

    public void move(SnakeBoard board, Direction direction) {
        Coordinates head = bodyCoordinates.getLast();
        Coordinates nextStep = new Coordinates(
                head.row() + direction.getRowShift(),
                head.column() + direction.getColumnShift()
        );

        if (!board.isWithIn(nextStep)) {
            if (onCrush != null) {
                onCrush.execute(nextStep);
                return;
            }
        }

        if (!board.isEmpty(nextStep)) {
            Entity entity = board.get(nextStep);
            if (isFood(entity)) {
                if (onEat != null) {
                    onEat.execute(nextStep);
                }
            } else {
                if (onCrush != null) {
                    onCrush.execute(nextStep);
                    return;
                }
            }
        } else {
            removeTail();
        }

        addHead(nextStep);

        board.remove(this);
        board.put(this);
    }

    private void addHead(Coordinates nextStep) {
        bodyCoordinates.add(nextStep);
    }

    private void removeTail() {
        bodyCoordinates.removeFirst();
    }

    private boolean isFood(Entity entity) {
        return FOOD.isInstance(entity);
    }

    private void addCoordinates(Coordinates coordinates) {
        bodyCoordinates.add(coordinates);
    }

    public void setOnEat(CallBack onEat) {
        this.onEat = onEat;
    }

    public void setOnCrush(CallBack onCrush) {
        this.onCrush = onCrush;
    }

    public interface CallBack {
        void execute(Coordinates coordinates);
    }
}

