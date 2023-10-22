import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.*;

public class PlayerTest {
    Player player, player1, player2;
    Card card1, card2, card3, card4, card5;

    @BeforeEach
    void setUp() {

        card1 = new Card(CardType.EIGHT, Color.RED, CardType.FIVE, Color.ORANGE);
        card2 = new Card(CardType.THREE, Color.YELLOW, CardType.THREE, Color.TEAL);
        card3= new Card(CardType.TWO, Color.BLUE, CardType.ONE, Color.PURPLE);
        card4 = new Card(CardType.FOUR, Color.GREEN, CardType.NINE, Color.ORANGE);
        card5= new Card(CardType.WILD, Color.BLUE, CardType.WILD, Color.PINK);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void testCalculatePoints(){
        player = new Player("Bob");
        player.addCardToHand(card1);
        player.addCardToHand(card2);
        player.addCardToHand(card3);
        player.addCardToHand(card4);
        player.addCardToHand(card5);
        assertEquals(player.calculateTotalPointsForPlayerHand(), 57);
    }

    @Test
    public void testAddCardToHand(){
        player1 = new Player("Bob");
        player1.addCardToHand(card1);
        player1.addCardToHand(card2);
        assertEquals(player1.hand.size(), 2);
    }

    @Test
    public void testRemoveCardFromHand(){
        player2 = new Player("Bob");
        player2.addCardToHand(card1);
        player2.addCardToHand(card2);
        player2.addCardToHand(card3);
        player2.addCardToHand(card4);
        player2.addCardToHand(card5);
        assertEquals(player2.hand.size(), 5);
        player2.removeCardFromHand(card4);
        player2.removeCardFromHand(card5);
        assertEquals(player2.hand.size(), 3);
    }
}
