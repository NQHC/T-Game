package models;

import java.awt.Color;
import java.awt.Point;

/**
 * StraightLine.java: Creates a straight line tetronimo
 *
 * @author Nathan C
 * @version 1.0 August 4, 2021
 *
 * @see java.awt.Point
 */
public class StraightLine extends Tetronimo {

    /**
     * Creates the tetronimo and puts it off the board
     */
    public StraightLine() {

        super(null);
        super.color = Color.CYAN; // sets color of shape
        super.s1.setR(3 + DISTANCE);
        super.s1.setC(0);
        super.s2.setR(4 + DISTANCE);
        super.s2.setC(0);
        super.s3.setR(5 + DISTANCE);
        super.s3.setC(0);
        super.s4.setR(6 + DISTANCE);
        super.s4.setC(0);
        super.colorize();
    }

    /**
     * Places the tetronimo on the board
     */
    @Override
    public void initialize() {
        super.s1.setR(3);
        super.s2.setR(4);
        super.s3.setR(5);
        super.s4.setR(6);

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
            rot[0] = new Point(super.s1.getR() - 2, super.s1.getC() - 2);
            rot[1] = new Point(super.s2.getR() - 1, super.s3.getC() + 1);
            rot[2] = new Point(super.s3.getR(), super.s3.getC());
            rot[3] = new Point(super.s4.getR() + 1, super.s4.getC() + 1);

        } else {
            rot[0] = new Point(super.s2.getR() + 1, super.s2.getC() + 1);
            rot[1] = new Point(super.s1.getR() + 2, super.s1.getC() + 2);
            rot[2] = new Point(super.s3.getR(), super.s3.getC());
            rot[3] = new Point(super.s4.getR() - 1, super.s3.getC() - 1);
        }
        return rot;
    }

    /**
     * Rotates the tetronimo
     */
    @Override
    public void rotate() {
        super.rotate();
        if (super.curRotation % 2 == 0) {
            super.s2.setR(super.s2.getR() + 1);
            super.s2.setC(super.s2.getC() + 1);

            super.s1.setR(super.s1.getR() + 2);
            super.s1.setC(super.s1.getC() + 2);

            super.s4.setR(super.s4.getR() - 1);
            super.s4.setC(super.s4.getC() - 1);

        } else {

            super.s2.setR(super.s2.getR() - 1);
            super.s2.setC(super.s2.getC() + -1);

            super.s1.setR(super.s1.getR() - 2);
            super.s1.setC(super.s1.getC() - 2);

            super.s4.setR(super.s4.getR() + 1);
            super.s4.setC(super.s4.getC() + 1);
        }

    }

}
