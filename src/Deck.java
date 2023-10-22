import java.util.*;
import java.util.stream.Collectors;

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
        List<CardType> cardTypes = Arrays.stream(CardType.values())
                .filter(card -> card != CardType.WILD && card != CardType.WILD_DRAW)
                .collect(Collectors.toList());
        CardType[] wildTypes = new CardType[]{CardType.WILD, CardType.WILD_DRAW};


        // all card except the wild cards
        for (Color color: colors){
            for (int i = 0; i < 2; i++){
                for (CardType type: cardTypes) {
                    sideDetails.add(new CardSideDetails(type, color));
                }
            }
        }

        for (int i = 0; i< 4; i++){
            for (CardType type: wildTypes) {
                sideDetails.add(new CardSideDetails(type, Color.WILD));
            }
        }
        Collections.shuffle(sideDetails);
        return sideDetails;
    }

    public void shuffle(){
        Collections.shuffle(cards);
    }

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

    //Nikita
    public void addToDiscardPile(Card card){
        discardPile.add(card);
    }

    public Card topCardFromDiscardPile(){
        return discardPile.get(discardPile.size()-1);
    }

    //Nikita
    public void removeFromDrawPile(Card card){
        drawPile.remove(card);
    }

}
