import java.lang.reflect.Array;
import java.util.ArrayList;

public class Game {
    ArrayList <Player> players;
    Player currentPlayer;
    Boolean direction; //false when lighter side of deck is played
    Deck deck;

    public Game(){
        players = new ArrayList<Player>();
        direction = false;
        currentPlayer = new CurrentPlayer();
        deck = new Deck();
    }

    private void startGame(){
        System.out.println("The game has started");
    }
    private Player nextTurn() {
        for (int i = 0; i < 4; i++) { //for now there are only 4 turns
            // currentPlayer = players.get(0);
            if(currentPlayer == players.get(0)) {
                currentPlayer = players.get(1);
                return currentPlayer;
            } else {
                currentPlayer = players.get(0);
                return currentPlayer;
            }
        }
    }
    private void playCard(){
        if ((CardType.Flip == (?)) && (direction == true)){
            //will build on this once I think of something here
        }
    }
    private Player checkWinner(){
        for(Player p: players ){
            if(p.countCardsInHand == 0){
                return p;
            }
        }
        return null;
    }

    public static void main(String args[]){
        Game play = new Game();
        play.startGame();
    }
    //hello
}
