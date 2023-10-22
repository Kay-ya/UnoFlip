import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    Game game;

    @BeforeEach
    void setUp() {
        game = new Game();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getPlayers() {
    }

    @Test
    void setPlayers() {
    }

    @Test
    void testNextPlayerIndex(){
        int result = game.nextPlayerIndex(1, 4, true);
        int resultFalse = game.nextPlayerIndex(1, 4, false);

        assertEquals(0,result);
        assertEquals(2,resultFalse);
    }

    @Test
    void addPlayer() {
    }

    @Test
    void removePlayer() {
    }

    @Test
    void printPlayer() {
    }
}
