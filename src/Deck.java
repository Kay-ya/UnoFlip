import java.util.*;

public class Deck {

    private List<Card> cards;

    private List<Card> discardPile;

    private List<Card> drawPile; //Nikita

    /**
     * Populates the card with both light and dark side of UNO cards and adds them to the deck
     */
    public Deck(){
        this.cards = new ArrayList<>();
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
     *
     */
    private static class CardSideDetails {
        CardType type;
        Color color;
        CardSideDetails(CardType type, Color color) {
            this.type = type;
            this.color = color;

        }
    }
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

    public void shuffle(){
        Collections.shuffle(cards);
    }

    /**
     * Draws a card from the top of the deck pile and removes it from the deck ArrayList
     * @return Card
     */
    public Card drawCard(){
        Card card = this.cards.get(0);
        cards.remove(card);       
        return card;
    }

    // maybe game class handles discard pile instead    
    public void emptyDiscard(){
        this.cards.addAll(discardPile);
        this.discardPile = new ArrayList<>();
    }

    /**
     * Adds a card to the discard pile ArrayList
     * @param card
     */
    //Nikita
    public void addToDiscardPile(Card card){
        discardPile.add(card);
    }

    /**
     * returns the card that is added last to the discard pile
     * @return Card
     */
    public Card topCardFromDiscardFile(){
        return discardPile.get(cards.size()-1);
    }

    //Nikita
    public void removeFromDrawPile(Card card){
        drawPile.remove(card);
    }

}
