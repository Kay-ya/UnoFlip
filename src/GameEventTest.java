import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class GameEventTest {
    GameEvent eventTrue, eventFalse;
    Card drawCard, removeHandCard;
    @BeforeEach
    void setUp() {
        drawCard = new Card(CardType.DRAW, Color.BLUE, CardType.FIVE, Color.PINK);
        removeHandCard = new Card(CardType.DRAW, Color.GREEN, CardType.WILD, Color.TEAL);
        eventTrue = new GameEvent(new Game(), drawCard, removeHandCard, true);
        eventFalse = new GameEvent(new Game(), drawCard, removeHandCard, false);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void testGetDrawCard() {
        assertEquals(drawCard.toString(), eventTrue.getDrawCard());
    }

    @Test
    public void testGetRemovedCard() {
        assertEquals(removeHandCard.toString(), eventTrue.getRemovedCard());
    }

    @Test
    public void testGetStatus() {
        assertTrue(eventTrue.getStatus());
        assertFalse(eventFalse.getStatus());
    }
}