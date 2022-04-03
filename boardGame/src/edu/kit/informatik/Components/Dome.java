package edu.kit.informatik.Components;

/**
 * Represents the dome which is used in building on the {@link Board}.
 * @author Sara
 *@version 1.0
 */
public class Dome implements Component {
    private String symbol;
    
    /**
     * Instantiates the dome with the given parameter
     * @param symbol represents the Dome
     */
    public Dome(String symbol) {
        this.symbol = symbol;
    }
    
    /**
     * returns the symbol of the dome.
     */
    @Override
    public String getSymbol() {
        return this.symbol;
    }
}
