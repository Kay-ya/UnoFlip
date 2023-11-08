import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    Player player;
    Card card, card1, card2;
    @BeforeEach
    public void setUp() {

        player = new Player("Player 1");
        card = new Card(CardType.FIVE, Color.BLUE, CardType.SKIP, Color.PINK);
        card1 = new Card(CardType.FLIP, Color.GREEN, CardType.EIGHT, Color.TEAL);
        card2 = new Card(CardType.FIVE, Color.ORANGE, CardType.WILD, Color.WILD);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void testAddCardToHand() {

        player.addCardToHand(card);
        assertTrue(player.hand.contains(card), "The hand should contain the added card");
        player.addCardToHand(card1);
        assertTrue(player.hand.contains(card1), "The hand should contain the added card");
        player.addCardToHand(card2);
        assertTrue(player.hand.contains(card2), "The hand should contain the added card");
        assertEquals(player.hand.size(), 3);

        assertEquals(player.hand.get(0), card, "The first drawn card and card in hand should be equal");
        assertEquals(player.hand.get(1), card1, "The second drawn card and card in hand should be equal");
        assertEquals(player.hand.get(2), card2, "The third drawn card and card in hand should be equal");
    }

    @Test
    public void testRemoveCardFromHand() {
        player.addCardToHand(card);
        player.addCardToHand(card1);
        player.addCardToHand(card2);
        assertEquals(player.hand.size(), 3);
        player.removeCardFromHand(card);
        assertEquals(player.hand.size(), 2);
        player.removeCardFromHand(card);
        assertEquals(player.hand.size(), 1);
        player.removeCardFromHand(card);
        assertFalse(player.hand.contains(card), "The hand should not contain the removed card");
    }

    @Test
    public void testCalculateTotalPointsForPlayerHand() {

        // no cards in hand yet, points should be 0 if bright side
        int brightPoints = player.calculateTotalPointsForPlayerHand(true);
        int darkPoints = player.calculateTotalPointsForPlayerHand(true);
        assertEquals(0, brightPoints, "Points should be correctly calculated");

        // no cards in hand yet, points should be 0 if dark side
        darkPoints = player.calculateTotalPointsForPlayerHand(false);
        assertEquals(0, darkPoints, "Points should be correctly calculated");

        player.addCardToHand(card1);

        // only flip card on bright side, flip = 20 points
        brightPoints = player.calculateTotalPointsForPlayerHand(true);
        assertEquals(20, brightPoints, "Points should be correctly calculated");

        // only 8 on dark side, 8 = 8 points
        darkPoints = player.calculateTotalPointsForPlayerHand(false);
        assertEquals(8, darkPoints, "Points should be correctly calculated");

        player.addCardToHand(card2);

        // flip card + five card on bright side, flip + Five = 20 + 5  = 25 points
        brightPoints = player.calculateTotalPointsForPlayerHand(true);
        assertEquals(25, brightPoints, "Points should be correctly calculated");

        // eight card + wild card on dark side, eight + wild = 8 + 40  = 48 points
        darkPoints = player.calculateTotalPointsForPlayerHand(false);
        assertEquals(48, darkPoints, "Points should be correctly calculated");
    }

    @Test
    public void testPlayerScore() {
        int initialScore = player.getPlayerScore();
        player.setPlayerScore(initialScore + 10);
        assertEquals(initialScore + 10, player.getPlayerScore(), "Player score should be updated correctly");
    }

    @Test
    public void testName() {
        assertEquals("Player 1", player.getName(), "Player name should be 'Player 1'");
    }

}