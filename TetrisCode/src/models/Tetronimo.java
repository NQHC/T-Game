package models;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import views.FieldLocation;
import views.TetrisBoard;

/**
 * Tetronimo.java: An abstract class to model the base capaabilities of a
 * tetronimo
 *
 * @author Nathan C
 * @version 1.0 August 5, 2021
 * 
 * @see java.awt.Point
 * @see java.awt.Color
 */
public abstract class Tetronimo {
    /**
     * Constant to represent the size of the tetronimo,
     */
    public static final int DISTANCE = -8; // distance of next piece from board
    public Color color; // color of shape
    public FieldLocation s1; // first square
    public FieldLocation s2; // 2nd square
    public FieldLocation s3; // 3rd square
    public FieldLocation s4; // 4th square

    protected int curRotation = 1; // current rotation orientation of tetronimo
    public Point[] rot; // point array for potential rotation

    /**
     * Generates the four rectangles for the tetronino and puts them on the screen,
     * they are at the default coordinates to start
     */
    public Tetronimo(TetrisBoard t) {

        this.s1 = new FieldLocation(1, 1);
        this.s2 = new FieldLocation(1, 1);
        this.s3 = new FieldLocation(1, 1);
        this.s4 = new FieldLocation(1, 1);
    }

    /**
     * Color the tetronimo, color is set depending on specific shape, called from
     * inherited class
     */
    public void colorize() {
        s1.setColor(color);
        s2.setColor(color);
        s3.setColor(color);
        s4.setColor(color);
    }

    /**
     * Initializes from next piece to active piece, moving it onto the board
     */
    public void initialize() {
        return;
    }

    /**
     * builds squares onto screen
     * 
     * @param screen // tetris screen
     */
    public void build(Graphics screen) {

        this.s1.create(screen);
        this.s2.create(screen);
        this.s3.create(screen);
        this.s4.create(screen);
    }

    /**
     * Checks if rotation should be allowed,
     * 
     * @return returning possible rotation coordinates in point array (row,column)
     */
    public Point[] rotateCheck() {

        return rot;

    }

    /**
     * Increments the rotation of the tetronimo, other classes need to override this
     * to provide the full functionality
     */
    public void rotate() {
        if (this.curRotation == 5) {
            curRotation = 1;
        }
        this.curRotation++;

    }

    /**
     * Shifts the tetronimo left one row
     */
    public void shiftLeft() {
        this.s1.setR(this.s1.getR() - 1);
        this.s2.setR(this.s2.getR() - 1);
        this.s3.setR(this.s3.getR() - 1);
        this.s4.setR(this.s4.getR() - 1);
    }

    /**
     * Shifts the tetronimo right one row
     */
    public void shiftRight() {

        this.s1.setR(this.s1.getR() + 1);
        this.s2.setR(this.s2.getR() + 1);
        this.s3.setR(this.s3.getR() + 1);
        this.s4.setR(this.s4.getR() + 1);

    }

    /**
     * shift tetronimo down 1 row
     */
    public void shiftDown() {

        s1.setC(s1.getC() + 1);
        s2.setC(s2.getC() + 1);
        s3.setC(s3.getC() + 1);
        s4.setC(s4.getC() + 1);

    }

    /**
     * get part of tetronimo that is furthest in Check position
     * 
     * @param f // Check is Right,Left,or Bottom, determines square to get location
     *          of to return for side collision
     * @return // location of square for collision check
     */
    public Point getPos(Check f) {
        int r = this.s1.getR();
        int c = this.s1.getC();
        if (f == Check.RIGHT) {
            if (this.s2.getR() > r) {
                r = this.s2.getR();
            }
            if (this.s3.getR() > r) {
                r = this.s3.getR();
            }
            if (this.s4.getR() > r) {
                r = this.s4.getR();
            }
        }
        if (f == Check.LEFT) {
            if (this.s2.getR() < r) {
                r = this.s2.getR();
            }
            if (this.s3.getR() < r) {
                r = this.s3.getR();
            }
            if (this.s4.getR() < r) {
                r = this.s4.getR();
            }
        }
        if (f == Check.BOTTOM) {
            if (this.s2.getC() > c) {
                c = this.s2.getC();
            }
            if (this.s3.getC() > c) {
                c = this.s3.getC();
            }
            if (this.s4.getC() > c) {
                c = this.s4.getC();
            }
        }
        return new Point(r, c);
    }

    /**
     * get exact location of any requested square
     * 
     * @param i // requested square, 1 - 4
     * @return // return (row,col)
     */
    public Point specificSquare(int i) {
        switch (i) {
            case 1:
                return new Point(this.s1.getR(), this.s1.getC());

            case 2:
                return new Point(this.s2.getR(), this.s2.getC());

            case 3:
                return new Point(this.s3.getR(), this.s3.getC());

            case 4:
                return new Point(this.s4.getR(), this.s4.getC());
            default:
                return new Point(0, 0);
        }

    }
}