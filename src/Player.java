import java.util.ArrayList;

public class Player {

    public ArrayList<Card> hand;

    private String name;

    private int playerScore;
    public Player(String name){
        this.name = name;
        hand = new ArrayList<>();
    }

    /**
     *  Calculates the points of each player - calculated after each player has played their card
     * @param isBright for light or dark side of card
     * @return Returns an integer value representing total points of the player's hand
     */
    public int calculateTotalPointsForPlayerHand(Boolean isBright){
        int handPoints = 0;
        for (Card card : hand) {
            if (isBright){
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
                        handPoints+=10;           // draw one = 10 pts
                        break;
                    case REVERSE:
                        handPoints+=20;
                        break;
                    case SKIP:
                        handPoints+=20;           // normal skip = 20 pts
                        break;
                    case FLIP:
                        handPoints+=20;
                        break;
                    case WILD:
                        handPoints+=40;
                        break;
                    case WILD_DRAW:
                        handPoints+=50;            // wild draw two = 50 pts
                        break;
                    default:
                        System.out.println("ERROR");
                        break;
                }
            }
            else {
                switch(card.getDarkCardType()){
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
                        handPoints+=20;     // draw five = 20 pts
                        break;
                    case REVERSE:
                        handPoints+=20;
                        break;
                    case SKIP:
                        handPoints+=30;     // skip all  = 30 pts
                        break;
                    case FLIP:
                        handPoints+=20;
                        break;
                    case WILD:
                        handPoints+=40;
                        break;
                    case WILD_DRAW:
                        handPoints+=60;     // wild draw color = 60 pts
                        break;
                    default:
                        System.out.println("ERROR");
                        break;
                }
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
    }

    /**
     *  Displays the cards in player's hand
     *  @param isBright bright or dark side of the card
     */
    public void displayHand(Boolean isBright) {
        int i = 1;
        System.out.println(this.name + "'s hand:");
        for (Card card : hand) {
            if (isBright){
                System.out.println(i + ". " + card.getBrightCardType() + " " + card.getBrightColor());
            }
            else{
                System.out.println( i + ". " + card.getDarkCardType() + " " + card.getDarkColor());
            }
            i++;
        }
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

    /**
     * Returns the player's hand
     * @return String Player's hand
     */
    public ArrayList<Card> getHand() {
        return hand;
    }
}
