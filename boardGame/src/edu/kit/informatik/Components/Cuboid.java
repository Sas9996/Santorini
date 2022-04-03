package edu.kit.informatik.Components;

/**
 * Encapsulates the cuboid which used in building
 * on the {@link Board}
 * @author Sara
 *@version 1.0
 */
public class Cuboid implements Component {
    private String symbol;
    
    /**
     * Instantiates the cuboid with the given parameters.
     * @param symbol the symbol representing the cuboid
     */
    public Cuboid(String symbol) {
        this.symbol = symbol;
    }
    
    /**
     * returns the symbol of this cuboid
     */
    @Override
    public String getSymbol() {
        return this.symbol;
    }
}
