package edu.kit.informatik;

import edu.kit.informatik.Board.Board;
import edu.kit.informatik.Components.*;
import edu.kit.informatik.GodsCards.*;
import edu.kit.informatik.PlayerAndFigure.GameFigure;
import edu.kit.informatik.PlayerAndFigure.Player;

/**
 * Encapsulates the Santorini game class, where the main needed methods 
 * are to compute the program.
 * @author Sara
 *@version 1.0
 */
public class Game {
    
    /**
     * the number of the cuboids at the beginning of the game.
     */
    private static final int INITIAL_NUM_OF_CUBOIDS = 54;
    
    /**
     * the number of the domes at the beginning of the game.
     */
    private static final int INITIAL_NUM_OF_DOMES = 18;
    private Player player1;
    private Player player2;
    private Player playerToPlay;
    private Board board;
    private int numsOfCuboids;
    private int numsOfDomes;
    private boolean isRunning;
    private boolean cardDrawnAllowed;
    
    /**
     * Instantiates the game with the given parameters.
     * @param namesOfFigures the names of the figures that the {@link Player} use.
     * @param coordinates the coordinates of the game.
     */
    public Game(String[][] namesOfFigures, int[][][] coordinates) {
        this.player1 = new Player("P1", namesOfFigures[0], coordinates[0]);
        this.player2 = new Player("P2", namesOfFigures[1], coordinates[1]);
        playerToPlay = player1;
        this.board = new Board();
        placeFigures();
        numsOfCuboids = INITIAL_NUM_OF_CUBOIDS;
        numsOfDomes = INITIAL_NUM_OF_DOMES;
        isRunning = true;
        cardDrawnAllowed = true;
    }
    
    /**
     * helping method that places the game {@link Figures} on the board.
     */
    private void placeFigures() {
        GameFigure[] figures1 = player1.getFigures();
        GameFigure[] figures2 = player2.getFigures();
        for (int i = 0; i < figures1.length; i++) {
            int row1 = figures1[i].getRowAndCol()[0];
            int col1 = figures1[i].getRowAndCol()[1];
            board.setFigureAt(figures1[i], row1, col1);
            int row2 = figures2[i].getRowAndCol()[0];
            int col2 = figures2[i].getRowAndCol()[1];
            board.setFigureAt(figures2[i], row2, col2);
        }
    }
    
    /**
     * draws a {@link GodsCard} with the given name, each player is allowed to draw three
     * cards in the game, once a card is drawn, it can not be drawn again.
     * @param cardSymbol represents the name of the {@link GodsCard}.
     * @return returns a string, "ok" if the card had been successfully drawn.
     * @throws IllegalArgumentException if the card drawing failed.
     */
    public String drawCard(String cardSymbol) throws IllegalArgumentException {
        if (cardDrawnAllowed) {
            GodsCard godsCard = generateCard(cardSymbol);
            playerToPlay.drawCard(godsCard);
            cardDrawnAllowed = false;
            return "OK";
        }
        throw new IllegalArgumentException("Error, you have already drawn a card");
    }
    
    /**
     * helping method that generates a {@link GodsCard} with the given symbol.
     * @param cardSymbol represents the {@link GodsCard} name
     * @return returns the class of the {@link GodsCard}
     * @throws IllegalArgumentException if the input was invalid.
     */
    private GodsCard generateCard(String cardSymbol) throws IllegalArgumentException {
        switch (cardSymbol) {
        case "Apollo":
            return new Apollo();
        case "Artemis":
            return new Artemis();
        case "Athena":
            return new Athena();
        case "Atlas":
            return new Atlas();
        case "Demeter":
            return new Demeter();
        case "Hermes":
            return new Hermes();
        default:
            throw new IllegalArgumentException("invalid input");
        }
    }
    
