import java.util.EventObject;

public class GameEvent extends EventObject {

    public Status status;
    public GameEvent(Game model, Status status){ // add params for getters
        super(model);
        this.status = status;
    }
    public Status getStatus() {
        return status;
    }
}

