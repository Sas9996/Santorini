package edu.kit.informatik.GodsCards;

import edu.kit.informatik.Board.ImmutableBoard;

/**
 * Encapsulates the god card Hermes, with the needed methods.
 * @author Sara
 * @version 1.0
 *
 */
public class Hermes extends GodsCard {
    private boolean[][] booleansBoard;
    
    /**
     * instantiates the god card Hermes.
     */
    public Hermes() {
        super();
        booleansBoard = new boolean[5][5];
    }
    
    /**
     * checks if moving up is allowed, and it doesn't let
     * the {@link Player} to move up while using this card.
     */
    @Override
    public boolean isMoveAllowed(ImmutableBoard board, int row1, int col1, 
            int row2, int col2, boolean isMoveUpAllowed) {
        if (!this.isMoveAllowed) {
            throw new IllegalArgumentException("move isn't allowed");

        }
        if (!checkOfFreeCell(board, row2, col2)) {
            throw new IllegalArgumentException("the cell isn't free");
        }
        if (!traverseBoard(board, row1, col1, row2, col2)) {
            throw new IllegalArgumentException("the move isn't on the same height");
        }
        isMoveAllowed = false;
        isBuildAllowed = true;
        return true;
    }
    
    /**
     * helping method that helps checks the way between two {@link BoardCell}
     * on the {@link ImmutableBoard} to see if the way is blocked
     * @param board {@link ImmutableBoard}
     * @param row1 row of the first {@link BoardCell}
     * @param col1 column of the first {@link BoardCell}
     * @param row2 row of the second {@link BoardCell}
     * @param col2 column of the second {@link BoardCell}
     * @return {@code true} if the way is not blocked, otherwise
     * returns {@code false}
     */
    private boolean traverseBoard(ImmutableBoard board, int row1, int col1, int row2, int col2) {
        if (row1 == row2 && col1 == col2) {
            return true;
        }
        int[] nums = new int[] {-1, 0, 1 };
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                int newRow = row1 + nums[i];
                int newCol = col1 + nums[j];
                if (newRow == row1 && newCol == col1 || newRow > 4 || newCol > 4 || newRow < 0 || newCol < 0) {
                    continue;
                }
                if (compareHeight(board, row1, col1, row2, col2) && !booleansBoard[newRow][newCol]) {
                    booleansBoard[newRow][newCol] = true;
                    traverseBoard(board, newRow, newCol, row2, col2);
                }
            }

        }
        return booleansBoard[row2][col2];
    }
    
    /**
     * helping method which compares the height of two {@link BoardCell}
     * @param board {@link ImmutableBoard}
     * @param row1 row of the first {@link BoardCell}
     * @param col1 column of the first {@link BoardCell}
     * @param row2 row of the second {@link BoardCell}
     * @param col2 column of the second {@link BoardCell}
     * @return returns {@code true} if the height of the two {@link BoardCell}
     * is equals, otherwise returns {@code false}
     */
    private boolean compareHeight(ImmutableBoard board, int row1, int col1, int row2, int col2) {
        if (board.getHeightOfCell(row1, col1) == board.getHeightOfCell(row2, col2)) {
            return true;
        }
        return false;
    }
}
