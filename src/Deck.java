import java.util.*;

public class Deck {

    private List<Card> cards;
    private List<Card> discardPile;

    public Deck(){
        this.cards = new ArrayList<>();
        List<CardSideDetails> lightSideDetails = createSideDetails(new Color[]{Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW}, 2);
        List<CardSideDetails> darkSideDetails = createSideDetails(new Color[]{Color.PINK, Color.TEAL, Color.ORANGE, Color.PURPLE}, 2);

        for (int i = 0; i < lightSideDetails.size() && i < darkSideDetails.size(); i++) {
            CardSideDetails light = lightSideDetails.get(i);
            CardSideDetails dark = darkSideDetails.get(i);
            cards.add(new Card(light.type, light.color, dark.type, dark.color));
        }
    }

    private static class CardSideDetails {
        CardType type;
        Color color;
        CardSideDetails(CardType type, Color color) {
            this.type = type;
            this.color = color;

        }
    }
    private List<CardSideDetails> createSideDetails(Color[] colors, int sets) {
        List<CardSideDetails> sideDetails = new ArrayList<>();
        for (Color color: colors){
            for (int i = 0; i < sets; i++){
                for (CardType type: CardType.values()){
                    sideDetails.add(new CardSideDetails(type, color));
                }
            }
        }
        Collections.shuffle(sideDetails);
        return sideDetails;
    }
}
