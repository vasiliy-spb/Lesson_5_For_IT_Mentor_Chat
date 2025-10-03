package dev.cheercode.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import dev.cheercode.board.Board;
import dev.cheercode.board.Coordinates;
import dev.cheercode.entity.Apple;
import dev.cheercode.entity.Entity;
import dev.cheercode.entity.Rock;
import dev.cheercode.entity.Snake;

public class SwingGamePanel extends JPanel {
    private static final int CELL_SIZE = 30;
    private static final Color EMPTY_COLOR = new Color(139, 69, 19);
    private static final Color APPLE_COLOR = Color.GREEN;
    private static final Color ROCK_COLOR = Color.GRAY;
    private static final Color SNAKE_COLOR = Color.RED;
    private static final Color BORDER_COLOR = Color.BLACK;

    private Board<Entity> board;

    public SwingGamePanel(Board<Entity> board) {
        this.board = board;
        setPreferredSize(new Dimension(
                board.getWidth() * CELL_SIZE,
                board.getHeight() * CELL_SIZE));
        setBackground(EMPTY_COLOR);
    }

    public void updateBoard(Board<Entity> board) {
        this.board = board;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (board == null)
            return;

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw grid
        for (int row = 0; row < board.getHeight(); row++) {
            for (int col = 0; col < board.getWidth(); col++) {
                int x = col * CELL_SIZE;
                int y = row * CELL_SIZE;

                Coordinates coords = new Coordinates(row, col);
                Color cellColor = getCellColor(coords);

                g2d.setColor(cellColor);
                g2d.fillRect(x, y, CELL_SIZE, CELL_SIZE);

                g2d.setColor(BORDER_COLOR);
                g2d.drawRect(x, y, CELL_SIZE, CELL_SIZE);
            }
        }
    }

    private Color getCellColor(Coordinates coords) {
        if (board.isEmpty(coords)) {
            return EMPTY_COLOR;
        }

        Entity entity = board.get(coords);
        if (entity instanceof Apple) {
            return APPLE_COLOR;
        } else if (entity instanceof Rock) {
            return ROCK_COLOR;
        } else if (entity instanceof Snake) {
            return SNAKE_COLOR;
        }

        return EMPTY_COLOR;
    }
}