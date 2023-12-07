public class LoadEvent extends GameEvent {
    private Player player;
    private Card topDiscard;
    private int score;
    private int round;

    /**
     * LoadEvent constructor initializes the model parameters.
     *
     * @param model
     */
    public LoadEvent(Game model, Player player, Status status, Card topDiscard, int score, int round) {
        super(model, status);
        this.player = player;
        this.status = status;
        this.topDiscard = topDiscard;
        this.score = score;
        this.round = round;
    }

    /**
     * Returns the player
     *
     * @return Player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Returns the top discard card
     *
     * @return Card
     */
    public Card getTopDiscard() {
        return topDiscard;
    }

    /**
     * Returns the score
     *
     * @return int
     */
    public int getScore() {
        return score;
    }

    /**
     * Returns the current round
     *
     * @return Round
     */
    public int getRound() {
        return round;
    }

}
