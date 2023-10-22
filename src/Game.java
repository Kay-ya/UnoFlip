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

    /**
     * Adds a player to the ArrayList of players
     * @param pl
     */
    public void addPlayer(Player pl){
        this.players.add(pl);
    }

    /**
     * Removes a player from the Arraylist of Players
     * @param pl
     */
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
    private static int nextPlayerIndex(int curIndex, int numPlayers, Boolean direction){
        if (direction) {
            curIndex -= 1;
            if (curIndex == -1){
                curIndex = numPlayers-1;
            }
        }
        else{
            curIndex = (curIndex + 1) % numPlayers;
        }
        return curIndex;
    }
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

        // starting player
        game.currentPlayer = game.players.get(playerIndex);

        // initial card to start game
        game.deck.addToDiscardPile(game.deck.drawCard());

        // game loop
        while(!game.currentPlayer.hand.isEmpty()){
            game.currentPlayer = game.players.get(playerIndex);

            System.out.println("The current card is:");
            view.printCard(game.deck.topCardFromDiscardPile(), game.isBright);

            StringBuilder strCards = new StringBuilder();
            int i = 1;
            for (Card card : game.currentPlayer.hand){
                strCards.append(i).append(". ").append(card.getBrightCardType()).append("_").append(card.getBrightColor()).append(" ").append(card.getDarkCardType()).append("_").append(card.getDarkColor()).append(", ");
                i++;
            }
            System.out.println(strCards);

            Card chosenCard = view.playerChooseOption(game.currentPlayer, game.deck.topCardFromDiscardPile(), game.isBright);

            if ( chosenCard != null && game.isBright ){
                switch(chosenCard.getBrightCardType()){
                    case DRAW:
                        playerIndex = nextPlayerIndex(playerIndex, game.players.size(), game.direction);
                        game.players.get(playerIndex).addCardToHand(game.deck.drawCard());
                        break;
                    case REVERSE:
                        game.direction = !game.direction;
                        break;
                    case SKIP:
                        playerIndex = nextPlayerIndex(playerIndex, game.players.size(), game.direction);
                        break;
                    case FLIP:
                        game.isBright = !game.isBright;
                        break;
                    case WILD:
                        Color chosenColor = view.playerColorInput(game.isBright);
                        if (game.isBright){
                            chosenCard.setBrightColor(chosenColor);
                        }
                        else{
                            chosenCard.setDarkColor(chosenColor);
                        }
                        break;
                }
                game.currentPlayer.removeCardFromHand(chosenCard);
                game.deck.addToDiscardPile(chosenCard);
            }
            else if (chosenCard != null){
                switch(chosenCard.getDarkCardType()){
                    case DRAW:
                        playerIndex = nextPlayerIndex(playerIndex, game.players.size(), game.direction);
                        for (int j = 0; j < 5; j++){
                            game.players.get(playerIndex).addCardToHand(game.deck.drawCard());
                        }
                        break;
                    case REVERSE:
                        game.direction = !game.direction;
                        break;
                    case SKIP:
                        playerIndex = nextPlayerIndex(playerIndex, game.players.size(), game.direction);
                        break;
                    case FLIP:
                        game.isBright = !game.isBright;
                        break;
                    case WILD:
                        Color chosenColor = view.playerColorInput(game.isBright);
                        if (game.isBright){
                            chosenCard.setBrightColor(chosenColor);
                        }
                        else{
                            chosenCard.setDarkColor(chosenColor);
                        }
                        break;
                }
                game.currentPlayer.removeCardFromHand(chosenCard);
                game.deck.addToDiscardPile(chosenCard);
            }
            else {
                game.currentPlayer.addCardToHand(game.deck.drawCard());
            }

            playerIndex = nextPlayerIndex(playerIndex, game.players.size(), game.direction);
        }

        int score = 0;
        for (Player p: game.players){
            score += p.getPlayerScore();
        }

        System.out.println(game.currentPlayer.getName() + "is the winner. \nScore: " + score);

    }

}