public class NextPlayerEvent extends GameEvent {
    private Player player;

    public NextPlayerEvent(Game model, Player player) {
        super(model);
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

}
