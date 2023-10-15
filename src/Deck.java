import java.security.Key;
import java.util.*;

public class Deck {
    private int inDeckCards;
    String text;
    private List<Card> deckLight;
    private List<Card> deckDark;

    private String card;

    HashMap<Card, Card> cardDictionary;


    public Deck() {
        //card = new Card[224];
        deckLight = new ArrayList<>();
        deckDark = new ArrayList<>();
        cardDictionary = new HashMap<Card, Card>();
    }

    private void populateDeck() { //creating the cards light side and dark side
        //light side
        //populates numbers
        for(int i=0; i<4; i++) {
            for (int j = 0; j < 9; j++) {
                for (int k = 0; k < 2; k++) {
                    Card card = new Card(Card.Colour.getColour(i), Card.Value.getValue(j));
                    deckLight.add(card);
                }
            }
        }
        //populates special cards except wild cards
        for(int i=0; i<4; i++) {
            for (int j=9; j<13; j++){
                for(int k=0;k<2;k++){
                    Card card = new Card(Card.Colour.getColour(i),Card.Value.getValue(j));
                    deckLight.add(card);
                }
            }
        }
        //populates wild cards
        for(int j=15; j<17; j++){
            for(int k=0; k<4; k++){
                Card card = new Card(Card.Colour.WILD, Card.Value.getValue(j));
                deckLight.add(card);
            }
        }

        //dark side
        //populates numbers
        for(int i=4; i<8; i++){
            for(int j=0; j< 9; j++){
                for(int k=0; k<2;k++){
                    Card card = new Card(Card.Colour.getColour(i), Card.Value.getValue(j));
                    deckDark.add(card);
                }
            }
        }
        //populates special cards except wild cards
        for(int i=4; i<8; i++) {
            for (int j=11; j<15; j++){
                for(int k=0;k<2;k++){
                    Card card = new Card(Card.Colour.getColour(i),Card.Value.getValue(j));
                    deckDark.add(card);
                }
            }
        }
        //populates wild cards
        for(int j=16; j<18; j++){
            for(int k=0; k<4; k++){
                Card card = new Card(Card.Colour.WILD, Card.Value.getValue(j));
                deckDark.add(card);
            }
        }
    }

    public void shuffleDeck(){ //shuffling both the light deck and dark deck beforre adding it to the hashmap/dictinoary
        Collections.shuffle(deckDark);
        Collections.shuffle(deckLight);
    }
    public void createFlipUnoCard(){ //generating both sides dark and light of the card
        this.shuffleDeck(); //shuffle deck function from above

        for(int i=0; i<112; i++) {
            cardDictionary.put(deckLight.get(i), deckDark.get(i));
        }
        //System.out.println(cardDictionary);
        //cardDictionary.elements();
    }

    public Card returnCard(){ //returning null should return a card for hand
        Card card = new Card(Card.Colour.GREEN, Card.Value.FIVE);
        //Card c = cardDictionary.get("BLUE_SIX");                    // the key should be a Card object
        Card c = cardDictionary.get(card);
        return c;
    }

    public HashMap<Card,Card> returnDeck(){
        this.populateDeck();
        this.createFlipUnoCard();
        return cardDictionary;
    }

    public static void main(String[] args) {
        Deck d = new Deck();
        d.populateDeck();
        System.out.println(d.returnDeck());
        System.out.println(d.returnCard());
        System.out.println(d.cardDictionary.size());
    }
}
