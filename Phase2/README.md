GROUP_0658 PHASE 2

### INSTRUCTIONS:

1. Install Android Studio if you haven't already.
2. Retrieve the URL for the starter code repository from MarkUs and clone it using Android Studio.
        - URL: https://markus.teach.cs.toronto.edu/git/csc207-2018-09-reg/group_0658
        - Answer "Yes" to whether you want to create a new Android Studio project.
        - Select "Import project from external model" and click Next
        - Add the project to the Gradle project path
        - If you get the  message "Unregistered VS root detected",  choose "Add root"
3. The project should now build.
4. Create an Android Virtual Device within Android Studio.
        - Select a Pixel2 smartphone to emulate, specifying the device OS as Android 8.1 API 27.
        - Launch the virtual device and ensure it loads correctly.
        - There may be a possible lag with your emulator. For every button you press, please give
        a few seconds to wait for the phone to respond.
5. Now run it, and it should open to the Log In page (see Functionality below).

### FUNCTIONALITY:

Login/Register Page
    - Users can log in to their accounts (saved in HashMap), or
    - Register if they don't have one
    - Once logged in, directed to main game page, where user can choose one of three games
Each Game Page
    - Can start a new game/load saved game/load previous game
    - Can check scoreboards(one for Leaderboard, one for User Scoreboard)
    - Can sign out of account, returning to log in page.
    - Can rate the app (app is not published so it only leads to the PlayStore).
Sliding Tiles Game
    - Once the user starts a new game...
    - Can choose from three different board sizes (3x3/4x4/5x5).
    - Then user has option to use an image as the board tiles by choosing from the images provided
      or import image from phone. If this option is skipped, starts a generic game with number tiles
    - Brought back to main game page when solved
    - Implements Autosave, Save, and Undo
Matching Cards Game
    - Once the user starts a new game...
    - Can choose from three different board sizes (2x2/4x4/6x6).
    - Then the game begins; can flip two cards at a time until no cards remain
    - Brought back to main game page when solved
    - Implements Autosave, Save
Sudoku Game
    - Once the user starts a new game...
    - A random Sudoku board is generated; can enter input until board is solved.
    - Brought back to main game page when solved
    - Implements Autosave, Save, and Undo
