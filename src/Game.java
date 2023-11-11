import java.util.ArrayList;
import java.util.List;

public class Game {
    private ArrayList <Player> players; //Stores the player name in an ArrayList
    private Player currentPlayer;
    List<GameUpdate> updateView;
    private Boolean direction; // true (default) = clockwise,
    private Deck deck;
    boolean status;
    private Boolean side; // true = Bright side, false = Dark side

    public Game(){
        this.players = new ArrayList<>();
        this.direction = false;
        this.currentPlayer = null;
        this.deck = new Deck();
        this.side = true;
        this.status = false;
        updateView = new ArrayList<>();
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

    public void addGameView(GameUpdate view){
        updateView.add(view);
    }
    public void replaceDeckCard(Card handCard){
        this.getCurrentPlayer().removeCardFromHand(handCard);
        this.getDeck().addToDiscardPile(handCard);
    }

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