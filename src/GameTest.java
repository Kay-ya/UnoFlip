import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains JUnit tests for the Game class, focusing on the
 * functionality of the nextPlayerIndex method to ensure that it works to calculats the index of the next player
 * based on the current index, number of players, and game direction.
 */
class GameTest {
    Game game;

    /**
     * The initial state for each test case.
     */
    @BeforeEach
    void setUp() {
        game = new Game();
    }

    /**
     * Clean up after each test case.
     */
    @AfterEach
    void tearDown() {
    }

    /**
     * Test the nextPlayerIndex method for both clockwise and counter-clockwise directions.
     */
    @Test
    void testNextPlayerIndex() {

        int resultClockwise = game.nextPlayerIndex(1, 4, true);
        assertEquals(0, resultClockwise, "The next player index should be 0");


        int resultCounterClockwise = game.nextPlayerIndex(1, 4, false);
        assertEquals(2, resultCounterClockwise, "The next player index should be 2");
    }
}
