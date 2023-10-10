import java.util.ArrayList;

public class Player {

    private String name;

    private ArrayList<Player> players;


    public Player(String name){
        this.name = name;
        players = new ArrayList<>();
    }

    //Player draws a card from the deck
    public Card drawCard(){
        return null;
    }

    public Card useCard(){
        return null;
    }

    public void addPlayer(Player pl){
        players.add(pl);
    }

    public void removePlayer(Player pl){
        players.remove(pl);
    }

}
