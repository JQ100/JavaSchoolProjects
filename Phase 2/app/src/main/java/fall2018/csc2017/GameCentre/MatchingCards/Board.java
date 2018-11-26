package fall2018.csc2017.GameCentre.MatchingCards;

import android.graphics.Bitmap;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import fall2018.csc2017.GameCentre.R;
import fall2018.csc2017.GameCentre.Tile;

/**
 * The sliding tiles board.
 */
public class Board extends fall2018.csc2017.GameCentre.Board {
    // TODO: Change NUM_ROWS and NUM_COLS to non-static and add getter/setter methods becuz they belong to each board.

//    /**
//     * The BitMap of background image, if there is not one, remain null
//     */
//    public static Bitmap BACKGROUND_BMAP = null;

    private boolean flipInProgress = false;

    private Stack<int[]> toBeFlipped = new Stack<>();

    /**
     * A new board of tiles in row-major order.
     * Precondition: len(tiles) == NUM_ROWS * NUM_COLS
     *
     * @param tiles the tiles for the board
     */
    Board(List<Tile> tiles) {
        Iterator<Tile> iter = tiles.iterator();

        for (int row = 0; row != Board.NUM_ROWS; row++) {
            for (int col = 0; col != Board.NUM_COLS; col++) {
                this.tiles[row][col] = iter.next();
            }
        }
    }

    void clearTiles(int row, int col) {
//        Tile temp = tiles[row1][col1];
//        tiles[row1][col1] = tiles[row2][col2];
//        tiles[row2][col2] = temp;
        tiles[row][col].setBackground(R.drawable.tile_blank);

        setChanged();
        notifyObservers();
    }

    void flipCards() {
        long startTime = System.currentTimeMillis();

//        while(System.currentTimeMillis() <= startTime + 1500) {
            flipInProgress = true;
            setChanged();
            notifyObservers();
//        }
//
//        flipInProgress = false;
//        setChanged();
//        notifyObservers();
    }

    public Stack<int[]> getToBeFlipped() {
        return toBeFlipped;
    }

    public boolean getFlipInProgress() {
        return flipInProgress;
    }
//    @Override
//    public String toString() {
//        return "Board{" +
//                "tiles=" + Arrays.toString(tiles) +
//                '}';
//    }
}