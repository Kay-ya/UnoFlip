import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This is the Test class for the Card class, which represents a card in an UNO game.
 */
class CardTest {
    Card card, card1;

    /**
     * The initial state for each test case.
     */


    @BeforeEach
    void setUp() {

        card = new Card(CardType.DRAW, Color.BLUE, CardType.FIVE, Color.PINK);
        card1 = new Card(CardType.DRAW, Color.GREEN, CardType.WILD, Color.TEAL);
    }


    /**
     * Cleans up after each test case.
     */
    @AfterEach
    void tearDown() {
    }

    /**
     * Tests the getBrightCardType method of the Card class.
     */

    @Test
    void getBrightCardType() {
        assertEquals(CardType.DRAW, card.getBrightCardType());
        assertNotEquals(CardType.FIVE, card1.getBrightCardType());
    }
    /**
     * Tests the getDarkCardType method of the Card class.
     */

    @Test
    void getDarkCardType() {
        assertEquals(CardType.FIVE, card.getDarkCardType());
        assertNotEquals(CardType.REVERSE, card1.getDarkCardType());
    }

    /**
     * Tests the getBrightColor method of the Card class.
     */

    @Test
    void getBrightColor() {
        assertEquals(Color.BLUE, card.getBrightColor());
        assertNotEquals(Color.RED, card1.getBrightColor());
    }

    /**
     * Tests the getDarkColor method of the Card class.
     */

    @Test
    void getDarkColor() {
        assertEquals(Color.PINK, card.getDarkColor());
        assertNotEquals(Color.ORANGE, card1.getDarkColor());
    }
}