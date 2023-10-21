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
    Boolean isBright;

    public Game(){
        this.players = new ArrayList<>();
        this.direction = false;
        this.currentPlayer = new Player(" ");
        this.deck = new Deck();
        this.isBright = true;
    }

    public void addPlayer(Player pl){
        this.players.add(pl);
    }

    public void removePlayer(Player pl){
        this.players.remove(pl);
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

    public static void main(String[] args){
        Game game = new Game();
        ViewGame view = new ViewGame();
        int playerIndex = 0;
        game.players = view.playerInput();

        // deal cards to players
        for (Player player : game.players) {
            for (int i = 0; i < 7; i++){
                player.addCardToHand(game.deck.drawCard());
            }
        }
        // initial card to start game
        game.deck.addToDiscardPile(game.deck.drawCard());

        while(true){
            game.currentPlayer = game.players.get(playerIndex);
            System.out.println("The current card is:");
            view.printCard(game.deck.topCardFromDiscardPile(), game.isBright);
            StringBuilder s = new StringBuilder();
            int i = 1;
            for (Card card : game.currentPlayer.hand){
                s.append(i).append(". ").append(card.getBrightCardType()).append("_").append(card.getBrightColor()).append(" ").append(card.getDarkCardType()).append("_").append(card.getDarkColor()).append(", ");
                i++;
            }
            System.out.println(s);
            Card chosenCard = view.playerChooseCard(game.currentPlayer);
            Card topDiscardCard = game.deck.topCardFromDiscardPile();
            if (game.isBright) {
                while (chosenCard.getBrightColor() != topDiscardCard.getBrightColor() && chosenCard.getBrightCardType() != topDiscardCard.getBrightCardType()){
                    System.out.println("Invalid card chosen.");
                    chosenCard = view.playerChooseCard(game.currentPlayer);
                }
            }
            else {
                while (chosenCard.getDarkColor() != topDiscardCard.getDarkColor() && chosenCard.getDarkCardType() != topDiscardCard.getDarkCardType()){
                    System.out.println("Invalid card chosen.");
                    chosenCard = view.playerChooseCard(game.currentPlayer);
                }
            }
            game.currentPlayer.removeCardFromHand(chosenCard);
            game.deck.addToDiscardPile(chosenCard);

            //special card effect happen here

            if (game.direction) {
                //reverse direction
            }
            else{
                playerIndex = (playerIndex + 1) % game.players.size();
            }
        }

    }

}