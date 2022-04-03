package edu.kit.informatik.GodsCards;

import edu.kit.informatik.Board.ImmutableBoard;
import edu.kit.informatik.Components.Component;
import edu.kit.informatik.Components.Cuboid;
import edu.kit.informatik.Components.Dome;

/**
 * Encapsulates the god card Atlas
 * @author Sara
 *@version 1.0
 */
public class Atlas extends GodsCard {
    
    /**
     * instantiates the god card Atlas
     */
    public Atlas() {
        super();
    }
    
    /**
     * helps building a {@link Dome} on any level, so that it blocks
     *  the other {@link Player} moves
     */
    @Override
    public boolean isBuildAllowed(ImmutableBoard board, Component component, int row, int col)
            throws IllegalArgumentException {
        if (!isBuildAllowed) {
            throw new IllegalArgumentException("you just built");
        }
        if (component instanceof Cuboid && !isCuboidAllowed(board, row, col)) {
            throw new IllegalArgumentException("you can't build cuboid");
        }
        if (component instanceof Dome && !board.isCellFree(row, col)) {
            throw new IllegalArgumentException("you can't build dome");
        }
        isBuildAllowed = false;
        return true;
    }
}
