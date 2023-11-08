import java.util.EventObject;

public class GameEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    Card drawCard;
    Card removeHandCard;
    //GameController gameController;
    public GameEvent(Game game, Card drawCard, Card removeHandCard) {
        super(game);
        this.drawCard = drawCard;
        this.removeHandCard = removeHandCard;
    }

    public String getDrawCard(){
        return drawCard.toString();
    }

    public String getRemovedCard(){
        return removeHandCard.toString();
    }
}
