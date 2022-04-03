package edu.kit.informatik.GodsCards;

import edu.kit.informatik.Board.BoardCell;
import edu.kit.informatik.Board.ImmutableBoard;
import edu.kit.informatik.Components.Component;
import edu.kit.informatik.Components.Cuboid;
import edu.kit.informatik.Components.Dome;

/**
 * Encapsulates the Gods cards with the needed methods
 * @author Sara
 *@version 1.0
 */
public class GodsCard {
    /**
     * checks if the move is allowed.
     */
    protected boolean isMoveAllowed;
    
    /**
     * checks if the build is allowed.
     */
    protected boolean isBuildAllowed;
    
    /**
     * instantiates the Godscard
     */
    public GodsCard() {
        isMoveAllowed = true;
        isBuildAllowed = false;
    }
    
    /**
     * checks if the move is allowed for the {@link Player}
     * @param board {@link ImmutableBoard} on which the {@link Player} moves.
     * @param row1 row of the first {@link BoardCell}
     * @param col1 column of the first {@link BoardCell}
     * @param row2 row of the second {@link BoardCell}
     * @param col2 column of the second {@link BoardCell}
     * @param isMoveUpAllowed boolean returns if the move up is allowed
     * @return {@code true} if the move is allowed, otherwise returns
     * {@code false}.
     * @throws IllegalArgumentException
     */
    public boolean isMoveAllowed(ImmutableBoard board, int row1, int col1, int row2, int col2, boolean isMoveUpAllowed)
            throws IllegalArgumentException {
        if (!isMoveAllowed) {
            throw new IllegalArgumentException("you just moved");
        }
        if (!this.checkOfHeightOnMove(board, row1, col1, row2, col2, isMoveUpAllowed)) {
            throw new IllegalArgumentException("you can't make the move because of height difference");
        }
        if (!this.checkRangeOfMove(board, row1, col1, row2, col2)) {
            throw new IllegalArgumentException("you can't make the move because of range");
        }
        if (!checkOfFreeCell(board, row2, col2)) {
            throw new IllegalArgumentException("the cell isn't free");
        }
        isMoveAllowed = false;
        isBuildAllowed = true;
        return true;
    }

    /**
     * checks if the building is allowed
     * @param board {@link ImmutableBoard} on which is the building
     * @param component {@link Component} stone for building
     * @param row of the {@link BoardCell}
     * 
     * @param col column of the {@link BoardCell}
     * @return {@code true} if the building is allowed, otherwise returns
     * {@code false}.
     * @throws IllegalArgumentException
     */
    public boolean isBuildAllowed(ImmutableBoard board, Component component, int row, int col)
            throws IllegalArgumentException {
        if (!isBuildAllowed) {
            throw new IllegalArgumentException("you just built");
        }
        if (component instanceof Cuboid && !isCuboidAllowed(board, row, col)) {
            throw new IllegalArgumentException("you can't build cuboid");
        }
        if (component instanceof Dome && !isDomeAllowed(board, row, col)) {
            throw new IllegalArgumentException("you can't build dome");
        }
        isBuildAllowed = false;
        return true;

    }
    
    /**
     * checks if the {@link Player} can play a next move or build.
     * @return if the move is allowed, and if the building is allowed
     */
    public boolean canRunNextRound() {
        return !isMoveAllowed && !isBuildAllowed;
    }
    
    /**
     * checks if the height of the move with the given parameters
     * @param board {@link ImmutableBoard} on which is the moving
     * @param row1 row of the first {@link BoardCell}
     * @param col1 column of the first {@link BoardCell}
     * @param row2 row of the second {@link BoardCell}
     * @param col2 column of the second {@link BoardCell}
     * @param isMoveUpAllowed boolean checks if the moving up is
     * allowed
     * @return {@code false} if the height of the second {@link BoardCell}
     * more than the height of the first {@link BoardCell} or if the 
     * moving up is not allowed, otherwise returns {@code true} 
     */
    protected boolean checkOfHeightOnMove(ImmutableBoard board, int row1, int col1, int row2, int col2,
                                          boolean isMoveUpAllowed) {
        int height1 = board.getHeightOfCell(row1, col1);
        int height2 = board.getHeightOfCell(row2, col2);
        if (height2 > height1 + 1) {
            return false;
        }
        if (!isMoveUpAllowed && height2 > height1) {
            return false;
        }
        return true;
    }
    
    /**
     * checks if the {@link BoardCell} is free
     * @param board {@link ImmutableBoard}
     * 
     * @param row row of the {@link BoardCell}
     * @param col column of the {@link BoardCell}
     * @return returns {@code true} if the {@link BoardCell} is free,
     * otherwise returns {@code false}
     */
    protected boolean checkOfFreeCell(ImmutableBoard board, int row, int col) {
        return board.isCellFree(row, col);
    }
    
    /**
     * checks the range of the move with the given parameters
     * @param board {@link ImmutableBoard}
     * @param row1 row of the first {@link BoardCell}
     * @param col1 column of the first {@link BoardCell}
     * @param row2 row of the second {@link BoardCell}
     * @param col2 column of the second {@link BoardCell}
     * @return returns rowCondition and the column colCondition.
     */
    protected boolean checkRangeOfMove(ImmutableBoard board, int row1, int col1, int row2, int col2) {
        boolean rowCondition = (row1 >= row2 - 1 && row1 < row2 + 1) || (row2 >= row1 - 1 && row2 < row1 + 1);
        boolean colCondition = (col1 >= col2 - 1 && col1 < col2 + 1) || (col2 >= col1 - 1 && col2 < col1 + 1);
        return rowCondition && colCondition;
    }
    
    /**
     * checks if building with a {@link Dome} is allowed
     * @param board {@link ImmutableBoard}
     * @param row row of the {@link BoardCell}
     * @param col column of the {@link BoardCell}
     * @return {@code true} if {@link Dome} building is allowed, otherwise
     * returns {@code false}
     */
    protected boolean isDomeAllowed(ImmutableBoard board, int row, int col) {
        int height = board.getHeightOfCell(row, col);
        if (height == BoardCell.NUMS_OF_MAX_CUBOID && board.isCellFree(row, col)) {
            return true;
        }
        return false;
    }
    
    /**
     * checks if building with a {@link Cuboid} is allowed
     * @param board {@link ImmutableBoard}
     * @param row row of the {@link BoardCell} to build on
     * @param col column of the {@link BoardCell} to build on
     * @return {@code true} if building a {@link Cuboid} is allowed,
     * otherwise returns {@code false}
     */
    protected boolean isCuboidAllowed(ImmutableBoard board, int row, int col) {
        if (board.getHeightOfCell(row, col) < BoardCell.NUMS_OF_MAX_CUBOID && board.isCellFree(row, col)) {
            return true;
        }
        return false;
    }
    
    /**
     * checks if moving up is allowed
     * @return returns {@code true} if moving up is allowed
     */
    public boolean isMoveUpAllowed() {
        return true;
    }
}
