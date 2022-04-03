package edu.kit.informatik.GodsCards;

import edu.kit.informatik.Board.ImmutableBoard;

/**
 * Encapsulates the god card Apollo, with needed method of the card.
 * @author Sara
 *@version 1.0
 */
public class Apollo extends GodsCard {
    
    /**
     * instantiates the god card Apollo
     */
    public Apollo() {
        super();
    }
    
    /**
     * the method of {@link Apollo} which allows the {@link Player} 
     * to move to a reserved {@link BoardCell} with the other {@link Player}
     * {@link GameFigure} and it swaps the {@link GameFigure} places.
     * @return {@code true} if the swap was successful, otherwise returns {@code false}
     */
    @Override
    public boolean isMoveAllowed(ImmutableBoard board, int row1, int col1, int row2, int col2, boolean isMoveUpAllowed)
            throws IllegalArgumentException {
        if (!this.checkOfHeightOnMove(board, row1, col1, row2, col2, isMoveUpAllowed)) {
            throw new IllegalArgumentException("you can't make the move because of height difference");
        }
        if (!this.checkRangeOfMove(board, row1, col1, row2, col2)) {
            throw new IllegalArgumentException("you can't make the move because of range");
        }
        if (board.hasCellDome(row2, col2)) {
            throw new IllegalArgumentException("there is dome");
        }
        isMoveAllowed = false;
        isBuildAllowed = true;
        return true;
    }

}
