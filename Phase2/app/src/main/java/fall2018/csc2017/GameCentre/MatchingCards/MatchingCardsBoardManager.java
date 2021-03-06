package fall2018.csc2017.GameCentre.MatchingCards;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import java.util.Vector;

import fall2018.csc2017.GameCentre.Board;
import fall2018.csc2017.GameCentre.BoardManager;
import fall2018.csc2017.GameCentre.R;
import fall2018.csc2017.GameCentre.Tile;

/**
 * Manage a board, including swapping tiles, checking for a win, and managing taps.
 */
public class MatchingCardsBoardManager extends BoardManager implements Serializable {
    /**
     * The Board for this game.
     */
    private Board board;

    /**
     * The ID of another identical card.
     */
    private int pairID = 0;

    /**
     * Cards that have been paired.
     */
    private Vector<int[]> clearedCards = new Vector<>();

    /**
     * The tiles the user last clicked on.
     */
    private Stack<int[]> lastClicks = new Stack<>();

    /**
     * The drawables to be used in this game.
     */
    private int[] cardIDs = {
            R.drawable.tile_1, R.drawable.tile_2,
            R.drawable.tile_3, R.drawable.tile_4,
            R.drawable.tile_5, R.drawable.tile_6,
            R.drawable.tile_7, R.drawable.tile_8,
            R.drawable.tile_9, R.drawable.tile_10,
            R.drawable.tile_11, R.drawable.tile_12,
            R.drawable.tile_13, R.drawable.tile_14,
            R.drawable.tile_15, R.drawable.tile_16,
            R.drawable.tile_17, R.drawable.tile_18
    };

    /**
     * Manage a new shuffled board.
     */
    public MatchingCardsBoardManager() {
        setGameName(MATCHING_CARDS_GAME);
        setSavedNumCols(4);
        setSavedNumRows(4);
        createBoard();
    }

    /**
     * Getter method for board
     * @return Board board
     */
    public Board getBoard() {
        return board;
    }

    @Override
    public void createBoard() {
        board = null;
        List<Tile> tiles = new ArrayList<>();
        final int numTiles = Board.NUM_ROWS * Board.NUM_COLS;
        int[] temp = new int[numTiles];
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            if (tileNum % 2 == 0){
                temp[tileNum] = cardIDs[tileNum / 2];
            } else {
                temp[tileNum] = cardIDs[(tileNum - 1) / 2];
            }
            tiles.add(new Tile(tileNum + 1, temp[tileNum]));
        }

        while (board == null) {
            Collections.shuffle(tiles);
            board = new MatchingCardsBoard(tiles);
        }
    }

    /**
     * Return whether the tiles are in row-major order.
     *
     * @return whether the tiles are in row-major order
     */
    @Override
    public boolean puzzleSolved() {
        boolean solved = true;
        Iterator<Tile> iter = board.iterator();
        int expectedId = MatchingCardsBoard.NUM_COLS * MatchingCardsBoard.NUM_ROWS;
        while (iter.hasNext()) {
            if (expectedId != iter.next().getId()) {
                solved = false;
                break;
            }
        }
        if (clearedCards.size() != board.numTiles()) {
            solved = false;
        }
        if (solved) {
            updateScore();
        }
        return solved;
    }

    /**
     * Return whether any of the four surrounding tiles is the blank tile.
     *
     * @param position the tile to check
     * @return whether the tile at position is surrounded by a blank tile
     */
    @Override
    public boolean isValidTap(int position) {

        int row = position / Board.NUM_COLS;
        int col = position % Board.NUM_COLS;
        if (!clearedCards.isEmpty()) {
            for (int i = 0; i != clearedCards.size(); i++) {
                if (clearedCards.elementAt(i)[0] == row &&
                        clearedCards.elementAt(i)[1] == col) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Getter method for LastClicks()
     * @return the two tiles the user last clicked on
     */
    public Stack<int[]> getLastClicks() {
        return lastClicks;
    }

    /**
     * Here is the score formula:
     * Score = 100 + NUM_ROWS * NUM_COLS * 2 - moves - time_in_seconds / 10
     * We give more points for a larger board. We also deduct 1 point for each move, but we revert
     * the score for each move that is undone. For every 10 seconds, we deduct 1 point.
     */
    public void updateScore() {
        long endTime = System.currentTimeMillis();
        score -= (endTime - startTime) / 1000 / 10;
    }

    /**
     * Process a touch at position in the board, swapping tiles as appropriate.
     *
     * @param position the position
     */
    @Override
    public void touchMove(int position) {
        //1D representation of a 2D Cell
        int row = position / Board.NUM_COLS;
        int col = position % Board.NUM_COLS;
        int currentID = board.getTile(row, col).getId();
        int[] clickedOn = {row, col};
        lastClicks.push(clickedOn);
        ((MatchingCardsBoard) board).flipCards();

        if (pairID == 0) {
            clearedCards.add(clickedOn);
            if (currentID % 2 == 0) {
                pairID = currentID - 1;
            } else {
                pairID = currentID + 1;
            }
        } else {
            if (pairID == currentID) {
                int[] toBeCleared = clearedCards.lastElement();
                ((MatchingCardsBoard) board).clearTiles(toBeCleared[0], toBeCleared[1]);
                clearedCards.add(clickedOn);
                ((MatchingCardsBoard) board).clearTiles(row, col);
                pairID = 0;
            } else {
                clearedCards.removeElementAt(clearedCards.size() - 1);
                pairID = 0;
            }
            lastClicks.pop();
            lastClicks.pop();
        }
        score--;
    }

    /**
     * An empty function for undoMove function
     */
    @Override
    public void undoMove() {

    }
}
