package fall2018.csc2017.GameCentre;

import java.io.Serializable;

import static fall2018.csc2017.GameCentre.SlidingTiles.SlidingTilesBoard.NUM_COLS;
import static fall2018.csc2017.GameCentre.SlidingTiles.SlidingTilesBoard.NUM_ROWS;

/**
 * Manage a board, including swapping tiles, checking for a win, and managing taps.
 */
abstract public class BoardManager implements Serializable {
    public static final String SLIDING_TILES_GAME = "Sliding Tiles";
    public static final String MATCHING_CARDS_GAME = "Matching Cards";
    public static final String SUDOKU_GAME = "Sudoku";

    /**
     * The board being managed.
     */
    protected Board board;
    protected int score = 0;
    /**
     * Records start time of game or the resume time of game (in milliseconds)
     */
    protected long startTime = 0;
    /**
     * The saved number of rows
     */
    private int savedNumRows = NUM_ROWS;
    /**
     * The saved number of columns
     */
    private int savedNumCols = NUM_COLS;

    /**
     * The name of the Game
     */
    private String gameName;

    /**
     * If the game is saved
     */
    private boolean saved = false;

    /**
     * Return the current board.
     */
    public abstract Board getBoard();

    public abstract void createBoard();

    public abstract boolean puzzleSolved();

    public abstract boolean isValidTap(int position);

    public abstract void touchMove(int position);

    public abstract void undoMove();


    /**
     * Setter method for the Game Name
     * @param gameName The name of the game
     */
    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    /**
     * Getter Method for the Game Name
     * @return the ID of the game
     */
    public String getGameName() { return gameName; }

    /**
     * getter method for the score
     * @return score
     */
    public int getScore() {
        return score;
    }

    /**
     * Updates score
     */
    public abstract void updateScore();

    /**
     * Sets starting score and time
     */
    public void setStartingScoreAndTime() {
        score = 100 + NUM_ROWS * NUM_COLS * 2;
        updateStartTime();
    }

    /**
     * Updates start time
     */
    public void updateStartTime() {
        startTime = System.currentTimeMillis();
    }

    /**
     * Getter Method (for testing purposes)
     * @return the start time
     */
    long getStartTime() {
        return startTime;
    }

    /**
     * Setter Method (for testing purposes)
     */
    void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    /**
     * Getter Method
     * @return saved number of columns
     */
    public int getSavedNumCols() {
        return savedNumCols;
    }

    /**
     * Getter Method
     * @return saved number of rows
     */
    public int getSavedNumRows() {
        return savedNumRows;
    }

    /**
     * Setter Method
     * @param numCols
     * @return the set number of columns
     */
    public void setSavedNumCols(int numCols) {
        this.savedNumCols = numCols;
        NUM_COLS = numCols;
    }

    /**
     * Setter Method
     * @param numRows
     * @return the set number of rows
     */
    public void setSavedNumRows(int numRows) {
        this.savedNumRows = numRows;
        NUM_ROWS = numRows;
    }

    /**
     * Getter method.
     * @return the value of boolean saved
     */
    public boolean getSaved() {
        return saved;
    }

    /**
     * Setter method for boolean saved.
     * @param saved whether the game has been saved or not
     */
    public void setSaved(boolean saved) {
        this.saved = saved;
    }

}
