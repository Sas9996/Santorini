package edu.kit.informatik.GodsCards;

import edu.kit.informatik.Board.ImmutableBoard;
import edu.kit.informatik.Components.Component;

/**
 * Encapsulates the god card Artemis, with needed methods
 * @author Sara
 *@version 1.0
 */
public class Artemis extends GodsCard {
    
    /**
     * represents the maximum allowed moves, if this card was used.
     */
    private static final int MAX_ALLOWED_MOVES = 2;
    private int moveCounter;
    
    /**
     * Instantiates the god card Artemis
     */
    public Artemis() {
        super();
        moveCounter = 0;
    }
    
    /**
     * checks if the wanted move is allowed, and helps the {@link Player} to move
     * according to this card's rule
     */
    @Override
    public boolean isMoveAllowed(ImmutableBoard board, int row1, int col1, int row2, int col2, boolean isMoveUpAllowed)
            throws IllegalArgumentException {
        if (moveCounter == MAX_ALLOWED_MOVES) {
            throw new IllegalArgumentException("you just moved");
        }
        super.isMoveAllowed(board, row1, col1, row2, col2, isMoveUpAllowed);
        this.isMoveAllowed = true;
        moveCounter++;
        return true;
    }
    
    /**
     * checks if the building is allowed
     */
    @Override
    public boolean isBuildAllowed(ImmutableBoard board, Component component, int row, int col)
            throws IllegalArgumentException {
        super.isBuildAllowed(board, component, row, col);
        moveCounter = MAX_ALLOWED_MOVES;
        this.isMoveAllowed = false;
        return true;
    }
}
