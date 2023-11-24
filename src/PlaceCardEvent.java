import java.util.ArrayList;

public class PlaceCardEvent extends GameEvent {
    private ArrayList<Card> hand;
    private Card topDiscard;

    public PlaceCardEvent(Game model, ArrayList<Card> hand, Card topDiscard) {
        super(model);
        this.hand = hand;
        this.topDiscard = topDiscard;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public Card getTopDiscard() {
        return topDiscard;
    }
}
