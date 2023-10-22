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
     * @return An instance of the Card class
     */
    public Card drawCard(){
        return deck.drawCard();
    }

    /**
     * Play the card if any of the cards from the hand matches the card in the pile (in terms of same color or number)
     * or play an action card (wild or wild four) which can be played anytime
     *
     * @return
     */
    public Card playCard(Card chosenCard) {
        if (chosenCard.getBrightCardType() == CardType.WILD || chosenCard.getBrightCardType() == CardType.WILD_DRAW){
            return chosenCard;
        }



        return chosenCard;
    }

    /**
     *  Calculates the points of each player - calculated after each player has played their card
     * @return Returns an integer value representing total points of the player's hand
     */
    public int calculateTotalPointsForPlayerHand(){

        for (Card card : hand) {
            switch(card.getBrightCardType()){

                case ONE:
                    handPoints+=1;
                    break;
                case TWO:
                    handPoints+=2;
                    break;
                case THREE:
                    handPoints+=3;
                    break;
                case FOUR:
                    handPoints+=4;
                    break;
                case FIVE:
                    handPoints+=5;
                    break;
                case SIX:
                    handPoints+=6;
                    break;
                case SEVEN:
                    handPoints+=7;
                    break;
                case EIGHT:
                    handPoints+=8;
                    break;
                case NINE:
                    handPoints+=9;
                    break;
                case DRAW:
                    handPoints+=10;                 //light side
                    break;
                case REVERSE:
                    handPoints+=20;
                    break;
                case SKIP:
                    handPoints+=20;
                    break;
                case FLIP:
                    handPoints+=20;
                    break;
                case WILD:
                    handPoints+=40;
                    break;
                case WILD_DRAW:
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
     * @param card An instance of the Card class
     */
    public void addCardToHand(Card card){
        hand.add(card);
    }

    /**
     *  Removes the card from the hand and adds to discard pile
     *  @param card An instance of the Card class
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
            System.out.println(card.getBrightCardType() + "_" + card.getBrightColor()); //+ " " + card.getDarkCardType() + "_" + card.getDarkColor() + " ");
        }
    }

    public void getPlayerHandPoints(){
        int playerPoints = calculateTotalPointsForPlayerHand();
        System.out.println("Total points for the player's hand: " + playerPoints);
    }

    /**
     * Returns the player's score
     * @return int Player's score
     */
    public int getPlayerScore() {
        return playerScore;
    }

    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }

    /**
     * Returns the player's name
     * @return String Player's name
     */
    public String getName() {
        return name;
    }
}
