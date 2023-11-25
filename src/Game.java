import java.util.ArrayList;
import java.util.List;

public class Game {
    private ArrayList <Player> players; //Stores the player name in an ArrayList
    private Player currentPlayer, displayedPlayer;
    private Boolean direction, side, isPlaced, isMatchFound; // true (default) = clockwise, true = Bright side, false = Dark side
    private Deck deck;
    private List<GameView> views;
    ArrayList<Card> cards;
    private int currentRound, trackPlayer, totalScore;
    private Status status;

    /**
     * Constructor for the Game class. Initializes the game state, including players, deck, and other parameters.
     */
    public Game(){
        this.players = new ArrayList<>();
        this.direction = false;
        this.currentPlayer = null;
        this.displayedPlayer = null;
        this.deck = new Deck();
        this.side = true;
        this.currentRound =1;
        this.trackPlayer =0;
        this.isPlaced = false;
        this.isMatchFound = false;
        this.views = new ArrayList<>();
        this.cards = new ArrayList<>();
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
    public void addPlayer( Player player) {
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

    public Status getStatus(){
        return this.status;
    }

    /**
     * Adds a GameUpdate listener to the list of views.
     *
     * @param view The GameUpdate object to be added as a listener.
     */
    public void addView(GameView view){
        this.views.add(view);
    }

    /**
     * Initializes AI and Human Players in the game.
     * @param numHumanPlayers
     * @param numAIPlayers
     */
    public void initialize(int numPlayers, int numAIPlayers){ // need test, possible point of failure
        if (numPlayers < 1) {
            throw new IllegalArgumentException("Number of players must be at least 1");
        }

        Player p;
        for (int i = 0; i < numPlayers; i++){
            p = new Player( "Human Player " + (i+1) );
            for (int j = 0; j < 7; j++) {
                p.addCardToHand(getDeck().drawCard());
            }
            addPlayer(p);
        }
        for (int i = 0; i < numAIPlayers; i++){
            p = new Player( "AI Player " + (i+1) );
            for (int j = 0; j < 7; j++) {
                p.addCardToHand(getDeck().drawCard());
            }
            addPlayer(p);
        }
        currentPlayer = players.get(0);
        displayedPlayer = currentPlayer;
    }

    /**
     * Returns the player index of the card to see which direction the game is being played.
     * @param curIndex current index
     * @param numPlayers number of players in game
     * @param direction clockwise or counter-clockwise
     * @return int
     */
    public int nextPlayerIndex(int curIndex, int numPlayers, Boolean direction){
        if (direction) {
            curIndex -= 1;
            if (curIndex == -1){
                curIndex = numPlayers-1;
            }
        }
        else{
            curIndex = (curIndex + 1) % numPlayers;
        }
        return curIndex;
    }

    /**
     * Iterates through the next player when user presses 'Next Player' button.
     */
    public void nextPlayer(CardColor selectedColor) {
        //AI IMPL BEGINS
        Card topDiscardCard = deck.topCardFromDiscardPile();
        if(getCurrentPlayer().getName().contains("AI")){
            // placeCard(cardIndex, selectedColor);
            cards = displayedPlayer.getHand();
            int i=0;
            for(Card handCards : cards){ //Loops over hand cards of current player
                System.out.println(handCards.getBrightCardType() + " : " + handCards.getBrightCardType() + " : " + getSide());
                if(getSide() && (handCards.getBrightColor() == topDiscardCard.getBrightColor()) || handCards.getBrightCardType() == topDiscardCard.getBrightCardType() || (handCards.getBrightColor() == CardColor.WILD)) {
                    selectedColor = CardColor.RED; //Sets the default colour of AI Player WILD CARD to RED
                    placeCard(i, selectedColor);
                    isMatchFound = true;
                    break;
                }
                if(!getSide() && (handCards.getDarkColor() == topDiscardCard.getDarkColor() || handCards.getDarkCardType() == topDiscardCard.getDarkCardType() || (handCards.getDarkColor() == CardColor.WILD))) {

                    selectedColor = CardColor.PINK; //Sets the default colour of AI Player WILD CARD to PINK
                    placeCard(i, selectedColor);
                    isMatchFound = true;
                    break;
                }
                i++;
            }
            if(!isMatchFound){
                getCurrentPlayer().addCardToHand(getDeck().drawCard());
                System.out.println("New Card Drawn");
            }
        }
        //AI IMPL ENDS
        int i = nextPlayerIndex(players.indexOf(currentPlayer), players.size(), direction);
        setCurrentPlayer(players.get(i));
        displayedPlayer = currentPlayer;
        //updateStatus();
        for (GameUpdate view : views) {
            view.handleNextPlayerEvent(new NextPlayerEvent(this, currentPlayer));
            trackPlayer++;
            if(trackPlayer == players.size()) {
                trackPlayer = 0;
                startNewRound();
                view.handleUpdateScoreEvent(new UpdateScoreEvent(this, getCurrentRound(), currentPlayer.getPlayerScore()));
            }
        }
    }

    /**
     * Draws a card from the deck and hands in to the current players hand
     */
    public void drawCard() {
        Card drawnCard = getDeck().drawCard();
        displayedPlayer.addCardToHand(drawnCard);
        //updateStatus();
        for (GameUpdate view : views) {
            view.handleDrawCardEvent(new DrawCardEvent(this, displayedPlayer.getHand()));
        }
    }

    /**
     * Gets the current round being played in the game.
     * @return int
     */
    public int getCurrentRound() {
        return currentRound;
    }

    /**
     * Starts the round and increments the round as the game begins.
     * @return int
     */
    public int startNewRound(){
        return currentRound++;
    }

    /**
     * Game logic where the cards get placed on the discard pile according to the UNO Flip rules.
     * @param cardIndex
     * @param selectedColor
     */
    public boolean placeCard(int cardIndex, CardColor selectedColor) {

        Card handCard = displayedPlayer.getHand().get(cardIndex);
        Card topDiscardCard = deck.topCardFromDiscardPile();
        int index = nextPlayerIndex(players.indexOf(displayedPlayer), players.size(), direction);
        Player nextPlayer = players.get(index);

        // if bright and color or card type match
        if (side && (handCard.getBrightColor() == topDiscardCard.getBrightColor() || handCard.getBrightCardType() == topDiscardCard.getBrightCardType())) {
            int score = getCurrentPlayer().calculateTotalPointsForPlayerHand(side);
            currentPlayer.setPlayerScore(score);
            System.out.println("Normal Bright " + currentPlayer.getPlayerScore());
            if (handCard.getBrightCardType() == CardType.FLIP) {
                int score1 = getCurrentPlayer().calculateTotalPointsForPlayerHand(side);
                currentPlayer.setPlayerScore(score1);
                System.out.println("FLIP Bright " + currentPlayer.getPlayerScore());
                flipSide();
            }
            else if (handCard.getBrightCardType() == CardType.DRAW) {
                int score1 = getCurrentPlayer().calculateTotalPointsForPlayerHand(side);
                currentPlayer.setPlayerScore(score1);
                System.out.println("DRAW Bright " + currentPlayer.getPlayerScore());
                nextPlayer.addCardToHand(deck.drawCard());
            }
            else if (handCard.getBrightCardType() == CardType.REVERSE) {
                int score1 = getCurrentPlayer().calculateTotalPointsForPlayerHand(side);
                currentPlayer.setPlayerScore(score1);
                System.out.println("Reverse Bright " + currentPlayer.getPlayerScore());
                flipDirection();
            }
            else if (handCard.getBrightCardType() == CardType.SKIP) {
                int score1 = getCurrentPlayer().calculateTotalPointsForPlayerHand(side);
                currentPlayer.setPlayerScore(score1);
                System.out.println("Skip Bright " + currentPlayer.getPlayerScore());
                int i = nextPlayerIndex(players.indexOf(currentPlayer), players.size(), direction);
                setCurrentPlayer(players.get(i));
            }
            displayedPlayer.removeCardFromHand(handCard);
            deck.addToDiscardPile(handCard);
            isPlaced = true;
        }
        // if dark and color or card type match
        else if (!this.getSide() && (handCard.getDarkColor() == topDiscardCard.getDarkColor() || handCard.getDarkCardType() == topDiscardCard.getDarkCardType())) {
            if (handCard.getDarkCardType() == CardType.FLIP) {
                flipSide();
            } else if (handCard.getDarkCardType() == CardType.DRAW) {
                for (int j = 0; j < 5; j++) {
                    nextPlayer.addCardToHand(deck.drawCard());
                }
            }
            else if (handCard.getDarkCardType() == CardType.REVERSE) {
                flipDirection();
            }
            else if (handCard.getBrightCardType() == CardType.SKIP) {
                int i = nextPlayerIndex(players.indexOf(currentPlayer), players.size(), !direction);
                setCurrentPlayer(players.get(i));
            }
            displayedPlayer.removeCardFromHand(handCard);
            deck.addToDiscardPile(handCard);
        }
        // light side wild cards
        else if (side && (handCard.getBrightColor() == CardColor.WILD)) {
            handCard.setBrightColor(selectedColor);
            if (handCard.getBrightCardType() == CardType.WILD_DRAW) {
                nextPlayer.addCardToHand(deck.drawCard());
                nextPlayer.addCardToHand(deck.drawCard());
            }

            displayedPlayer.removeCardFromHand(handCard);
            deck.addToDiscardPile(handCard);
            isPlaced = true;
        }
        // dark side wild cards
        else if (!side && (handCard.getDarkColor() == CardColor.WILD)) {
            handCard.setDarkColor(selectedColor);
            if (handCard.getDarkCardType() == CardType.WILD_DRAW) {
                Card drawnCard = new Card(null,null,null,null);
                while (drawnCard.getDarkColor() != selectedColor){
                    drawnCard = deck.drawCard();
                    nextPlayer.addCardToHand(drawnCard);
                }
            }

            displayedPlayer.removeCardFromHand(handCard);
            deck.addToDiscardPile(handCard);
            isPlaced = true;
        }
        for (GameUpdate view : views) {
            view.handlePlaceCardEvent(new PlaceCardEvent(this, displayedPlayer.getHand(), deck.topCardFromDiscardPile()));
        }
        return isPlaced;
    }
}