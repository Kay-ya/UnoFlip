import org.junit.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
        card1 = new Card(CardType.TWO, Color.BLUE, CardType.FIVE, Color.TEAL);
        card2 = new Card(CardType.EIGHT, Color.RED, CardType.NINE, Color.PINK);
        card3 = new Card(CardType.ONE, Color.GREEN, CardType.SEVEN, Color.ORANGE);
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
    void emptyDiscard() {

    }

    @org.junit.jupiter.api.Test
    void addToDiscardPile() {
        Card card = side.remove(0);
        d.addToDiscardPile(card);
        discardCard.add(card);
    }

    @org.junit.jupiter.api.Test
    void topCardFromDiscardFile() {
        //discardCard.get(0);
    }

    @org.junit.jupiter.api.Test
    void removeFromDrawPile() {
    }
}