package dev.cheercode.entity;

import dev.cheercode.board.Coordinates;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public abstract class LongEntity extends Entity {
    protected final LinkedList<Coordinates> bodyCoordinates;

    public LongEntity(List<Coordinates> bodyCoordinates) {
        this.bodyCoordinates = new LinkedList<>(bodyCoordinates);
    }

    public List<Coordinates> getBodyCoordinates() {
        return new ArrayList<>(bodyCoordinates);
    }

}

/*

. . . . . . . .
. d . . . . . .
. d . . . . . .
. r r d . . . .
. . . r r u . .
. . . . . . . .
. . . . . . . .

 */
