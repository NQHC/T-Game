package models;

import java.awt.Color;
import java.awt.Point;

/**
 * LShape.java: Creates a square tetronimo
 *
 * @author Nathan C
 * @version 1.0 August 4, 2021
 *
 * @see java.awt.Point
 */
public class Square extends Tetronimo {

    /**
     * Creates the tetronimo and puts it off the board
     */
    public Square() {

        super(null);
        super.color = Color.YELLOW; // sets color of shape
        super.s1.setR(3 + DISTANCE);
        super.s1.setC(0);
        super.s2.setR(4 + DISTANCE);
        super.s2.setC(0);
        super.s3.setR(3 + DISTANCE);
        super.s3.setC(1);
        super.s4.setR(4 + DISTANCE);
        super.s4.setC(1);
        super.colorize();

    }

    /**
     * Places the tetronimo on the board
     */
    @Override
    public void initialize() {
        super.s1.setR(3);

        super.s2.setR(4);

        super.s3.setR(3);

        super.s4.setR(4);

    }

    /**
     * Checks if rotation should be allowed,
     * 
     * @return returning possible rotation coordinates in point array (row,column)
     */
    @Override
    public Point[] rotateCheck() {
        rot = new Point[4];
        if (super.curRotation % 2 == 0) {
            rot[0] = new Point(super.s2.getR(), super.s2.getC());
            rot[1] = new Point(super.s1.getR(), super.s1.getC());
            rot[2] = new Point(super.s4.getR(), super.s4.getC());
            rot[3] = new Point(super.s3.getR(), super.s3.getC());
        } else {

            rot[0] = new Point(super.s2.getR(), super.s2.getC());
            rot[1] = new Point(super.s1.getR(), super.s1.getC());
            rot[2] = new Point(super.s4.getR(), super.s4.getC());
            rot[3] = new Point(super.s3.getR(), super.s3.getC());
        }
        return rot;
    }

    /**
     * Rotates the tetronimo
     */
    @Override
    public void rotate() {
        super.rotate();
    }

}
