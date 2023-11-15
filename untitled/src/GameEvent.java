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


    Player player;
    //GameController gameController;
    public GameEvent(Game game, Card drawCard, Card removeHandCard, Boolean status) {
        super(game);
        this.drawCard = drawCard;
        this.removeHandCard = removeHandCard;
        this.status = status;
    }

    public GameEvent(Game game, Player player){
        super(game);
        this.player = player;
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

    public Player getPlayer() {
        return player;
    }
}