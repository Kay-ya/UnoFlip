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
    Boolean status;
    //GameController gameController;
    public GameEvent(Game game, Card drawCard, Card removeHandCard, Boolean status) {
        super(game);
        this.drawCard = drawCard;
        this.removeHandCard = removeHandCard;
        this.status = status;
    }

    public String getDrawCard(){
        return drawCard.toString();
    }

    public String getRemovedCard(){
        return removeHandCard.toString();
    }

    public boolean getStatus(){
        return this.status;
    }
}
