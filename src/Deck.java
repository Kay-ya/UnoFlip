import java.util.*;

public class Deck {

    private List<Card> cards;

    private List<Card> discardPile;

    private List<Card> drawPile; //Nikita

    /**
     * Populates the deck with both light and dark side of UNO cards
     */
    public Deck(){
        this.cards = new ArrayList<>();
        this.discardPile = new ArrayList<>();
        this.drawPile = new ArrayList<>(); //Nikita
        List<CardSideDetails> lightSideDetails = createSideDetails(new Color[]{Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW});
        List<CardSideDetails> darkSideDetails = createSideDetails(new Color[]{Color.PINK, Color.TEAL, Color.ORANGE, Color.PURPLE});

        for (int i = 0; i < lightSideDetails.size() && i < darkSideDetails.size(); i++) {
            CardSideDetails light = lightSideDetails.get(i);
            CardSideDetails dark = darkSideDetails.get(i);
            cards.add(new Card(light.type, light.color, dark.type, dark.color));
        }
    }

    /**
     * Creates the side for the card in the deck of the card type and the color associated with the type card
     */
    private static class CardSideDetails {
        CardType type;
        Color color;
        CardSideDetails(CardType type, Color color) {
            this.type = type;
            this.color = color;

        }
    }

    /**
     * Returns a List of type CardSideDetails
     * @param colors
     * @return List<CardSideDetails>
     */
    private List<CardSideDetails> createSideDetails(Color[] colors) {
        List<CardSideDetails> sideDetails = new ArrayList<>();
        // might be a cleaner way of initializing one side
        for (Color color: colors){
            for (int i = 0; i < 2; i++){
                for (CardType type: CardType.values()){
                    sideDetails.add(new CardSideDetails(type, color));
                }
            }
        }
        Collections.shuffle(sideDetails);
        return sideDetails;
    }

    /**
     * Draws a card from the deck, removes the card from the deck and returns the drawn card
     * @return Card
     */
    public Card drawCard(){
        Card card = this.cards.get(0);
        cards.remove(card);       
        return card;
    }


    /**
     * Adds the card to the discard pile after the user has
     * @param card
     */
    public void addToDiscardPile(Card card){
        discardPile.add(card);
    }

    /**
     * Returns the last card entered into the discard pile
     * @return Card
     */
    public Card topCardFromDiscardPile(){
        return discardPile.get(discardPile.size()-1);
    }

}
