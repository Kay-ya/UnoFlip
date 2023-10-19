import java.util.ArrayList;

public class Player {

    private Deck deck = new Deck();
    public ArrayList<Card> hand;

    private String name;
    private int handPoints;

    private int playerScore;
    public Player(String name){
        this.name = name;
        hand = new ArrayList<>();
        calculateTotalPointsForPlayerHand();
    }
    /**
     *  Draw a card from the deck if the none of the cards in their hand match the card in the pile
     */
    public Card drawCard(){
        return deck.drawCard();
    }

    /**
     *  Play the card if any of the cards from the hand matches the card in the pile (in terms of same color or number)
     *  or play an action card (wild or wild four) which can be played anytime
     */
    public void playCard(){

    }


    /**
     *  Calculates the points of each player - calculated after each player has played their card?
     */
    public int calculateTotalPointsForPlayerHand(){

        for (Card card : hand) {
            System.out.println("In calculateTotalPointsForPlayerHand 2");
            switch(card.getBrightCardType()){
                case ONE:
                    System.out.println("ONE");
                    handPoints+=1;
                    break;
                case TWO:
                    System.out.println("TWO");
                    handPoints+=2;
                    break;
                case THREE:
                    System.out.println("THREE");
                    handPoints+=3;
                    break;
                case FOUR:
                    System.out.println("FOUR");
                    handPoints+=4;
                    break;
                case FIVE:
                    System.out.println("FIVE");
                    handPoints+=5;
                    break;
                case SIX:
                    System.out.println("SIX");
                    handPoints+=6;
                    break;
                case SEVEN:
                    System.out.println("SEVEN");
                    handPoints+=7;
                    break;
                case EIGHT:
                    System.out.println("EIGHT");
                    handPoints+=8;
                    break;
                case NINE:
                    System.out.println("NINE");
                    handPoints+=9;
                    break;
                case DRAW:
                    System.out.println("DRAW");
                    handPoints+=10;                 //light side
                    break;
                case REVERSE:
                    System.out.println("REVERSE");
                    handPoints+=20;
                    break;
                case SKIP:
                    System.out.println("SKIP");
                    handPoints+=20;
                    break;
                case FLIP:
                    System.out.println("FLIP");
                    handPoints+=20;
                    break;
                case WILD:
                    System.out.println("WILD");
                    handPoints+=40;
                    break;
                case WILD_DRAW:
                    System.out.println("WILD_DRAW");
                    handPoints+=50;                     //light side
                    break;
                default:
                    System.out.println("ERROR");
                    break;

            }
        }
        return handPoints;
    }

    /**
     *  Adds the card drawn from draw pile to hand and removes it from draw pile
     */
    public void addCardToHand(Card card){
        hand.add(card);
        //deck.removeFromDrawPile(card);   
    }

    /**
     *  Removes the card from the hand and adds to discard pile
     */
    public void removeCardFromHand(Card card){
        hand.remove(card);
        deck.addToDiscardPile(card);
    }

    /**
     *  Displays the cards in player's hand
     */
    public void displayHand() {
        System.out.println(this.name + "'s hand:");
        for (Card card : hand) {
            System.out.print(card.getBrightCardType() + "_" + card.getBrightColor() + " " + card.getDarkCardType() + "_" + card.getDarkColor() + " ");
        }
        int playerPoints = calculateTotalPointsForPlayerHand();
        System.out.println("Total points for the player's hand:" + playerPoints);
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }
}