    /**
     * moves the game {@link GameFigure} with help of the given parameters.
     * @param figure the {@link GameFigure} that needs to be moved
     * @param rowNum the number of the row
     * @param colNum the number of the column
     * @return returns a String: "ok" if the moving was successful, or the winner if the player
     * moved on top of a tower.
     * @throws IllegalArgumentException if the input is invalid.
     */
    public String move(String figure, int rowNum, int colNum) throws IllegalArgumentException {
        GameFigure gameFigure = playerToPlay.getGameFigure(figure);
        int[] figureCoordinate = gameFigure.getRowAndCol();
        Player otherPlayer = playerToPlay == player1 ? player2 : player1;
        boolean isMoveUpAllowed = otherPlayer.getCurrentGodsCard().isMoveUpAllowed();
        boolean moveCondition = this.playerToPlay.getCurrentGodsCard().isMoveAllowed(board, figureCoordinate[0],
            figureCoordinate[1], rowNum, colNum, isMoveUpAllowed);
        if (moveCondition) {
            cardDrawnAllowed = false;
            board.swapFigure(figureCoordinate[0], figureCoordinate[1], rowNum, colNum);
            playerToPlay.moveFigure(figure, rowNum, colNum);
            int maxNumberOfCuboidOnCell = 3;
            if (board.getHeightOfCell(rowNum, colNum) == maxNumberOfCuboidOnCell) {
                isRunning = false;
                return playerToPlay.getName() + " wins";
            }
            return "OK";
        }
        return "Unknown error";
    }
    
    /**
     * builds a {@link Component} on the {@link Board} with the given parameters.
     * @param component a string represents the name of the {@link Component}, either
     * a "C" for a {@link Cuboid} or a "D" for a {@link Dome}.
     * @param rowNum the number on the row of the {@link BoardCell}.
     * @param colNum the number of the column of the {@link BoardCell}.
     * @return returns a String: "ok" if the building was successful, or the winner {@link Player}.
     * 
     * @throws IllegalArgumentException if the {@link Player} has no more components to build.
     */
    public String build(String component, int rowNum, int colNum) throws IllegalArgumentException {
        Component buildingComponent;
        if (component.equals("C")) {
            if (numsOfCuboids > 0) {
                buildingComponent = new Cuboid("C");
            } else {
                throw new IllegalArgumentException("no enough cuboids");
            }
        } else {
            if (numsOfDomes > 0) {
                buildingComponent = new Dome("D");
            } else {
                throw new IllegalArgumentException("no enough domes");
            }
        }
        boolean buildCondition = playerToPlay.getCurrentGodsCard().isBuildAllowed(board, buildingComponent, rowNum,
                colNum);
        if (buildCondition) {
            if (component.equals("C")) {
                numsOfCuboids--;
            } else if (component.equals("D")) {
                numsOfDomes--;
            }
            board.setComponent(buildingComponent, rowNum, colNum);
        }
        if (numsOfCuboids == 0) {
            isRunning = false;
            return playerToPlay.getName() + " wins";

        }
        nextRound();
        return "OK";
    }
    
    /**
     * shows the {@link Player} whose on turn next
     * @return the {@link Player} name whose turn next.
     */
    public String turn() {
        return playerToPlay.getName();
    }
    
    /**
     * surrenders the active {@link Player} and the next {@link Player} will be
     * the winner.
     * @return returns the name of the winner, which is the next {@link Player}.
     */
    public String surrender() {
        Player player = player1 == playerToPlay ? player2 : player1;
        isRunning = false;
        return player.getName() + " wins";
    }
    
    /**
     * shows the components left in the bag
     * @return the number of each component with their numbers.
     */
    public String bag() {
        return "C;" + numsOfCuboids + " D;" + numsOfDomes;

    }
    
    /**
     * prints what's in the cell on the {@link Board}
     * @param rowNum number of the row of the cell
     * @param colNum number of the column of the cell
     * @return returns what's in the cell.
     */
    public String cellPrint(int rowNum, int colNum) {
        return board.printCell(rowNum, colNum);
    }
    
    /**
     * prints the top view of the {@link Board}
     * @return the structre of the {@link Board} from the top.
     */
    public String print() {
        return board.print();
    }
    
    /**
     * helping method that ensures to turn the player after they do the required
     *  number of move/build.
     */
    private void nextRound() {
        if (playerToPlay.getCurrentGodsCard().canRunNextRound()) {
            playerToPlay = player1 == playerToPlay ? player2 : player1;
            playerToPlay.restoreGodsCard();
            cardDrawnAllowed = true;
        }
    }
    
    /**
     * boolean that shows if the program is running
     * @return {@code true} if the program is running, and {@code false} otherwise.
     */
    public boolean isRunning() {
        return isRunning;
    }

}
