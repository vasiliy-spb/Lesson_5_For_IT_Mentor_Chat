package dev.cheercode.temp_chess;

public class Piece {
    private String sprite;
    private Color color;

    public Piece(String sprite, Color color) {
        this.sprite = sprite;
        this.color = color;
    }

    public String getSprite() {
        return sprite;
    }

    public Color getColor() {
        return color;
    }

    public enum Color {
        BLACK,
        WHITE;
    }
}
