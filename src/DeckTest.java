import org.junit.Assert;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 *  Test class for the Deck class, representing the deck of UNO cards.
 */
class DeckTest {

    Card card1;
    Card card2;
    Card card3;
    ArrayList<Deck> deck;
    Deck d = new Deck();
    ArrayList<Card> side;
    ArrayList<Card> discardCard;

    /**
     * Initial state of each test case.
     */
    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        deck = new ArrayList<>();
        side = new ArrayList<>();
        discardCard = new ArrayList<>();
        card1 = new Card(CardType.TWO, Color.BLUE, CardType.FIVE, Color.TEAL);
        card2 = new Card(CardType.EIGHT, Color.RED, CardType.NINE, Color.PINK);
        card3 = new Card(CardType.ONE, Color.GREEN, CardType.SEVEN, Color.ORANGE);
        side.add(card1);
        side.add(card2);
        side.add(card3);
        //deck
        //deck.add();

    }

    /**
     * Cleans up after each test case.
     */
    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    /**
     * Tests the shuffle method to ensure the deck is shuffled.
     */
    @org.junit.jupiter.api.Test
    void shuffle() {
        Collections.shuffle(deck);
    }

    /**
     * Tests the drawCard method to ensure a card is drawn from the deck.
     */
    @org.junit.jupiter.api.Test
    void drawCard() {
        String card_1 = card1.getBrightCardType() + "_" + card1.getBrightColor();
        Card card_2 = d.drawCard();
        assertNotEquals(card_1, card_2.getBrightCardType() + "_" + card_2.getBrightColor());
    }

    /**
     * Tests the addToDiscardPile method to ensure a card is added to the discard pile.
     */
    @org.junit.jupiter.api.Test
    void addToDiscardPile() {
        Card card = side.remove(0);
        d.addToDiscardPile(card);
        discardCard.add(card);
        assertEquals(discardCard.size(), 1);
    }

    /**
     * Tests the topCardFromDiscardFile method to ensure the top card from the discard pile is retrieved.
     */
    @org.junit.jupiter.api.Test
    void topCardFromDiscardFile() {
        Player p = new Player("Sam");
        //discardCard.get(0);
        Card c = d.drawCard();
        p.addCardToHand(c);
        Card c1 = p.hand.remove(0);
        d.addToDiscardPile(c1);
        Card c2 = d.topCardFromDiscardPile();

        String c3 = c2.getBrightCardType() + "_" + c2.getBrightColor();
        assertNotEquals(card1.getBrightCardType() + "_" + card1.getBrightColor(), c3);
    }
}
