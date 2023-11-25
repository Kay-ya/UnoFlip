public class NextPlayerEvent extends GameEvent {
    private Player player;

    /**
     * NextPlayerEvent constructor initializes the model and player parameters.
     * @param model
     * @param player
     */
    public NextPlayerEvent(Game model, Player player) {
        super(model);
        this.player = player;
    }

    /**
     * Returns the player
     * @return Player
     */
    public Player getPlayer() {
        return player;
    }

}
