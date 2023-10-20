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

    public void printCard(Card card, Boolean isBright){
        if (isBright){
            System.out.println(card.getBrightCardType().toString()+"_"+card.getBrightColor().toString());
        }
        else {
            System.out.println(card.getDarkCardType().toString()+"_"+card.getDarkColor().toString());
        }
    }
}