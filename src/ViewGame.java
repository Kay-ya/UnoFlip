import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.ArrayList;
import java.util.Scanner;

public class ViewGame {

    Scanner scan;
    int numberOfPlayers;
    //Player pl;

    public ViewGame(){
        scan = new Scanner(System.in);
        this.numberOfPlayers = 0;
    }

    /**
     * Takes player input for number of players playing and name of each player
     * @return ArrayList containing entered players
     */
    public ArrayList <Player> playerInput() {
        ArrayList<Player> players = new ArrayList<>();
        System.out.println("Enter Number of Players (you can only enter a maximum of upto 4 player): ");
        numberOfPlayers = scan.nextInt();
        if ((numberOfPlayers < 2) || (numberOfPlayers > 4)){
            System.out.println("Invalid Number of Players entered, try again");
        }
        else {
            for (int i = 0; i < numberOfPlayers; i++) {
                System.out.print("Player name: ");
                String playerName = scan.next();
                players.add(new Player(playerName));
            }
        }
        return players; // Added , return type change from void to ArrayList <Player>
    }

    /**
     * Returns the card the user has chosen from the hand or returns an invalid card if the card
     * does not match the color or number present in hand
     * @param player
     * @param topCard
     * @param isBright
     * @return Card
     */
    public Card playerChooseOption(Player player, Card topCard, Boolean isBright) {
        System.out.println("It is " + player.getName() + "'s turn. The card you would like to play: ");
        int cardNum = scan.nextInt();
        while(cardNum < 0 || cardNum > player.hand.size()){
            System.out.println("Enter card index to play or 0 to draw a card:");
            cardNum = scan.nextInt();
        }

        if (cardNum == 0){
            return null;
        }
        Card chosenCard = player.hand.get(cardNum-1);


        if (isBright){
            if (chosenCard.getBrightCardType() == CardType.WILD || chosenCard.getBrightCardType() == CardType.WILD_DRAW){
                return chosenCard;
            }
            while (chosenCard.getBrightColor() != topCard.getBrightColor() && chosenCard.getBrightCardType() != topCard.getBrightCardType()){
                System.out.println("Invalid card chosen.");
                chosenCard = playerChooseOption(player, topCard, isBright);
            }
        }
        else {
            if (chosenCard.getDarkCardType() == CardType.WILD || chosenCard.getDarkCardType() == CardType.WILD_DRAW){
                return chosenCard;
            }
            while (chosenCard.getDarkColor() != topCard.getDarkColor() && chosenCard.getDarkCardType() != topCard.getDarkCardType()){
                System.out.println("Invalid card chosen.");
                chosenCard = playerChooseOption(player, topCard, isBright);
            }
        }

        return chosenCard;
    }

    public Color playerColorInput(Boolean isBright){
        if (isBright){
            System.out.println("You have played a wild card. Please choose a color: Blue, Green, Red, Yellow");
        }
        else{
            System.out.println("You have played a wild card. Please choose a color: Orange, Pink, Purple, Teal");
        }
        String color = scan.next();
        if (isBright){
            switch (color){
                case "Blue":
                    return Color.BLUE;
                case "Green":
                    return Color.GREEN;
                case "Red":
                    return Color.RED;
                case "Yellow":
                    return Color.YELLOW;
                default:
                    System.out.println("Invalid color chosen");
                    playerColorInput(isBright);
            }
        }
        else {
            switch (color){
                case "Orange":
                    return Color.ORANGE;
                case "Pink":
                    return Color.PINK;
                case "Purple":
                    return Color.PURPLE;
                case "Teal":
                    return Color.TEAL;
                default:
                    System.out.println("Invalid color chosen");
                    playerColorInput(isBright);
            }
        }
        return null;
    }
}