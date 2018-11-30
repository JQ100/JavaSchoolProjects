package fall2018.csc2017.GameCentre.MatchingCards;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import fall2018.csc2017.GameCentre.AccountManager;
//import fall2018.csc2017.GameCentre.BoardManager;
import fall2018.csc2017.GameCentre.Board;
import fall2018.csc2017.GameCentre.BoardManager;
import fall2018.csc2017.GameCentre.LoadAndSave;
import fall2018.csc2017.GameCentre.R;
import fall2018.csc2017.GameCentre.SlidingTiles.SlidingTilesGameActivity;
import fall2018.csc2017.GameCentre.Sudoku.SudokuGameActivity;

// We do not consolidate this with Game/ComplexityActivity because there is too much difference
// between them.
public class MatchingCardsComplexity extends AppCompatActivity {

    /**
     * The board manager.
     */
    private BoardManager boardManager;
    /**
     * The account manager.
     */
    private AccountManager accountManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matching_complexity);

        accountManager = (AccountManager) LoadAndSave.loadFromFile(
                LoadAndSave.ACCOUNT_MANAGER_FILENAME, this);
        if (accountManager == null) {
            accountManager = new AccountManager();
            LoadAndSave.saveToFile(LoadAndSave.ACCOUNT_MANAGER_FILENAME, accountManager, this);
        }

        boardManager = (BoardManager) LoadAndSave.loadFromFile(
                accountManager.getCurrentAccount().getCurrentGameFileName(), this);

        addButton2Listener();
        addButton4Listener();
        addButton6Listener();
    }

    /**
     * Activate the 2x2 button.
     */
    private void addButton2Listener() {
        Button startButton = findViewById(R.id.matchingbutton2);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Todo: remove the following extra code after fixing BoardManager()
                Board.NUM_ROWS = 2;
                Board.NUM_COLS = 2;
                boardManager.createBoard();
                boardManager.setSavedNumRows(2);
                boardManager.setSavedNumCols(2);
                switchToGame();
            }
        });
    }

    /**
     * Activate the 4x4 button.
     */
    private void addButton4Listener() {
        Button startButton = findViewById(R.id.matchingbutton4);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Board.NUM_ROWS = 4;
                Board.NUM_COLS = 4;
                boardManager.createBoard();
                boardManager.setSavedNumCols(4);
                boardManager.setSavedNumRows(4);
                switchToGame();
            }
        });
    }

    /**
     * Activate the 6x6 button.
     */
    private void addButton6Listener() {
        Button startButton = findViewById(R.id.matchingbutton6);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Board.NUM_ROWS = 6;
                Board.NUM_COLS = 6;
                boardManager.createBoard();
                boardManager.setSavedNumRows(6);
                boardManager.setSavedNumCols(6);
                switchToGame();
            }
        });
    }

//    /**
//     * Switch to the GameActivity view to play the game.
//     */
//    private void switchToBackgroundChange() {
//        Intent tmp = new Intent(this, ImageActivity.class);
//        LoadAndSave.saveToFile(LoadAndSave.ACCOUNT_MANAGER_FILENAME, accountManager, this);
//        saveCurrentBoardManager();
//        startActivity(tmp);
//    }

    /**
     * Save the current BoardManager.
     */
    private void saveCurrentBoardManager() {
        LoadAndSave.saveToFile(accountManager.getCurrentAccount().getCurrentGameFileName(),
                boardManager, this);
    }

    /**
     * Switch to the SudokuGameActivity view to play the game.
     */
    private void switchToGame() {
        LoadAndSave.saveToFile(LoadAndSave.ACCOUNT_MANAGER_FILENAME, accountManager, this);
        Intent tmp;
        switch (boardManager.getGameName()) {
            case BoardManager.SLIDING_TILES_GAME:
                tmp = new Intent(this, SlidingTilesGameActivity.class);
                break;
            case BoardManager.MATCHING_CARDS_GAME:
                tmp = new Intent(this, MatchingCardsGameActivity.class);
                break;
            default:
                tmp = new Intent(this, SudokuGameActivity.class);
        }
        saveCurrentBoardManager();
        startActivity(tmp);
    }
}