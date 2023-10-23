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

    /**
     * Set the bright side color for the current instance of the card
     * @param color the color that the card is going to be set to
     */
    public void setBrightColor(Color color){ this.brightColor = color; }

    /**
     * Set the dark side color for the current instance of the card
     * @param color the color that the card is going to be set to
     */
    public void setDarkColor(Color color){ this.darkColor = color; }

    /**
     * Prints the card of the side the user is player (dark or light) and returns the
     * type and color of the card on the bright or dark side
     * @param isBright for which side of the card that is currently being used
     */
    public void printCard(Boolean isBright){
        if (isBright){
            System.out.println(this.getBrightCardType().toString()+" "+this.getBrightColor().toString());
        }
        else {
            System.out.println(this.getDarkCardType().toString()+" "+this.getDarkColor().toString());
        }
    }
}
