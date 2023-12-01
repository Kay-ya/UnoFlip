public class NextPlayerEvent extends GameEvent {
    private Player player;
    private Status status;

    /**
     * NextPlayerEvent constructor initializes the model and player parameters.
     * @param model
     * @param player
     */
    public NextPlayerEvent(Game model, Player player, Status status) {
        super(model, status);
        this.player = player;
        this.status = status;
    }

    /**
     * Returns the player
     * @return Player
     */
    public Player getPlayer() {
        return player;
    }

}
