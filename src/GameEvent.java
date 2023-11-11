import java.util.EventObject;


/**
 * This is a class, extending the EventObject class, representing an event in the UnoFlip! card game.
 * It notifies about  object events such as drawing a card, removing a card from a player's hand  or changing the status
 * of the game.
 */
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

    /**
     * Creates a GameEvent object.
     *
     * @param game           The game instance.
     * @param drawCard       The card drawn during the game event.
     * @param removeHandCard The card removed from the player's hand during the game event.
     * @param status         The status of the game event.
     */
    public GameEvent(Game game, Card drawCard, Card removeHandCard, Boolean status) {
        super(game);
        this.drawCard = drawCard;
        this.removeHandCard = removeHandCard;
        this.status = status;
    }


    /**
     * Gets a string representation of the card drawn during the game event.
     *
     * @return A string representation of the drawn card.
     */

    public String getDrawCard(){
        return drawCard.toString();
    }


    /**
     * Gets a string representation of the card removed from the player's hand during the game event.
     *
     * @return A string representation of the removed card.
     */

    public String getRemovedCard(){
        return removeHandCard.toString();
    }

    /**
     * Gets the status of the game event.
     *
     * @return The status of the game event, indicating a specific condition or action.
     */

    public boolean getStatus(){
        return this.status;
    }
}
