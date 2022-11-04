package views;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import controllers.TetrisController;
import models.Check;
import models.Tetronimo;

/**
 * TetrisBoard.java: Class to model the tetris board
 *
 * @author Nathan C
 * @version 1.0 August 5, 2021
 *
 * @see java.awt.Graphics
 * @see java.awt.event.KeyListener
 * @see java.awt.event.KeyEvent
 * @see javax.swing.JFrame
 */
public class TetrisBoard implements KeyListener {
    /**
     * Constant to represent the width of the board
     */
    public static final int WIDTH = 10;

    /**
     * Constant to represnet the height of the board
     */
    public static final int HEIGHT = 24;

    public Tetronimo tetronimo; // active tetronimo
    public Tetronimo next; // next tetronimo
    public final TetrisController CONTROLLER; // controller
    private FieldLocation[][] playingField; // grid for squares
    JFrame frame; // frame for screen

    /**
     * Constructor to initialize the board
     *
     */
    public TetrisBoard() {
        this.CONTROLLER = new TetrisController(this);
        this.buildBoard();
        frame = new JFrame();
        frame.setSize(500, 650);
        frame.setLocationRelativeTo(null);
        frame.add(new Panel(this));
        frame.setVisible(true);
        frame.addKeyListener(this);

        this.run();
    }

    /**
     * Builds the playing field for tetris
     */
    private void buildBoard() {
        playingField = new FieldLocation[WIDTH][HEIGHT];
        for (int i = 0; i < TetrisBoard.WIDTH; i++) {
            for (int j = 0; j < TetrisBoard.HEIGHT; j++) {
                this.playingField[i][j] = new FieldLocation(i, j);
            }
        }
    }

    /**
     * Puts squares for grid onto screen
     * 
     * @param screen // active tetris screen graphic
     */
    public void build(final Graphics screen) {
        for (int i = 0; i < TetrisBoard.WIDTH; i++) {
            for (int j = 0; j < TetrisBoard.HEIGHT; j++) {
                playingField[i][j].create(screen);
            }
        }
    }

    /**
     * Starts gameplay and is responsible for keeping the game going 
     */
    public void run() {
        this.tetronimo = this.CONTROLLER.getNextTetromino();
        while (true) {
            this.tetronimo.initialize(); // put current tetronimo on board
            next = this.CONTROLLER.getNextTetromino(); // next tetronimo

            while (!this.CONTROLLER.tetronimoLanded(this.tetronimo)) {
                try {

                    Thread.sleep(300);// wait between shift downs
                } catch (final InterruptedException ex) {
                }
                if (!this.CONTROLLER.tetronimoLanded(this.tetronimo)) {
                    this.tetronimo.shiftDown();
                }
                try {

                    Thread.sleep(200); // wait after shift down before land check for fluid gameplay
                } catch (final InterruptedException ex) {
                }
            }

            if (this.CONTROLLER.isOver(this.tetronimo)) {
                break;
            }
            this.CONTROLLER.setPiece(this.tetronimo);

            this.tetronimo = null; // delete current tetronimo
            CONTROLLER.lineCheck(); // checks if lines have been filled
            this.tetronimo = next; // set current tetronimo to next tetronimo
        }
        try {

            Thread.sleep(10000); // wait then exit program
        } catch (final InterruptedException ex) {
        }
        System.exit(0);
    }

    /**
     * Getter method for the array representing the playing field, not used yet but
     * will be needed by the controller later
     *
     * @return The playing field
     */
    public FieldLocation[][] getPlayingField() {
        return playingField;
    }

    /**
     * This method is not used in this program
     *
     * @param e The key event
     */
    @Override
    public void keyTyped(final KeyEvent e) {
        // not in use
    }

    /**
     * Handles the key events by the user
     *
     * @param e The key event
     */
    @Override
    public void keyPressed(final KeyEvent e) {
        final int key = e.getKeyCode();

        if (this.tetronimo == null) {
            return;
        }

        switch (key) {
            case 38: // up arrow
                final Point p[] = tetronimo.rotateCheck(); // calls for potential rotation position

                if (CONTROLLER.edgeCheck(p)) { // checks potential position against possible positions
                    this.tetronimo.rotate();

                }
                break;
            case 37: // left arrow
                if (tetronimo.getPos(Check.LEFT).x >= 1) {
                    for (int i = 1; i < 5; i++) {
                        if (!playingField[tetronimo.specificSquare(i).x + -1][tetronimo.specificSquare(i).y].getColor().equals(Color.BLACK)) { // checks side collision
                            break;
                        } else if (i == 4) {
                            this.tetronimo.shiftLeft();
                        }
                    }
                }
                break;
            case 39: // right arrow
                if (tetronimo.getPos(Check.RIGHT).x <= TetrisBoard.WIDTH - 2) { 
                    for (int i = 1; i < 5; i++) {
                        if (!playingField[tetronimo.specificSquare(i).x + 1][tetronimo.specificSquare(i).y].getColor().equals(Color.BLACK)) { // checks side collision
                            break;
                        } else if (i == 4) {
                            this.tetronimo.shiftRight();
                        }
                    }
                }
                break;
            case 40: // down arrow
            {
                if (!CONTROLLER.tetronimoLanded(tetronimo)) { // if tetronimo has not landed, shift down
                    this.tetronimo.shiftDown();
                }

                break;
            }
        }

    }

    /**
     * This method is not used in this program
     *
     * @param e The key event
     */
    @Override
    public void keyReleased(final KeyEvent e) {
        // not in use
    }
}