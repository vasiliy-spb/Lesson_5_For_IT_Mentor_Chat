package dev.cheercode.board;

import dev.cheercode.entity.Entity;
import dev.cheercode.entity.LongEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SnakeBoard extends Board<Entity> {
    public SnakeBoard(int height, int width) {
        super(height, width);
    }

    public void put(LongEntity longEntity) {
        for (Coordinates bodyCoordinate : longEntity.getBodyCoordinates()) {
            put(longEntity, bodyCoordinate);
        }
    }

    public LongEntity remove(LongEntity longEntity) {
        for (Coordinates coordinates : getBody(longEntity)) {
            remove(coordinates);
        }
        return longEntity;
    }

    private List<Coordinates> getBody(LongEntity longEntity) {
        List<Coordinates> body = new ArrayList<>();
        for (Map.Entry<Coordinates, Entity> entry : items.entrySet()) {
            if (entry.getValue() == longEntity) {
                body.add(entry.getKey());
            }
        }
        return body;
    }
}
