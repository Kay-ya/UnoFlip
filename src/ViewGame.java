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
        Game g = new Game();
        Deck d = new Deck();
        System.out.println("Enter Number of Players (you can only enter a maximum of upto 4 player): ");
        numberOfPlayers = scan.nextInt();
        if ((numberOfPlayers < 2) || (numberOfPlayers > 4)){
            System.out.println("Invalid Number of Players entered, try again");
        }
        else {
            for (int i = 0; i < numberOfPlayers; i++) {
                System.out.print("Player name: ");
                String playerName = scan.next();
                Player player = new Player(playerName);
                g.addPlayer(new Player(playerName));
            }
        }
        return g.getPlayers(); // Added , return type change from void to ArrayList <Player>
    }
}