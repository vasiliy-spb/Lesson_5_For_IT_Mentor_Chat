package dev.cheercode.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board<T> {
    private final int height;
    private final int width;
    protected final Map<Coordinates, T> items;

    public Board(int height, int width) {
        this.height = height;
        this.width = width;
        this.items = new HashMap<>();
    }

    public T get(Coordinates coordinates) {
        validateCoordinates(coordinates);
        return items.get(coordinates);
    }

    public void put(T value, Coordinates coordinates) {
        validateCoordinates(coordinates);
        items.put(coordinates, value);
    }

    public T remove(Coordinates coordinates) {
        validateCoordinates(coordinates);
        return items.remove(coordinates);
    }

    public List<T> values() {
        return new ArrayList<>(items.values());
    }

    public boolean isEmpty(Coordinates coordinates) {
        validateCoordinates(coordinates);
        return !items.containsKey(coordinates);
    }

    public Coordinates getCoordinates(T value) {
        for (Map.Entry<Coordinates, T> entry : items.entrySet()) {
            if (entry.getValue() == value) {
                return entry.getKey();
            }
        }
        throw new IllegalArgumentException("Coordinates not found for value: " + value);
    }

    private void validateCoordinates(Coordinates coordinates) {
        if (coordinates == null) {
            throw new IllegalArgumentException("Coordinates is null");
        }
        if (!isWithIn(coordinates)) {
            throw new IllegalArgumentException("Coordinates out of board: " + coordinates);
        }
    }

    public boolean isWithIn(Coordinates coordinates) {
        return coordinates.row() >= 0 && coordinates.row() < height &&
                coordinates.column() >= 0 && coordinates.column() < width;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
