import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains JUnit tests for the Game class, focusing on the
 * functionality of the nextPlayerIndex method to ensure that it works to calculats the index of the next player
 * based on the current index, number of players, and game direction.
 */
class GameTest {
    Game game;

    /**
     * The initial state for each test case.
     */
    @BeforeEach
    void setUp() {
        game = new Game();
    }

    /**
     * Clean up after each test case.
     */
    @AfterEach
    void tearDown() {
    }

    /**
     * Test the nextPlayerIndex method for both clockwise and counter-clockwise directions.
     */
    @Test
    void testNextPlayerIndex() {

        int resultClockwise = game.nextPlayerIndex(1, 4, true);
        assertEquals(0, resultClockwise, "The next player index should be 0");


        int resultCounterClockwise = game.nextPlayerIndex(1, 4, false);
        assertEquals(2, resultCounterClockwise, "The next player index should be 2");
    }

    /**
     * Test the addGameView method
     */
    @Test
    void testAddGameView(){
        GameUpdate mockView = new GameUpdate() {
            @Override
            public void handleUnoUpdate(GameEvent e) {

            }
        };

        game.addGameView(mockView);


        assertEquals(1, game.updateView.size());
        assertEquals(mockView, game.updateView.get(0));
    }

    /**
     * Test for replaceDeckCard
     */
    @Test
    void testReplaceDeckCard(){
        Card card = new Card(CardType.ONE,Color.GREEN,CardType.FIVE,Color.GREEN);
        Player player = new Player("fiope");
        player.addCardToHand(card);
        game.addPlayer(player);
        game.setCurrentPlayer(player);
        game.replaceDeckCard(card);


        assertTrue(player.getHand().isEmpty()); // checks if player's hand is empty
        assertTrue( game.getDeck().topCardFromDiscardPile().equals(card)); // checks if card is in discard pile or not


    }

    /**
     * Test placeCards method
     */
    @Test
    void testPlaceCards(){
        Player player = new Player("fi");
        game.setCurrentPlayer(player);
        Card topDiscardCard = new Card(CardType.TWO, Color.BLUE, CardType.TWO, Color.RED);
        Card brightColorCard = new Card(CardType.TWO, Color.BLUE, CardType.FOUR, Color.GREEN);
        game.placeCards(brightColorCard, topDiscardCard);
        assertTrue(game.getCurrentPlayer().getHand().isEmpty(),"Player hand should be empty");
        assertEquals( brightColorCard, game.getDeck().topCardFromDiscardPile(),"Top card from discard pile should match the expected bright color card");

        Card wildDrawCard = new Card(CardType.WILD_DRAW, Color.WILD, CardType.EIGHT, Color.ORANGE);
        Card topDiscardBright = new Card(CardType.TWO, Color.BLUE, CardType.FOUR, Color.GREEN);
        game.placeCards(wildDrawCard, topDiscardBright);
        assertEquals(2, game.getCurrentPlayer().getHand().size(), "Player's hand should have 2 cards");



        Card wildCard = new Card(CardType.WILD, Color.WILD, CardType.FIVE, Color.GREEN);
        Card topDiscardDark1 = new Card(CardType.DRAW, Color.PINK, CardType.WILD, Color.WILD);
        game.placeCards(wildCard, topDiscardDark1);
        Card actualTopCard = game.getDeck().topCardFromDiscardPile();
        assertEquals(topDiscardDark1, actualTopCard, "Top card should match the discarded card. Expected: " + topDiscardDark1 + ", Actual: " + actualTopCard);


    }
}

