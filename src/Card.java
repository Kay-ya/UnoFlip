import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.Objects;

public class Card {


    private Colour colour;
    private Value value;
    private int hashCode;


    public Card(Colour colour, Value value) {
        this.colour = colour;
        this.value = value;
        this.hashCode = Objects.hash(colour, value);
    }

    public enum Colour {BLUE, GREEN, RED, YELLOW, PINK, TEAL, ORANGE, PURPLE, WILD;

        private static final Colour[] colours= Colour.values();

        public static Colour getColour(int i) {
            return Colour.colours[i];
        }
    }

    public enum Value  {ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, DRAW_ONE, SKIP, REVERSE, FLIP, DRAW_FIVE, SKIP_EVERYONE, DRAW_TWO, WILD, DRAW_COLOUR;
        private static final Value[] values = Value.values();

        public static Value getValue(int i) {
            return Value.values[i];
        }
    }

    public Colour getColour() {
        return this.colour;
    }

    public Value getValue() {
        return this.value;
    }


    public String toString(){ //converts the colour and values to string to form one side of a card
        return colour + "_" + value;
    }

    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Card))
            return false;
        Card c = (Card)o;
        return c.colour == colour && c.value == value;
    }

    @Override
    public int hashCode() {
        return this.hashCode;
    }
}
