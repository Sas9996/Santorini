package edu.kit.informatik.GodsCards;

import edu.kit.informatik.Board.ImmutableBoard;

/**
 * Encapsulates the god card Athena with the needed methods.
 * @author Sara
 * @version 1.0
 *
 */
public class Athena extends GodsCard {
    private boolean movedUp;
    
    /**
     * Instantiates the god card Athena.
     */
    public Athena() {
        super();
    }
    
    /**
     * checks if the move is allowed and prevents the other player from moving up.
     */
    @Override
    public boolean isMoveAllowed(ImmutableBoard board, int row1, int col1, int row2, int col2, boolean isMoveUpAllowed)
            throws IllegalArgumentException {
        super.isMoveAllowed(board, row1, col1, row2, col2, isMoveUpAllowed);
        movedUp = isMovedUp(board, row1, col1, row2, col2);
        return true;
    }
    
    /**
     * helping method that checks if the {@link Player} has moved up.
     * @param board the {@link ImmutableBoard}
     * @param row1 the row of the first cell on the {@link Board}
     * @param col1 the column of the first cell on the {@link Board}
     * @param row2 the row of the second cell on the {@link Board}
     * @param col2 the column of the second cell on the {@link Board}
     * @return {@code true} if the move was up, otherwise returns {@code false}.
     * @throws IllegalArgumentException
     */
    private boolean isMovedUp(ImmutableBoard board, int row1, int col1, int row2, int col2)
            throws IllegalArgumentException {
        return board.getHeightOfCell(row2, col2) > board.getHeightOfCell(row1, col1);
    }

    /**
     * checks if the move up is allowed
     */
    @Override
    public boolean isMoveUpAllowed() {
        return !movedUp;
    }


}
