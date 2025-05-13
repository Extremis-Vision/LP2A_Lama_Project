package Lama;

/**
 * This class represents a game-specific collection of rocks.
 * It extends the Rocks class and initializes the game with specific rocks.
 */
public class RocksGame extends Rocks {

    /**
     * Constructor for the Rocks_Game class.
     * Initializes the game with 50 white rocks and 20 black rocks.
     */
    public RocksGame() {
        super();
        for (int rockWhite = 1; rockWhite <= 50; rockWhite++) {
            this.addRock(Rock.WHITE);
        }
        for (int rockBlack = 1; rockBlack <= 20; rockBlack++) {
            this.addRock(Rock.BLACK);
        }
    }
}
