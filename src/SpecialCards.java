public class SpecialCards {
    Deck deck;
    Card card;
    public SpecialCards(){
        deck = new Deck();
    }
    public void applyActionBrightSide(){
        Card card = deck.topCardFromDiscardPile();
        switch (card.getBrightCardType()){
            case REVERSE:
                break;
            case SKIP:
                break;
            case WILD:
                break;
        }
    }

    public void applyActionDarkSide(){
        Card card = deck.topCardFromDiscardPile();
        switch (card.getDarkCardType()){
        }
    }
}
