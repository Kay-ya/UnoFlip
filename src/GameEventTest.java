import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class GameEventTest {
    GameEvent eventTrue, eventFalse;
    Card drawCard, removeHandCard;
    @BeforeEach
    void setsUp() {
        drawCard = new Card(CardType.DRAW, CardColor.BLUE, CardType.FIVE, CardColor.PINK);
        removeHandCard = new Card(CardType.DRAW, CardColor.GREEN, CardType.WILD, CardColor.TEAL);
        eventTrue = new GameEvent(new Game());
        eventFalse = new GameEvent(new Game());
    }

    @AfterEach
    void tearDown() {
    }

}