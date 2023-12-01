import java.util.ArrayList;

public class DrawCardEvent extends GameEvent {
    private ArrayList<Card> hand;

    /**
     * DrawCardEvent constructor initializes the model and hand parameters.
     * @param model
     * @param hand
     */
    public DrawCardEvent(Game model, ArrayList<Card> hand, Status status) {
        super(model, status);
        this.hand = hand;
    }

    /**
     * Returns an ArrayList of hands of current player.
     * @return Arraylist
     */
    public ArrayList<Card> getHand() {
        return hand;
    }
}
