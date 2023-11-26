public class UpdateScoreEvent extends GameEvent{
    private int score;
    private int round;
    private String p;

    /**
     * UpdateScoreEvent constructor initializes the model, round and score parameters.
     * @param model
     * @param round
     */
    public UpdateScoreEvent(Game model, int round, Status status) {
        super(model, status);
        this.round = round;
    }

    public UpdateScoreEvent(Game model, int score, String p, Status status){
        super(model, status);
        this.score = score;
        this.p = p;
    }

    /**
     * Returns the score of the player
     * @return
     */
    public int getScore() {
        return score;
    }

    /**
     * Returns the round of the player
     * @return
     */
    public int getRound() {
        return round;
    }

    public String getPlayer(){
        return p;
    }
}
