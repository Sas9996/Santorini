package edu.kit.informatik.Board;

import edu.kit.informatik.Components.Component;
import edu.kit.informatik.Components.Cuboid;
import edu.kit.informatik.Components.Dome;
import edu.kit.informatik.PlayerAndFigure.GameFigure;

/**
 * Encapsulates the Cell of the {@link Board}
 * @author Sara
 *@version 1.0
 */
public class BoardCell {
    
    /**
     * represents the maximum number of the {@link Cuboid} that is allowed to
     * be built on the cell.
     */
    public static final int NUMS_OF_MAX_CUBOID = 3;
    private Cuboid[] cuboids;
    private int amountOfCuboids;
    private GameFigure figure;
    private Dome dome;
    
    /**
     * Instantiates the {@link BoardCell}.
     */
    public BoardCell() {
        this.cuboids = new Cuboid[NUMS_OF_MAX_CUBOID];

    }
    
    /**
     * checks if the cell has no {@link GameFigure} or a {@link Dome}
     * @return returns {@code true} if it has the mentioned 
     * pieces, or {@code false} otherwise.
     */
    public boolean isFree() {
        return figure == null && dome == null;
    }
    
    /**
     * sets the {@link GameFigure} on the cell
     * @param figure {@link GameFigure} that needs to be sets
     * @throws IllegalArgumentException
     */
    public void setFigure(GameFigure figure) throws IllegalArgumentException {
        this.figure = figure;
    }
    
    /**
     * returns the amount of {@link Cuboid} on the cell
     * @return the amount of the {@link Cuboid}.
     */
    public int boardCellHeight() {
        return amountOfCuboids;
    }
    
    /**
     * sets the {@link Component} on the cell.
     * @param component {@link Component} that needs to be set
     */
    public void setComponent(Component component) {
        if (component instanceof Dome) {
            setDome((Dome) component);
        } else {
            setCuboids((Cuboid) component);
        }
    }
    
    /**
     * helping method that sets a {@link Dome} on the cell
     * @param dome {@link Dome} that needs to be set.
     */
    private void setDome(Dome dome) {
        this.dome = dome;
    }
    
    /**
     * sets the {@link Cuboid} on the cell
     * @param cuboid {@link Cuboid} that needs to be set.
     */
    private void setCuboids(Cuboid cuboid) {
        this.cuboids[amountOfCuboids] = cuboid;
        this.amountOfCuboids++;
    }
    
    /**
     * gets the {@link GameFigure} on the cell
     * @return returns the {@link GameFigure}.
     */
    public GameFigure getFigure() {
        return figure;
    }
    
    /**
     * removes the {@link GameFigure} off the cell
     * @return removes the cell
     */
    public GameFigure removeFigure() {
        GameFigure gameFigure = this.figure;
        this.figure = null;
        return gameFigure;
    }
    
    /**
     * prints the elements in the cell as a String, each element is
     * represented with its name and separated from the other with a comma
     * @return a Sting representation of the cell's elements from bottom to top.
     */
    public String printCell() {
        String res = "";
        for (int i = 0; i < cuboids.length; i++) {
            if (cuboids[i] != null) {
                res += cuboids[i].getSymbol() + ",";
            }
        }
        if (dome != null) {
            res += dome.getSymbol() + ",";
        }
        if (figure != null) {
            res += figure.getName() + ",";
        }
        if (res != "") {
            res = res.substring(0, res.length() - 1);
            return res;
        }
        return res;
    }
    
    /**
     * prints the upper view of the cell
     * @return a String representation of the upper cell's element,
     * or a dot if the cell is empty.
     */
    public String printUpper() {
        if (dome != null) {
            return dome.getSymbol();
        }
        if (figure != null) {
            return figure.getName();
        }
        for (int i = 0; i < cuboids.length; i++) {
            if (cuboids[i] != null) {
                return cuboids[i].getSymbol();
            }
        }
        return ".";
    }
    
    /**
     * checks if the cell has a {@link Dome}
     * @return {@code true} if it has a {@link Dome}, or {@code false} otherwise.
     */
    public boolean hasDome() {
        return dome != null;
    }
}
