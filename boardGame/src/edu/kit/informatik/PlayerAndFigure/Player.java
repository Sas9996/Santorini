package edu.kit.informatik.PlayerAndFigure;

import edu.kit.informatik.GodsCards.GodsCard;

/**
 * Encapsulates the player class, with the needed methods.
 * @author Sara
 *@version 1.0
 */
public class Player {
    
    /**
     * the number of {@link GameFigure}
     */
    private static final int NUMS_OF_FIGURES = 2;
    
    /**
     * represents the maximum number of the drawn {@link GodsCard}
     */
    private static final int MAX_NUM_OF_DRAWN_CARDS = 3;
    private GodsCard currentGodsCard;
    private String name;
    private GameFigure[] figures;
    private int numberOfDrawnCards;
    
    /**
     * Instantiates a player with the given parameters
     * @param name of the player
     * @param nameOfFigures name of {@link GameFigure}
     * @param coordinate coordinates
     */
    public Player(String name, String[] nameOfFigures, int[][] coordinate) {
        this.name = name;
        this.figures = new GameFigure[NUMS_OF_FIGURES];
        this.currentGodsCard = new GodsCard();
        initFigures(nameOfFigures, coordinate);

    }
    
    /**
     * helping method to initialize the {@link GameFigure}
     * @param nameOfFigures name of {@link GameFigure}
     * @param coordinate coordinates
     */
    private void initFigures(String[] nameOfFigures, int[][] coordinate) {
        for (int i = 0; i < NUMS_OF_FIGURES; i++) {
            figures[i] = new GameFigure(nameOfFigures[i], coordinate[i][0], coordinate[i][1]);
        }
    }
    
    /**
     * gets the number of drawn {@link GodsCard}
     * @return the number of the cards
     */
    public int getNumberOfDrawnCards() {
        return numberOfDrawnCards;
    }
    
    /**
     * gets the current {@link GodsCard}
     * @return current {@link GodsCard}
     */
    public GodsCard getCurrentGodsCard() {
        return currentGodsCard;
    }
    
    /**
     * restores the {@link GodsCard}
     */
    public void restoreGodsCard() {
        this.currentGodsCard = new GodsCard();
    }
  
    /**
     * draws a {@link GodsCard}
     * @param godsCard {@link GodsCard} to be drawn
     */
    public void drawCard(GodsCard godsCard) {
        if (numberOfDrawnCards == MAX_NUM_OF_DRAWN_CARDS) {
            throw new IllegalArgumentException("you have already drawn max numbers of cards");
        }
        numberOfDrawnCards++;
        this.currentGodsCard = godsCard;
    }
    
    /**
     * gets the {@link GameFigure}
     * @param nameOfTheGameFigure name of {@link GameFigure}
     * @return returns the {@link GameFigure}
     * @throws IllegalArgumentException if the {@link GameFigure} does
     * not exist.
     */
    public GameFigure getGameFigure(String nameOfTheGameFigure) throws IllegalArgumentException {
        for (int i = 0; i < NUMS_OF_FIGURES; i++) {
            if (figures[i].getName().equals(nameOfTheGameFigure)) {
                return figures[i];
            }
        }
        throw new IllegalArgumentException("there is no such a figure ");
    }
    
    /**
     * gets the name of the player
     * @return name of the player
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * moves {@link GameFigure}
     * @param figure name of the {@link GameFigure}
     * @param row row of the move
     * @param col column of the move
     */
    public void moveFigure(String figure, int row, int col) {
        GameFigure gameFigure = getGameFigure(figure);
        gameFigure.setRowAndCol(row, col);
    }
    
    /**
     * gets {@link GameFigure}
     * @return returns the {@link GameFigure}
     */
    public GameFigure[] getFigures() {
        return figures;
    }
}
