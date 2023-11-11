
/**
 *The GameUpdate interface sets the rules for classes wanting game updates,like events during play.
 * Any class using this interface needs to have a handleUnoUpdate method, dealing with game events
 */


public interface GameUpdate {


    /**
     * Handles updates from the Uno game.
     * @param e The GameEvent instance which contains information about the Uno Game update.
     */
    public void handleUnoUpdate(GameEvent e);
}
