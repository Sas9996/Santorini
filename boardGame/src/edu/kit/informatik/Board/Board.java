package edu.kit.informatik.Board;

import edu.kit.informatik.Components.Component;
import edu.kit.informatik.PlayerAndFigure.GameFigure;

/**
 * Encapsulates the Board on which the {@link Game} Santorini takes place.
 * @author Sara
 *@version 1.0
 */
public class Board implements ImmutableBoard {
    
    /**
     * represents the number of the rows on the board.
     */
    private static final int NUMS_OF_ROWS = 5;
    
    /**
     * represents the number of columns on the board.
     */
    private static final int NUMS_OF_COLUMNS = 5;
    private BoardCell[][] boardCells;
    
    /**
     * Instantiates the board.
     */
    public Board() {
        this.boardCells = new BoardCell[NUMS_OF_ROWS][NUMS_OF_COLUMNS];
        this.initBoard();
    }
    
    /**
     * helping method that initializes the board from {@link BoardCells}.
     */
    private void initBoard() {
        for (int i = 0; i < NUMS_OF_ROWS; i++) {
            for (int j = 0; j < NUMS_OF_COLUMNS; j++) {
                boardCells[i][j] = new BoardCell();
            }

        }
    }
    
    /**
     * boolean that checks whether the {@link BoardCell} has a {@link GameFigure}.
     * @param row on the {@link BoardCell}
     * @param col column on the {@link BoardCell}
     * @return returns {@link BoardCell}
     */
    public boolean isCellFree(int row, int col) {
        return boardCells[row][col].isFree();
    }
    
    /**
     * sets the {@link Component} at the given coordinates on the {@link BoardCell}.
     * @param component to be set
     * @param row of the {@link BoardCell}
     * @param col column of the {@link BoardCell}
     */
    public void setComponent(Component component, int row, int col) {

        boardCells[row][col].setComponent(component);
    }
    
    /**
     * swaps the {@link GameFigure} at the given coordinates 
     * on the {@link BoardCell}.
     * @param oldRow the number of the old row
     * @param oldCol the number of the old column
     * @param newRow the number of the new row
     * @param newCol the number of the new column
     */
    public void swapFigure(int oldRow, int oldCol, int newRow, int newCol) {
        GameFigure figure = boardCells[oldRow][oldCol].removeFigure();
        GameFigure figureDes = boardCells[newRow][newCol].getFigure();
        boardCells[newRow][newCol].setFigure(figure);
        if (figureDes != null) {
            boardCells[oldRow][oldCol].setFigure(figureDes);
            figureDes.setRowAndCol(oldRow, oldCol);
        }
    }
    
    /**
     * returns the height of the {@link BoardCell} with the given parameters.
     * @param row of the {@link BoardCell}
     * @param col column of the {@link BoardCell}
     * @return the height of the wanted {@link BoardCell}.
     */
    public int getHeightOfCell(int row, int col) {
        return boardCells[row][col].boardCellHeight();
    }
    
    /**
     * checks if the {@link BoardCell} has a {@link Dome}.
     * @return {@code true} if it has a {@link Dome}, and {@code false} otherwise.
     */
    @Override
    public boolean hasCellDome(int row, int col) {
        return boardCells[row][col].hasDome();
    }
    
    /**
     * prints the {@link BoardCell} 
     * @param rowNum the number of the row of the {@link BoardCell}
     * @param colNum the number of the column of the {@link BoardCell}
     * @return prints what's in the {@link BoardCell}.
     */
    public String printCell(int rowNum, int colNum) {
        return boardCells[rowNum][colNum].printCell();
    }
    
    /**
     * prints the top view of the board
     * @return String represents the top view of each {@link BoardCell} on
     * the board.
     */
    public String print() {
        String res = "";
        for (int i = 0; i < NUMS_OF_ROWS; i++) {
            for (int j = 0; j < NUMS_OF_COLUMNS; j++) {
                res += boardCells[i][j].printUpper();
                res += ",";
                if (j == NUMS_OF_COLUMNS - 1) {
                res = res.substring(0, res.length() - 1);
                }
            }
            res += '\n';
            if (i == NUMS_OF_ROWS - 1) {
                res = res.substring(0, res.length() - 1);
            }
        }
        return res;
    }
    
    /**
     * sets the {@link GameFigure} on the wanted {@link BoardCell} with
     * help of the given parameters
     * @param figure the {@link GameFigure} that needs to be set
     * @param row the row of the {@link BoardCell}.
     * @param col the column of the {@link BoardCell}.
     */
    public void setFigureAt(GameFigure figure, int row, int col) {
        boardCells[row][col].setFigure(figure);
    }
}
