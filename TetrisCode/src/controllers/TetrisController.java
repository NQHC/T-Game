package controllers;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

import models.Check;
import models.JShape;
import models.LShape;
import models.SShape;
import models.Square;
import models.StraightLine;
import models.TShape;
import models.Tetronimo;
import models.ZShape;
import views.TetrisBoard;

/**
 * TetrisController.java: Class to hold all of the game logic for tetris, such
 * as scoring and position tracking
 *
 * @author Nathan C
 * @version 1.0 August 4, 2021
 * @see java.util.Random
 * @see java.awt.Font
 */
public class TetrisController {
    private final TetrisBoard TETRIS_BOARD; // tetris board
    public int score = 0; // game score
    Tetronimo tetronimo; // tetronimo object
    boolean gameOver = false; // boolean for if game state is over

    /**
     * Constructor to take in a tetris board so the controller and the board can
     * communciate
     *
     * @param tetrisBoard A tetris board instance
     */
    public TetrisController(TetrisBoard tetrisBoard) {
        this.TETRIS_BOARD = tetrisBoard;
    }

    /**
     * Randomly chooses the next tetronimo and returns it (INCOMPLETE)
     *
     * @return The next tetronimo to be played
     */
    public Tetronimo getNextTetromino() {

        Random r = new Random();
        int i = r.nextInt(7); // random number 0 - 6
        switch (i) {
            case 0:
                tetronimo = new LShape();
                break;
            case 1:
                tetronimo = new StraightLine();
                break;
            case 2:
                tetronimo = new JShape();
                break;
            case 3:
                tetronimo = new Square();
                break;
            case 4:
                tetronimo = new ZShape();
                break;
            case 5:
                tetronimo = new SShape();
                break;
            case 6:
                tetronimo = new TShape();
                break;
        }

        return tetronimo;
    }

    /**
     * Builds the current board state
     * 
     * @param screen // graphic panel
     */
    public void boardState(Graphics screen) {
        TETRIS_BOARD.build(screen);
        if (this.TETRIS_BOARD.tetronimo != null) {
            this.TETRIS_BOARD.tetronimo.build(screen);
        }
        if (this.TETRIS_BOARD.next != null) {
            this.TETRIS_BOARD.next.build(screen);
        }
        build(screen);
    }

    /**
     * Builds score and game over message
     * 
     * @param screen graphic panel
     */
    public void build(Graphics screen) {
        screen.setFont(new Font("Verdana", Font.PLAIN, 22));
        screen.drawString("Score: " + score, 330, 100);
        if (gameOver == true) {
            screen.setFont(new Font("Verdana", Font.BOLD, 35));
            screen.drawString("Game Over", 125, 300);
        }
    }

    /**
     * Method to determine if the tetronimo has landed
     *
     * @param tetronimo The tetronimo to evaluate
     * @return True if the tetronimo has landed (on the bottom of the board or
     *         another tetronimo), false if it has not
     */
    public boolean tetronimoLanded(Tetronimo tetronimo) {
        if (tetronimo.getPos(Check.BOTTOM).y >= TetrisBoard.HEIGHT - 1) {
            return true;
        }
        Point p; // point for each square
        for (int i = 1; i < 5; i++) {
            p = tetronimo.specificSquare(i); // get square location
            if (!TETRIS_BOARD.getPlayingField()[p.x][p.y + 1].getColor().equals(Color.BLACK)) {
                return true;
            }
        }
        return false;
    }

    /**
     * check if (row,column) points are out of play area or in occupied space
     * 
     * @param r // array of points for row,column of tetronimo piece
     * @return // returns false if point is out of play
     */
    public boolean edgeCheck(Point r[]) { //
        for (int i = 0; i < 4; i++) {

            if ((r[i].x < 0) || r[i].x > TetrisBoard.WIDTH - 1 || r[i].y > TetrisBoard.HEIGHT - 1 || r[i].y < 0) {
                return false;
            }
            if (!TETRIS_BOARD.getPlayingField()[r[i].x][r[i].y].getColor().equals(Color.BLACK)) {
                return false;
            }
        }
        return true;
    }

    /**
     * checks if line has been cleared and then moves blocks down
     */
    public void lineCheck() {
        int tetrisCount = 0; // count for possible tetris (800 points)
        for (int i = 0; i < TetrisBoard.HEIGHT; i++) {
            for (int j = 0; j < TetrisBoard.WIDTH; j++) {
                if (TETRIS_BOARD.getPlayingField()[j][i].getColor().equals(Color.BLACK)) {
                    break;
                }
                if (j == TetrisBoard.WIDTH - 1) {
                    tetrisCount++;
                    score += 100;
                    for (int k = 0; k < TetrisBoard.WIDTH; k++) {

                        for (int i2 = i - 1; i2 >= 0; i2--) {
                            TETRIS_BOARD.getPlayingField()[k][i2 + 1]
                                    .setColor(TETRIS_BOARD.getPlayingField()[k][i2].getColor());
                        }
                    }

                }
            }
        }
        score += (400 * (tetrisCount / 4));
        return;
    }

    /** Sets occupied squares to color of landed piece */
    public void setPiece(Tetronimo tetronimo) {
        Point p; // point for coordinates of a square
        Color c = tetronimo.color; // color of tetronimo
        for (int i = 1; i < 5; i++) {
            p = tetronimo.specificSquare(i);
            TETRIS_BOARD.getPlayingField()[p.x][p.y].setColor(c);
        }

    }

    /**
     * Checks if current piece is occupying an already filled square
     * 
     * @param tetronimo // active piece
     * @return returns true if game is over
     */
    public boolean isOver(Tetronimo tetronimo) {
        Point p; // point for coordiantes of a square
        for (int i = 1; i < 5; i++) {
            p = tetronimo.specificSquare(i);
            if (!TETRIS_BOARD.getPlayingField()[p.x][p.y].getColor().equals(Color.BLACK)) {
                gameOver = true;
                return true;
            }
        }
        return false;
    }
}
