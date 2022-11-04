package views;

import java.awt.Color;
import java.awt.Graphics;

/**
 * FieldLocation.java: Class that represents each individual square on the
 * board. Instance of class is one square drawn on tetris board.
 *
 * @author Nathan C
 * @version 1.0 August 5, 2021
 * @see java.awt.Color
 */
public class FieldLocation {

    private int row, col; // the grid location of this Square

    private Color color; // the color of this Square

    public static final int DIMENSION = 20; // dimension of squares(height length)

    /**
     * 
     * @param row // row for square
     * @param col // column for square
     */
    public FieldLocation(int row, int col) {

        // instance variables
        this.row = row;
        this.col = col;
        color = Color.BLACK;
    }

    /**
     * set color of square
     * 
     * @param c new color value
     */
    public void setColor(Color c) {
        color = c;
    }

    /**
     * get color of square
     * 
     * @return current color value
     */
    public Color getColor() {
        return color;
    }

    /**
     * set row in grid
     * 
     * @param r new row value
     */
    public void setR(int r) {
        this.row = r;
    }

    /**
     * get row in grid
     * 
     * @return row value
     */
    public int getR() {
        return row;
    }

    /**
     * // set column in grid
     * 
     * @param c new column value
     */
    public void setC(int c) {
        this.col = c;
    }

    /**
     * get column in grid
     * 
     * @return column value
     */
    public int getC() {
        return col;
    }

    /**
     * Create square on screen, if color is black, draw outline, else fill the shape
     * 
     * @param screen graphic panel
     */
    public void create(Graphics screen) {
        int x = row * DIMENSION + 125; // x location for square
        int y = col * DIMENSION + 50; // y location for square
        if (!color.equals(Color.BLACK)) {
            screen.setColor(color);
            screen.fillRect(x, y, DIMENSION, DIMENSION);
        }
        screen.setColor(Color.BLACK);
        screen.drawRect(x, y, DIMENSION, DIMENSION);
    }

}
