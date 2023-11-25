import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private ArrayList <Player> players; //Stores the player name in an ArrayList
    private Player currentPlayer;
    private Player displayedPlayer;
    private Boolean direction; // true (default) = clockwise,
    private Deck deck;
    private Boolean side; // true = Bright side, false = Dark side
    private List<GameView> views;

    private Status status;

    public Game(){
        this.players = new ArrayList<>();
        this.direction = false;
        this.currentPlayer = null;
        this.displayedPlayer = null;
        this.deck = new Deck();
        this.side = true;
        this.views = new ArrayList<>();
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void addPlayer( Player player) {
        players.add(player);
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Boolean getDirection() {
        return direction;
    }

    public void flipDirection() {
        direction = !direction;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public Boolean getSide() {
        return side;
    }

    public void flipSide() {
        side = !side;
    }

    public Status getStatus(){
        return this.status;
    }
    public void addView(GameView view){
        this.views.add(view);
    }

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

    public void nextPlayer(CardColor selectedColor) {
        ArrayList<Card> cards = new ArrayList<>();
        boolean isMatchFound = false;
        Card topDiscardCard = deck.topCardFromDiscardPile();

        //-----------------FOR AI ----------------------------------------------------
        if(getCurrentPlayer().getName().contains("AI")){


           // placeCard(cardIndex, selectedColor);
            cards = displayedPlayer.getHand();
            int i=0;
            for(Card handCard : cards){ //Loops over hand cards of current player
                System.out.println(handCard.getBrightCardType() + " : " + handCard.getBrightCardType() + " : " + getSide());
                if(getSide() && (handCard.getBrightColor() == topDiscardCard.getBrightColor() || handCard.getBrightCardType() == topDiscardCard.getBrightCardType() || (handCard.getBrightColor() == CardColor.WILD))) {
                    selectedColor = CardColor.RED; //Sets the default colour of AI Player WILD CARD to RED
                    placeCard(i, selectedColor);
                    isMatchFound = true;
                    break;
                }
                if(!getSide() && (handCard.getDarkColor() == topDiscardCard.getDarkColor() || handCard.getDarkCardType() == topDiscardCard.getDarkCardType() || (handCard.getDarkColor() == CardColor.WILD))) {

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
        //--------------------------------------------------------------------------

        int i = nextPlayerIndex(players.indexOf(currentPlayer), players.size(), direction);
        setCurrentPlayer(players.get(i));
        System.out.println("getCurrentPlayer 2: " + getCurrentPlayer().getName());
        displayedPlayer = currentPlayer;
        //updateStatus();
        for (GameUpdate view : views) {
            view.handleNextPlayerEvent(new NextPlayerEvent(this, currentPlayer));
        }
    }

    public void drawCard() {
        Card drawnCard = getDeck().drawCard();
        displayedPlayer.addCardToHand(drawnCard);
        //updateStatus();
        for (GameUpdate view : views) {
            view.handleDrawCardEvent(new DrawCardEvent(this, displayedPlayer.getHand()));
        }
    }
    public boolean placeCard(int cardIndex, CardColor selectedColor) {
        Card handCard = displayedPlayer.getHand().get(cardIndex);
        System.out.println("HAND card  " + handCard.getBrightCardType());
        System.out.println("In placeCard handCard" + handCard.getBrightCardType() + " : " + handCard.getDarkCardType());
        Card topDiscardCard = deck.topCardFromDiscardPile();
        int index = nextPlayerIndex(players.indexOf(displayedPlayer), players.size(), direction);
        Player nextPlayer = players.get(index);
        boolean isPlaced = false;      //Returns status of placeCards - Milestone  3-----------------

        // if bright and color or card type match
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
            isPlaced = true;  //Returns status of placeCards - Milestone  3-----------------

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
            isPlaced = true;   //Returns status of placeCards - Milestone  3----------------

        }
        // light side wild cards
        else if (side && (handCard.getBrightColor() == CardColor.WILD)) {
            System.out.println("In placeCard handCard LIGHT");
            handCard.setBrightColor(selectedColor);
            if (handCard.getBrightCardType() == CardType.WILD_DRAW) {
                nextPlayer.addCardToHand(deck.drawCard());
                nextPlayer.addCardToHand(deck.drawCard());
            }

            displayedPlayer.removeCardFromHand(handCard);
            deck.addToDiscardPile(handCard);
            isPlaced = true;   //Returns status of placeCards - Milestone  3----------------

        }
        // dark side wild cards
        else if (!side && (handCard.getDarkColor() == CardColor.WILD)) {
            System.out.println("In placeCard handCard DARK");
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
            isPlaced = true;   //Returns status of placeCards - Milestone  3---------------
        }
        //updateStatus();
        for (GameUpdate view : views) {
            view.handlePlaceCardEvent(new PlaceCardEvent(this, displayedPlayer.getHand(), deck.topCardFromDiscardPile()));
        }
        return  isPlaced;
    }
}
