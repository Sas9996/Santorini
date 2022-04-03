package edu.kit.informatik.Board;

/**
 * Encapsulates an interface of the immutable board.
 * @author Sara
 *@version 1.0
 */
public interface ImmutableBoard {
    boolean isCellFree(int row, int col);
    int getHeightOfCell(int row, int col);
    boolean hasCellDome(int row, int col);
}
