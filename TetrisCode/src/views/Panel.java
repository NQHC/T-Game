package views;

import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * Panel.java: Class that paints objects onto screen
 *
 * @author Nathan C
 * @version 1.0 August 5, 2021
 * @see javax.swing.JPanel
 * @see java.awt.Graphics
 */
public class Panel extends JPanel {
    public TetrisBoard board; // tetris board 

    /**
     * 
     * @param b tetris board 
     */
    Panel(TetrisBoard b) {
        this.board = b;
    }

    /**
     * Paints components on screen. Repaints itself to update board.
     */
    @Override
    protected void paintComponent(Graphics screen) {
        super.paintComponent(screen);

        board.CONTROLLER.boardState(screen);
        repaint();
    }
}