public class Card {
    private final CardType brightType;
    private Color brightColor;
    private final CardType darkType;
    private Color darkColor;

    public Card(CardType bCT, Color bC, CardType dCT, Color dC) {
        this.brightType = bCT;
        this.brightColor = bC;
        this.darkType = dCT;
        this.darkColor = dC;

    }

    /**
     * Returns the type of the card being played on the light side of the deck
     * @return CardType
     */
    public CardType getBrightCardType() {
        return brightType;
    }

    /**
     * Returns the type of card being played on the dark side of the deck
     * @return CardType
     */
    public CardType getDarkCardType() {
        return darkType;
    }

    /**
     * Returns the color on the light side of the deck
     * @return Color
     */
    public Color getBrightColor() {
        return brightColor;
    }

    /**
     * Returns the color on the dark side of the deck
     * @return Color
     */
    public Color getDarkColor() {
        return darkColor;
    }

    public void setBrightColor(Color color){ this.brightColor = color; }
    public void setDarkColor(Color color){ this.darkColor = color; }
}
