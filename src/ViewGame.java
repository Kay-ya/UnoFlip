import java.util.ArrayList;
import java.util.Scanner;

public class ViewGame {

    Scanner scan;
    int numberOfPlayers;
    ArrayList <Player> p = new ArrayList<>();
    Player pl;

    public ViewGame(){
        scan = new Scanner(System.in);
        this.numberOfPlayers = 0;
    }

    public void display() {
        System.out.println("Enter Number of Players");
        numberOfPlayers = scan.nextInt();
        System.out.println("Enter player name");
        Game g = new Game();
        for (int i = 0; i < numberOfPlayers; i++) {
            //while (scan.hasNextLine()) {
                String player = scan.nextLine();
                pl = new Player(player);
            //}
        }
    }
        //System.out.println();

    }

