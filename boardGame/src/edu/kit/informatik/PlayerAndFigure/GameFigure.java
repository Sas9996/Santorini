package edu.kit.informatik.PlayerAndFigure;

/**
 * Encapsulates the game figure
 * @author Sara
 *@version 1.0
 */
public class GameFigure {
    private String name;
    private int row;
    private int col;
    
    /**
     * Instantiates the game figure with the given parameters
     * @param name of the game figure
     * @param row row of the game figure is placed on
     * @param col column of the f´game figure is placed on
     */
    public GameFigure(String name, int row, int col) {
        this.name = name;
        this.row = row;
        this.col = col;
    }
    
    /**
     * gets the name of the game figure
     * @return returns the name of the game figure
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * sets the row and column of the game figure
     * @param row row to be set
     * @param col column to be set
     */
    public void setRowAndCol(int row, int col) {
        this.row = row;
        this.col = col;
    }
    
    /**
     * gets the row and column of the game figure
     * @return returns the row and column of the game figure.
     */
    public int[] getRowAndCol() {
        return new int[] {row, col };
    }
}
