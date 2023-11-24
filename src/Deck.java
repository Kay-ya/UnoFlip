import java.util.*;
import java.util.stream.Collectors;

public class Deck {

    private List<Card> cards;

    private List<Card> discardPile;

    /**
     * Populates the deck with both light and dark side of UNO cards
     */
    public Deck(){
        this.cards = new ArrayList<>();
        this.discardPile = new ArrayList<>();
        List<CardSideDetails> lightSideDetails = createSideDetails(new CardColor[]{CardColor.BLUE, CardColor.GREEN, CardColor.RED, CardColor.YELLOW});
        List<CardSideDetails> darkSideDetails = createSideDetails(new CardColor[]{CardColor.PINK, CardColor.TEAL, CardColor.ORANGE, CardColor.PURPLE});

        for (int i = 0; i < lightSideDetails.size() && i < darkSideDetails.size(); i++) {
            CardSideDetails light = lightSideDetails.get(i);
            CardSideDetails dark = darkSideDetails.get(i);
            cards.add(new Card(light.type, light.color, dark.type, dark.color));
        }
        // starting card
        addToDiscardPile(drawCard());
    }

    /**
     * Creates the side for the card in the deck of the card type and the color associated with the type card
     */
    private static class CardSideDetails {
        CardType type;
        CardColor color;
        CardSideDetails(CardType type, CardColor color) {
            this.type = type;
            this.color = color;

        }
    }

    /**
     * Returns a List of type CardSideDetails
     * @param colors possible colors for bright or dark side that is being generated
     * @return List<CardSideDetails>
     */
    private List<CardSideDetails> createSideDetails(CardColor[] colors) {
        List<CardSideDetails> sideDetails = new ArrayList<>();
        List<CardType> cardTypes = Arrays.stream(CardType.values())
                .filter(card -> card != CardType.WILD && card != CardType.WILD_DRAW)
                .collect(Collectors.toList());
        CardType[] wildTypes = new CardType[]{CardType.WILD, CardType.WILD_DRAW};


        // all card except the wild cards
        for (CardColor color: colors){
            for (int i = 0; i < 2; i++){
                for (CardType type: cardTypes) {
                    sideDetails.add(new CardSideDetails(type, color));
                }
            }
        }

        for (int i = 0; i< 4; i++){
            for (CardType type: wildTypes) {
                sideDetails.add(new CardSideDetails(type, CardColor.WILD));
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
     * @param card card to add to discard pile
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
