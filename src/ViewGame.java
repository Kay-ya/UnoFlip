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


        public void playerInput() {
            Game g = new Game();
            System.out.println("Enter Number of Players (you can only enter a maximum of upto 4 player): ");
            numberOfPlayers = scan.nextInt();
            if ((numberOfPlayers < 2) || (numberOfPlayers > 4)){
                System.out.println("Invalid Number of Players entered, try again");
            }
            else {
                for (int i = 0; i < numberOfPlayers; i++) {
                    System.out.print("Player name: ");
                    String player = scan.next();
                    g.addPlayer(new Player(player));
                }
            }
        }

    }

