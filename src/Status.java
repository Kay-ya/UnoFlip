import java.io.Serializable;

public enum Status implements Serializable {
    NEW_TURN,
    INVALID,
    CARD_PLACED,
    CARD_DRAWN,
    WINNER
}
