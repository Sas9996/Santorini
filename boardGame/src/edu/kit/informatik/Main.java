package edu.kit.informatik;

/**
 * Main class of the program, contains the entry point
 * and the input/output constants.
 * @author Sara
 *@version 1.0
 */
public class Main {
    /**
     * the main method where the program can be executed
     *
     * @param args an array of the arguments, where the user enter
     */
    public static void main(String[] args) {
        String[] names = getNamesOfFigures(args);
        String[][] namesOfFigures = new String[][] {{names[0], names[1] }, {names[2], names[3] } };
        int[][] coordinates = getCoordinates(args);
        int[][][] coordinatesOfFigures = new int[][][] {{coordinates[0], coordinates[1] },
                {coordinates[2], coordinates[3] } };
        Game game = new Game(namesOfFigures, coordinatesOfFigures);
        Command command = null;
        do {
            try {
                command = Command.executeMatching(Terminal.readLine(), game);
            } catch (IllegalArgumentException e) {
                Terminal.printError(e.getMessage());
            }
        } while (command == null || (command.isRunning() && game.isRunning()));
    }
    
    /**
     * helping method that gets the name of {@link GameFigure}, to help
     * computing the main method.
     * @param args of the main method
     * @return returns the name of the {@link GameFigure}.
     */
    private static String[] getNamesOfFigures(String[] args) {
        String[] namesOfFigures = new String[4];
        for (int i = 0; i < args.length; i++) {
            namesOfFigures[i] = args[i].split(";")[0];
        }
        return namesOfFigures;
    }
    
    /**
     * helping method that returns the coordinates, to help
     * computing the main method.
     * @param args of the main method
     * @return the coordinates on the {@link Board}.
     */
    private static int[][] getCoordinates(String[] args) {
        int[][] coordinates = new int[4][2];
        int row = 0;
        int col = 0;
        for (int i = 0; i < args.length; i++) {
            String[] splitedArray = args[i].split(";");
            try {
                row = Integer.parseInt(splitedArray[1]);
                col = Integer.parseInt(splitedArray[2]);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("invalid format");
            }
            coordinates[i] = new int[] {row, col };
        }
        return coordinates;

    }
}
