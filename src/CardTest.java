import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {
    Card card, card1;


    @BeforeEach
    void setUp() {

        card = new Card(CardType.DRAW, Color.BLUE, CardType.FIVE, Color.PINK);
        card1 = new Card(CardType.DRAW, Color.GREEN, CardType.WILD, Color.TEAL);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getBrightCardType() {
        assertEquals(CardType.DRAW, card.getBrightCardType());
        assertNotEquals(CardType.FIVE, card1.getBrightCardType());
    }

    @Test
    void getDarkCardType() {
        assertEquals(CardType.FIVE, card.getDarkCardType());
        assertNotEquals(CardType.REVERSE, card1.getDarkCardType());
    }

    @Test
    void getBrightColor() {
        assertEquals(Color.BLUE, card.getBrightColor());
        assertNotEquals(Color.RED, card1.getBrightColor());
    }

    @Test
    void getDarkColor() {
        assertEquals(Color.PINK, card.getDarkColor());
        assertNotEquals(Color.ORANGE, card1.getDarkColor());
    }
}