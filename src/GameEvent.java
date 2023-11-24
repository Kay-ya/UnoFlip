import java.util.EventObject;

public class GameEvent extends EventObject {

    public GameEvent(Game model){ // add params for getters
        super(model);
    }

}

