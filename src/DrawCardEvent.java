import java.util.ArrayList;

public class DrawCardEvent extends GameEvent {
    private ArrayList<Card> hand;

    public DrawCardEvent(Game model, ArrayList<Card> hand) {
        super(model);
        this.hand = hand;
    }

    public ArrayList<Card> gethand() {
        return hand;
    }
}
