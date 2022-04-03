package edu.kit.informatik.GodsCards;

import edu.kit.informatik.Board.ImmutableBoard;
import edu.kit.informatik.Components.Component;

/**
 * Encapsulates the god card Demeter, with the needed method
 * @author Sara
 *@version 1.0
 */
public class Demeter extends GodsCard {
    
    /**
     * represents the maximum number of builds
     */
    private static final int MAX_ALLOWED_BUILDS = 2;
    private int buildCounter;
    
    /**
     * instantiates the god cars Demeter
     */
    public Demeter() {
        super();
        buildCounter = 0;
    }
    
    /**
     * helps the {@link Player} who has this card, to build two times in his turn.
     */
    @Override
    public boolean isBuildAllowed(ImmutableBoard board, Component component, int row, int col) {
        if (buildCounter == MAX_ALLOWED_BUILDS) {
            throw new IllegalArgumentException("you can't build");
        }
        super.isBuildAllowed(board, component, row, col);
        this.isBuildAllowed = true;
        buildCounter++;
        if (buildCounter == MAX_ALLOWED_BUILDS) {
            isBuildAllowed = false;
        }
        return true;
    }
}
