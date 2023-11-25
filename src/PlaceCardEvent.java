import java.util.ArrayList;

public class PlaceCardEvent extends GameEvent {
    private ArrayList<Card> hand;
    private Card topDiscard;

    /**
     * PlayerCardEvent constructor initializes the model, hand and topDiscard card parameters.
     * @param model
     * @param hand
     * @param topDiscard
     */
    public PlaceCardEvent(Game model, ArrayList<Card> hand, Card topDiscard) {
        super(model);
        this.hand = hand;
        this.topDiscard = topDiscard;
    }

    /**
     * Returns the arraylist hand of the current player
     * @return ArrayList
     */
    public ArrayList<Card> getHand() {
        return hand;
    }

    /**
     * Returns the top card of the discard pile
     * @return Card
     */
    public Card getTopDiscard() {
        return topDiscard;
    }
}
