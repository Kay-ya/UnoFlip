import java.util.ArrayList;
import java.util.List;

public class Game {
    private ArrayList <Player> players; //Stores the player name in an ArrayList
    private Player currentPlayer;
    List<GameUpdate> updateView;
    private Boolean direction; // true (default) = clockwise,
    private Deck deck;
    private Boolean side; // true = Bright side, false = Dark side

    public Game(){
        this.players = new ArrayList<>();
        this.direction = false;
        this.currentPlayer = null;
        this.deck = new Deck();
        this.side = true;
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
    public static int nextPlayerIndex(int curIndex, int numPlayers, Boolean direction){
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

    public void placeCards(Card handCard, Card topDiscardCard){
        if (this.getSide() && (handCard.getBrightColor() == topDiscardCard.getBrightColor() || handCard.getBrightCardType() == topDiscardCard.getBrightCardType())) {
            this.getCurrentPlayer().removeCardFromHand(handCard);
            this.getDeck().addToDiscardPile(handCard);
            //removeCard = handCard;
        } else if (!this.getSide() && (handCard.getDarkColor() == topDiscardCard.getDarkColor() || handCard.getDarkCardType() == topDiscardCard.getDarkCardType())) {
            this.getCurrentPlayer().removeCardFromHand(handCard);
            this.getDeck().addToDiscardPile(handCard);
            //removeCard = handCard;
        }
        for (GameUpdate view: updateView){
            view.handleUnoUpdate(new GameEvent(this, topDiscardCard, handCard));
        }
    }
}