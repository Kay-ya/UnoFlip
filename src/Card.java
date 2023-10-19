public class Card {
    private final CardType brightType;
    private final Color brightColor;
    private final CardType darkType;
    private final Color darkColor;

    public Card(CardType bCT, Color bC, CardType dCT, Color dC) {
        this.brightType = bCT;
        this.brightColor = bC;
        this.darkType = dCT;
        this.darkColor = dC;

    }
