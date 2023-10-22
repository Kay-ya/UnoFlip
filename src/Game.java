import java.util.ArrayList;

public class Game {
    public ArrayList <Player> players; //Stores the player name in an ArrayList
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

    /**
     * Returns the player index of the card to see which direction the game is being played.
     * @param curIndex
     * @param numPlayers
     * @param direction
     * @return int
     */
    public static int nextPlayerIndex(int curIndex, int numPlayers, Boolean direction){
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
            game.deck.topCardFromDiscardPile().printCard(game.isBright);

            StringBuilder strCards = new StringBuilder();
            game.currentPlayer.displayHand(game.isBright);

            Card chosenCard = view.playerChooseOption(game.currentPlayer, game.deck.topCardFromDiscardPile(), game.isBright);
            Color chosenColor = null;
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
                        chosenColor = view.playerColorInput(game.isBright);
                        if (game.isBright){
                            chosenCard.setBrightColor(chosenColor);
                        }
                        else{
                            chosenCard.setDarkColor(chosenColor);
                        }
                        break;
                    case WILD_DRAW:
                        chosenColor = view.playerColorInput(game.isBright);
                        if (game.isBright){
                            chosenCard.setBrightColor(chosenColor);
                        }
                        else{
                            chosenCard.setDarkColor(chosenColor);
                        }
                        playerIndex = nextPlayerIndex(playerIndex, game.players.size(), game.direction);
                        game.players.get(playerIndex).addCardToHand(game.deck.drawCard());
                        game.players.get(playerIndex).addCardToHand(game.deck.drawCard());
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
                        chosenColor = view.playerColorInput(game.isBright);
                        if (game.isBright){
                            chosenCard.setBrightColor(chosenColor);
                        }
                        else{
                            chosenCard.setDarkColor(chosenColor);
                        }
                        break;
                    case WILD_DRAW:
                        chosenColor = view.playerColorInput(game.isBright);
                        playerIndex = nextPlayerIndex(playerIndex, game.players.size(), game.direction);
                        Card c = game.deck.drawCard();
                        game.players.get(playerIndex).addCardToHand(c);
                        if (game.isBright){
                            chosenCard.setBrightColor(chosenColor);
                            while (c.getBrightColor() != chosenColor){
                                c = game.deck.drawCard();
                                game.players.get(playerIndex).addCardToHand(c);
                            }
                        }
                        else{
                            chosenCard.setDarkColor(chosenColor);
                            while (c.getDarkColor() != chosenColor){
                                c = game.deck.drawCard();
                                game.players.get(playerIndex).addCardToHand(c);
                            }
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