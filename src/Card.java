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

    public CardType getBrightCardType() {
        return brightType;
    }

    public CardType getDarkCardType() {
        return darkType;
    }

    public Color getBrightColor() {
        return brightColor;
    }

    public Color getDarkColor() {
        return darkColor;
    }

    public void setBrightColor(Color color){ this.brightColor = color; }
    public void setDarkColor(Color color){ this.darkColor = color; }
}
