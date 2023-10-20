import java.lang.reflect.Array;
import java.util.ArrayList;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Game {
    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public ArrayList <Player> players;
    Player currentPlayer;
    Boolean direction; //false when lighter side of deck is played
    Deck deck;

    public Game(){
        players = new ArrayList<Player>();
        direction = false;
        currentPlayer = new Player(" ");
        deck = new Deck();
    }

    public void addPlayer(Player pl){
        players.add(pl);
    }

    public void removePlayer(Player pl){
        players.remove(pl);
    }


    /*private void startGame(){
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
        //if ((CardType.Flip == (?)) && (direction == true)){
            //will build on this once I think of something here

    }
    private Player checkWinner(){
        for(Player p: players ){
            if(p.countCardsInHand == 0){
                return p;
            }
        }
        return null;
    }*/

    public static void main(String args[]){
        Game play = new Game();
        Deck d = new Deck();
        ViewGame view = new ViewGame();

        // Updated
        play.players = view.playerInput();

        //Removed hardcoded players
        // Calling below methods on the players entered
        for (Player player : play.players) {
            for (int i = 0; i < 7; i++){
                player.addCardToHand(d.drawCard());
            }
            player.displayHand();
            player.getPlayerHandPoints();
        }
    }

}