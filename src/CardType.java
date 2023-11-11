/**
 * The CardType enum represents the possible types of Uno cards in the Uno Flip game.
 * Each enum value corresponds to a specific card type.
 */

public enum CardType {
    ONE,
    TWO,
    THREE,
    FOUR,
    FIVE,
    SIX,
    SEVEN,
    EIGHT,
    NINE,
    DRAW, // Light Side: Draw One, Dark Side: Draw Five
    REVERSE,
    SKIP, // Light Side: Skip One, Dark Side: Skip All
    FLIP,
    WILD,
    WILD_DRAW, // Light Side: Draw Two, Dark Side: Draw Color
}
