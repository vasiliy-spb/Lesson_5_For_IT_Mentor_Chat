package dev.cheercode.temp_chess;

import dev.cheercode.board.Board;
import dev.cheercode.board.Coordinates;

public class ChessBoard extends Board<Piece> {

    public ChessBoard() {
        super(8, 8);
    }

    public boolean isWhite(Coordinates coordinates) {
        return true;
    }

    public String toAddress(Coordinates coordinates) {
        return "h4";
    }
}
