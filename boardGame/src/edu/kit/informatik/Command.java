package edu.kit.informatik;

/**
 * Represents the Command class, where the program commands are.
 * @author Sara
 *@version 1.0
 */
public enum Command {
    
    /**
     * the draw-card command which uses the{@link drawCard} method to draw a {@link Gods} card.
     */
    DRAW_CARD("draw-card (Apollo|Artemis|Athena|Atlas|Demeter|Hermes)") {
        @Override
        public void execute(String parameters, Game game) throws IllegalArgumentException {
            Terminal.printLine(game.drawCard(parameters));
        }

    },
    
    /**
     * the move command which uses the {@link move} method to move player's figures.
     */
    MOVE("move [a-zA-Z]+;(0|1|2|3|4);(0|1|2|3|4)") {
        @Override
        public void execute(String parameters, Game game) throws IllegalArgumentException {
            String[] splitted = parameters.split(";");
            String figure = splitted[0];
            int row = 0;
            int col = 0;
            try {
                row = Integer.parseInt(splitted[1]);
                col = Integer.parseInt(splitted[2]);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("invalid parameters");
            }
            Terminal.printLine(game.move(figure, row, col));
        }
    },
   
    /**
     * the build command which uses the {@link build} method, to build on the board.
     */
    BUILD("build (C|D);(0|1|2|3|4);(0|1|2|3|4)") {
        @Override
        public void execute(String parameters, Game game) throws IllegalArgumentException {
            String[] splitted = parameters.split(";");
            String figure = splitted[0];
            int row = 0;
            int col = 0;
            try {
                row = Integer.parseInt(splitted[1]);
                col = Integer.parseInt(splitted[2]);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("invalid parameters");
            }
            Terminal.printLine(game.build(figure, row, col));
        }

    },
    
    /**
     * the turn command which uses the {@link turn method} that shows 
     * which is the current active player.
     */
    TURN("turn") {
        @Override
        public void execute(String parameters, Game game) throws IllegalArgumentException {
            Terminal.printLine(game.turn());
        }

    },
    
    /**
     * surrender command which uses the {@link surrender} method, that enables the current 
     * player to surrender the game.
     */
    SURRENDER("surrender") {
        @Override
        public void execute(String parameters, Game game) {
            Terminal.printLine(game.surrender());
        }
    },
    
    /**
     * the bag command, which uses the {@link bag} method, that shows how 
     * many building stones are left.
     */
    BAG("bag") {
        @Override
        public void execute(String parameters, Game game) throws IllegalArgumentException {
            Terminal.printLine(game.bag());
        }
    },
    
    /**
     * the cell print command, which uses the {@link cellPrint} method, that prints 
     * the components of the cell.
     */
    CELL_PRINT("cellprint (0|1|2|3|4);(0|1|2|3|4)") {
        @Override
        public void execute(String parameters, Game game) throws IllegalArgumentException {
            String[] splitted = parameters.split(";");
            int row = 0;
            int col = 0;
            try {
                row = Integer.parseInt(splitted[0]);
                col = Integer.parseInt(splitted[1]);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("invalid parameters");
            }
            Terminal.printLine(game.cellPrint(row, col));
        }
    },
    
    /**
     * the print command, which uses the {@link print} method, that prints the 
     * top view of the board.
     */
    PRINT("print") {
        @Override
        public void execute(String parameters, Game game) throws IllegalArgumentException {
            Terminal.printLine(game.print());
        }
    },
    
    /**
     * The quit command, which finishes the program.
     */
    QUIT("quit") {
        @Override
        public void execute(String parameters, Game game) {
            quit();
        }
    };

    /**
     * Contains the game state.
     */
    private boolean isRunning;

    /**
     * Contains this commands input format.
     */
    private String pattern;

    /**
     * Constructs a new command.
     *
     * @param pattern The regex pattern to use if the input valid
     */
    Command(String pattern) {
        this.isRunning = true;
        this.pattern = pattern;
    }
   
    /**
     * the executeMatching method, which matches the input with the commands
     * 
     * @param input input of the user
     * @param product of the game
     * @return returns the command
     * @throws IllegalArgumentException if the command is invalid
     */
    public static Command executeMatching(String input, Game product) throws IllegalArgumentException {
        for (Command command : Command.values()) {
            if (input.matches(command.pattern)) {
                command.execute(input.substring(input.indexOf(" ") + 1), product);
                return command;
            }
        }

        throw new IllegalArgumentException("Error, not a valid command!");
    }

    /**
     * Checks if the program is still running.
     *
     * @return {@code true} if the program is still running.
     */
    public boolean isRunning() {
        return isRunning;
    }

    /**
     * Exit the program
     */
    protected void quit() {
        isRunning = false;
    }
    
    /**
     * abstract method execute, which executes the commands with the given parameters
     * @param parameters the given parameters that needs to be executed in the commands
     * @param game Santorini game
     * @throws IllegalArgumentException
     */
    public abstract void execute(String parameters, Game game) throws IllegalArgumentException;
}
