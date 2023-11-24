import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {

    Card card1;
    Card card2;
    Card card3;
    ArrayList<Deck> deck;
    Deck d = new Deck();
    ArrayList<Card> side;
    ArrayList<Card> discardCard;



    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        deck = new ArrayList<>();
        side = new ArrayList<>();
        discardCard = new ArrayList<>();
        card1 = new Card(CardType.TWO, CardColor.BLUE, CardType.FIVE, CardColor.TEAL);
        card2 = new Card(CardType.EIGHT, CardColor.RED, CardType.NINE, CardColor.PINK);
        card3 = new Card(CardType.ONE, CardColor.GREEN, CardType.SEVEN, CardColor.ORANGE);
        side.add(card1);
        side.add(card2);
        side.add(card3);
        //deck
        //deck.add();

    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @org.junit.jupiter.api.Test
    void shuffle() {
        Collections.shuffle(deck);
    }

    @org.junit.jupiter.api.Test
    void drawCard() {
        String card_1 = card1.getBrightCardType() + "_" + card1.getBrightColor();
        Card card_2 = d.drawCard();
        assertNotEquals(card_1,card_2.getBrightCardType()+ "_" + card_2.getBrightColor());
    }

    @org.junit.jupiter.api.Test
    void addToDiscardPile() {
        Card card = side.remove(0);
        d.addToDiscardPile(card);
        discardCard.add(card);
        assertEquals(discardCard.size(), 1);
    }

    @org.junit.jupiter.api.Test
    void topCardFromDiscardFile() {
        Player p = new Player("Sam");
        //discardCard.get(0);
        Card c = d.drawCard();
        p.addCardToHand(c);
        Card c1 = p.hand.remove(0);
        d.addToDiscardPile(c1);
        Card c2 = d.topCardFromDiscardPile();

        String c3 = c2.getBrightCardType() + "_" + c2.getBrightColor() ;
        assertNotEquals(card1.getBrightCardType()+ "_" + card1.getBrightColor(), c3);

    }
}