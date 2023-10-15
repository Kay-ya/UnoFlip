import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;

public class Player {

    private String name;
    public ArrayList<Card> hand;
    private int score;
    Deck card = new Deck();

    public Player(String name){
        this.name = name;
        this.score =0;
        hand = new ArrayList<>();
        //players = new ArrayList<>();
    }

    public String getName(){
        return name;
    }

    public void addCardToHand(Card card){
        //for(int i=0; i<7;i++){
            hand.add(card);
        //}
    }

    public void removeCardFromHand(Card card){
        hand.remove(card);
    }

    public void displayHand() {
        System.out.println(name + "'s hand:");
        for (Card card : hand) {
            System.out.print(card.getBrightCardType() + "_" + card.getBrightColor() + " " + card.getDarkCardType() + "_" + card.getDarkColor() + " ");
        }
    }

    public int getScore(){
        return score;
    }

    public int increaseScore(int point){
        return score += point;
    }

    //Player draws a card from the deck


    public Card useCard(){
        return null;
    }


    public static void main(String[] args) {
        //p.addPlayer("p");
    }
}