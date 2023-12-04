import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Game extends DefaultHandler implements Serializable {
    private ArrayList <Player> players; //Stores the player name in an ArrayList
    private Player currentPlayer, displayedPlayer;
    private Boolean direction, side; // true (default) = clockwise, true = Bright side, false = Dark side
    private Deck deck;
    private transient List<GameView> views;
    //Card card;
    private int currentRound, trackPlayer;
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
        this.status = Status.NEW_TURN;
        //this.card = new Card();
        this.views = new ArrayList<>();
        //this.cards = new ArrayList<>();
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
     * Adds a GameUpdate listener to the list of views.
     *
     * @param view The GameUpdate object to be added as a listener.
     */
    public void addView(GameView view){
        this.views.add(view);
    }

    /**
     * Initializes AI and Human Players in the game.
     * @param numPlayers
     * @param numAIPlayers
     */
    public void initialize(int numPlayers, int numAIPlayers){ // need test, possible point of failure
        if (numPlayers + numAIPlayers < 1) {
            throw new IllegalArgumentException("Number of players must be at least 1");
        }

        Player p;
        for (int i = 0; i < numPlayers; i++){
            p = new Player( "Human Player " + (i+1) );
            for (int j = 0; j < 7; j++) {
                p.addCardToHand(deck.drawCard());
            }
            addPlayer(p);
        }
        for (int i = 0; i < numAIPlayers; i++){
            p = new Player( "AI Player " + (i+1) );
            for (int j = 0; j < 7; j++) {
                p.addCardToHand(deck.drawCard());
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
    public void nextPlayer() {
        ArrayList<Card> cards;
        boolean isMatchFound = false;
        //AI IMPL BEGINS
        CardColor selectedColor;
        Card topDiscardCard = deck.topCardFromDiscardPile();
        if(getCurrentPlayer().getName().contains("AI")){
            // placeCard(cardIndex, selectedColor);
            cards = displayedPlayer.getHand();
            int i=0;
            for(Card handCards : cards){ //Loops over hand cards of current player
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
                drawCard();
                System.out.println("New Card Drawn");
            }
        }
        //AI IMPL ENDS
        int i = nextPlayerIndex(players.indexOf(currentPlayer), players.size(), direction);
        setCurrentPlayer(players.get(i));
        displayedPlayer = currentPlayer;
        status = Status.NEW_TURN;
        for (GameUpdate view : views) {
            view.handleNextPlayerEvent(new NextPlayerEvent(this, currentPlayer, status));
            trackPlayer++;
            if(trackPlayer == players.size()) {
                trackPlayer = 0;
                for(Player p: players){
                    System.out.println(p.getName());
                    if(this.getSide()) {
                        p.setPlayerScore(p.calculateTotalPointsForPlayerHand(side));
                        System.out.println("Get player BRIGHT" + " " + p.getName() + " " + p.getPlayerScore());
                        view.handleUpdateScoreEvent(new UpdateScoreEvent(this, p.getPlayerScore(), p.getName(), status));
                    }
                    else {
                        p.setPlayerScore(p.calculateTotalPointsForPlayerHand(!side));
                        System.out.println("Get player DARK" + " " + p.getName() + " " + p.getPlayerScore());
                        view.handleUpdateScoreEvent(new UpdateScoreEvent(this, p.getPlayerScore(), p.getName(), status));
                    }
                    if(p.getPlayerScore() > 500 || p.getPlayerScore() == 500){
                        System.out.println(p.getName() + " WINS!");
                        view.handleUpdateScoreEvent(new UpdateScoreEvent(this, p.getPlayerScore(), p.getName(), status));
                    }
                }
                startNewRound();
                view.handleUpdateScoreEvent(new UpdateScoreEvent(this, getCurrentRound(), status));
            }
        }
    }

    /**
     * Draws a card from the deck and hands in to the current players hand
     */
    public void drawCard() {
        Card drawnCard = deck.drawCard();
        displayedPlayer.addCardToHand(drawnCard);
        status = Status.CARD_DRAWN;
        for (GameUpdate view : views) {
            view.handleDrawCardEvent(new DrawCardEvent(this, displayedPlayer.getHand(), status));
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
    public void startNewRound(){
        currentRound++;
    }

    /**
     * Game logic where the cards get placed on the discard pile according to the UNO Flip rules.
     * @param cardIndex
     * @param selectedColor
     */
    public boolean placeCard(int cardIndex, CardColor selectedColor) {
        boolean isPlaced = false;
        status = Status.CARD_PLACED;
        Card handCard = displayedPlayer.getHand().get(cardIndex);
        Card topDiscardCard = deck.topCardFromDiscardPile();
        int index = nextPlayerIndex(players.indexOf(displayedPlayer), players.size(), direction);
        Player nextPlayer = players.get(index);
        // if light and color or card type match
        if (side && (handCard.getBrightColor() == topDiscardCard.getBrightColor() || handCard.getBrightCardType() == topDiscardCard.getBrightCardType())) {
            if (handCard.getBrightCardType() == CardType.FLIP) {
                flipSide();
            }
            else if (handCard.getBrightCardType() == CardType.DRAW) {
                nextPlayer.addCardToHand(deck.drawCard());
            }
            else if (handCard.getBrightCardType() == CardType.REVERSE) {
                flipDirection();
            }
            else if (handCard.getBrightCardType() == CardType.SKIP) {
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
            isPlaced = true;
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
                Card drawnCard = new Card(null, null, null, null);
                while (drawnCard.getDarkColor() != selectedColor) {
                    drawnCard = deck.drawCard();
                    nextPlayer.addCardToHand(drawnCard);
                }
            }

            displayedPlayer.removeCardFromHand(handCard);
            deck.addToDiscardPile(handCard);
            isPlaced = true;
        }
        else{
            status = Status.INVALID;
        }

        for (GameUpdate view : views) {
            view.handlePlaceCardEvent(new PlaceCardEvent(this, displayedPlayer.getHand(), deck.topCardFromDiscardPile(), status));
        }
        return isPlaced;
    }

    public void saveGame(String fileName){
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(this);
            System.out.println("Game saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadGame(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            Game loadedGame = (Game) ois.readObject();
            System.out.println("Game loaded successfully.");
            this.deck = loadedGame.deck;
            this.currentPlayer = loadedGame.currentPlayer;
            this.displayedPlayer = loadedGame.displayedPlayer;
            this.direction = loadedGame.direction;
            this.side = loadedGame.side;
            this.currentRound = loadedGame.currentRound;
            this.trackPlayer = loadedGame.trackPlayer;
            //this.cards = loadedGame.cards;
            this.players = loadedGame.players;
            this.status = loadedGame.status;

            for(GameUpdate view: views){
                view.handleNextPlayerEvent(new NextPlayerEvent(this, displayedPlayer, status));
                view.handleUpdateScoreEvent(new UpdateScoreEvent(this, currentRound, status));
                view.handlePlaceCardEvent(new PlaceCardEvent(this, displayedPlayer.getHand(), deck.topCardFromDiscardPile(), status));
                //view.handleGameView(new )
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            //return null;
        }
    }

    /**public Game returnLoadedGame(String fileName){
        Game g = loadGame(fileName);
        return g;
    }**/
    //public static void main(String[] args) {
       // Game g = new Game();
        //g.saveGame("unoGame.ser");

        //Game loadedGame = Game.loadGame("unoGame.ser");

        //System.out.println(g.serializeToXml());
        //.save("game.txt");
    //}

}