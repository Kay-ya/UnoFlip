import java.util.ArrayList;
import java.util.List;

/**
 * The Game class represents the Uno Flip game. It manages players, the game deck, and game state.
 */
public class Game {
    private ArrayList<Player> players; // Stores the player names in an ArrayList
    private Player currentPlayer;
    List<GameUpdate> updateView;
    private Boolean direction; // true (default) = clockwise,
    private Deck deck;
    boolean status;
    private Boolean side; // true = Bright side, false = Dark side

    /**
     * Constructor for the Game class. Initializes the game state, including players, deck, and other parameters.
     */
    public Game() {
        this.players = new ArrayList<>();
        this.direction = false;
        this.currentPlayer = null;
        this.deck = new Deck();
        this.side = true;
        this.status = false;
        updateView = new ArrayList<>();
    }

    /**
     * Getter for the list of players in the game.
     *
     * @return An ArrayList containing Player objects.
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * Adds a player to the game.
     *
     * @param player The Player object to be added to the game.
     */
    public void addPlayer(Player player) {
        players.add(player);
    }

    /**
     * Getter for the current player in the game.
     *
     * @return The current Player object.
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Sets the current player in the game.
     *
     * @param currentPlayer The Player object to be set as the current player.
     */
    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    /**
     * Getter for the game direction (clockwise or counter-clockwise).
     *
     * @return true if the direction is clockwise, false otherwise.
     */
    public Boolean getDirection() {
        return direction;
    }

    /**
     * Flips the game direction between clockwise and counter-clockwise.
     */
    public void flipDirection() {
        direction = !direction;
    }

    /**
     * Getter for the game deck.
     *
     * @return The Deck object representing the game deck.
     */
    public Deck getDeck() {
        return deck;
    }

    /**
     * Sets the game deck to the provided Deck object.
     *
     * @param deck The Deck object to be set as the game deck.
     */
    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    /**
     * Getter for the game side (Bright side or Dark side).
     *
     * @return true if the game is on the Bright side, false if on the Dark side.
     */
    public Boolean getSide() {
        return side;
    }

    /**
     * Flips the game side between Bright side and Dark side.
     */
    public void flipSide() {
        side = !side;
    }

    /**
     * Calculates the next player index based on the current index, the number of players, and the direction of play.
     *
     * @param curIndex     The current player index.
     * @param numPlayers   The total number of players in the game.
     * @param direction    The direction of play (clockwise or counter-clockwise).
     * @return The next player index.
     */
    public int nextPlayerIndex(int curIndex, int numPlayers, Boolean direction) {
        if (direction) {
            curIndex -= 1;
            if (curIndex == -1) {
                curIndex = numPlayers - 1;
            }
        } else {
            curIndex = (curIndex + 1) % numPlayers;
        }
        return curIndex;
    }

    /**
     * Adds a GameUpdate listener to the list of views.
     *
     * @param view The GameUpdate object to be added as a listener.
     */
    public void addGameView(GameUpdate view) {
        updateView.add(view);
    }

    /**
     * Replaces a card in the player's hand with the specified card and adds the replaced card to the discard pile.
     *
     * @param handCard The card to be replaced in the player's hand.
     */
    public void replaceDeckCard(Card handCard) {
        this.getCurrentPlayer().removeCardFromHand(handCard);
        this.getDeck().addToDiscardPile(handCard);
    }
    /**
     * manages the discard pile card placement in accordance with the game's regulations
     * and modifies the status of the game.
     * @param handCard        The card from the player's hand to be placed on the discard pile.
     * @param topDiscardCard  The top card from the discard pile.
     */

    public void placeCards(Card handCard, Card topDiscardCard){
        if (this.getSide() && (handCard.getBrightColor() == topDiscardCard.getBrightColor() || handCard.getBrightCardType() == topDiscardCard.getBrightCardType())) {
            replaceDeckCard(handCard);
            if(handCard.getBrightCardType() == CardType.FLIP){
                flipSide();
                status = false;
            }
            else if(handCard.getBrightCardType() == CardType.DRAW){
                this.getCurrentPlayer().addCardToHand(deck.drawCard());
                status = false;
            }
            status = false;
        } else if (!this.getSide() && (handCard.getDarkColor() == topDiscardCard.getDarkColor() || handCard.getDarkCardType() == topDiscardCard.getDarkCardType())) {
            this.getCurrentPlayer().removeCardFromHand(handCard);
            this.getDeck().addToDiscardPile(handCard);
            status = false;
            //removeCard = handCard;
            replaceDeckCard(handCard);
            if(handCard.getDarkCardType() == CardType.FLIP){
                flipSide();
                status = false;
            }
            else if(handCard.getDarkCardType() == CardType.DRAW){
                for(int i =0; i<5; i++) {
                    this.getCurrentPlayer().addCardToHand(deck.drawCard());
                }
                status = false;
            }
            status = false;
        }
        else if (this.getSide() && handCard.getBrightCardType() == CardType.WILD_DRAW){
            replaceDeckCard(handCard);
            for(int i =0; i<2; i++) {
                this.getCurrentPlayer().addCardToHand(deck.drawCard());
            }
            status = false;
        } else if (!this.getSide() && handCard.getDarkCardType() == CardType.WILD) {
            replaceDeckCard(handCard);
            status = false;
        } else if (this.getSide() && handCard.getBrightCardType() == CardType.WILD){
            replaceDeckCard(handCard);
            status = false;
        }
        else{
            System.out.println("Invalid Card");
            status = true;
        }
        for (GameUpdate view: updateView){
            view.handleUnoUpdate(new GameEvent(this, topDiscardCard, handCard, status));
        }
    }

}