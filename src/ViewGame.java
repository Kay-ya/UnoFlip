import java.util.ArrayList;
import java.util.Scanner;

public class ViewGame {

    Scanner scan;
    int numberOfPlayers;
    //Player pl;

    public ViewGame() {
        scan = new Scanner(System.in);
        this.numberOfPlayers = 0;
    }

    /**
     * Takes player input for number of players playing and name of each player
     *
     * @return ArrayList containing entered players
     */
    public ArrayList<Player> playerInput() {
        ArrayList<Player> players = new ArrayList<>();
        System.out.println("Enter Number of Players (2-4): ");
        numberOfPlayers = scan.nextInt();
        if ((numberOfPlayers < 2) || (numberOfPlayers > 4)) {
            System.out.println("Invalid Number of Players entered, try again");
        } else {
            for (int i = 0; i < numberOfPlayers; i++) {
                System.out.printf("Player %d name: ", i+1);
                String playerName = scan.next();
                players.add(new Player(playerName));
            }
        }
        return players; // Added , return type change from void to ArrayList <Player>
    }

    /**
     * Prints the card of the side the user is player (dark or light) and returns the
     * type and color of the card on the bright or dark side
     * @param card
     * @param isBright
     */
    public void printCard(Card card, Boolean isBright) {
        if (isBright) {
            System.out.println(card.getBrightCardType().toString() + "_" + card.getBrightColor().toString());
        } else {
            System.out.println(card.getDarkCardType().toString() + "_" + card.getDarkColor().toString());
        }
    }
    public Color getPlayerColorChoice() {
        System.out.println("Choose a color (RED, YELLOW, GREEN, BLUE): ");
        String colorChoice = scan.next();
        if (colorChoice.equals("RED") || colorChoice.equals("YELLOW") || colorChoice.equals("GREEN")) {
            return Color.valueOf(colorChoice);

        } else {
             return getPlayerColorChoice(); // Recursively get a valid color choice
        }
    }

    //}


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
        while (cardNum < 0 || cardNum > player.hand.size()) {
            System.out.println("Enter card index to play or 0 to draw a card:");
            cardNum = scan.nextInt();
        }
        if (cardNum == 0) {
            return null;
        }
        Card chosenCard = player.hand.get(cardNum - 1);

        if (isBright) {
            while (chosenCard.getBrightColor() != topCard.getBrightColor() && chosenCard.getBrightCardType() != topCard.getBrightCardType()) {
                System.out.println("Invalid card chosen.");
                chosenCard = playerChooseOption(player, topCard, true);
            }
        } else {
            while (chosenCard.getDarkColor() != topCard.getDarkColor() && chosenCard.getDarkCardType() != topCard.getDarkCardType()) {
                System.out.println("Invalid card chosen.");
                chosenCard = playerChooseOption(player, topCard, false);
            }
        }


        return chosenCard;
    }

    }
