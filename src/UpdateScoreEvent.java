public class UpdateScoreEvent extends GameEvent{

    private Player player;
    private int score;

    private int round;

    public UpdateScoreEvent(Game model, int round, int score) {
        super(model);
        this.score = score;
        this.round = round;
    }

    public int getScore() {
        return score;
    }
    public int getRound() {
        return round;
    }


}
