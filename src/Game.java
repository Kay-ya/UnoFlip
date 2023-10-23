import java.util.ArrayList;
import java.util.Scanner;

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
    static int nextPlayerIndex(int curIndex, int numPlayers, Boolean direction){
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
                        game.players.get(nextPlayerIndex(playerIndex, game.players.size(), game.direction)).addCardToHand(game.deck.drawCard());
                        break;
                    case REVERSE:
                        game.direction = !game.direction;
                        break;
                    case SKIP:
                        if (game.direction){
                            playerIndex -= 1;
                            if (playerIndex == -1){
                                playerIndex = game.players.size()-1;
                            }
                        }
                        else {
                            playerIndex = (playerIndex + 1) % game.players.size();
                        }
                        break;
                    case FLIP:
                        game.isBright = !game.isBright;
                        break;
                    case WILD:
                        Color selectedColor = view.getPlayerColorChoice();
                        System.out.println(selectedColor + " is chosen");
                        Card wildCard = new Card(CardType.WILD, selectedColor, null, null);
                        game.deck.addToTopOfDiscardPile(wildCard); // Add the WILD card to the discard pile
                        break;


                }
                game.currentPlayer.removeCardFromHand(chosenCard);
                game.deck.addToDiscardPile(chosenCard);
            }
            else if (chosenCard != null){
                switch(chosenCard.getDarkCardType()){
                    case DRAW:
                        for (int j = 0; j < 5; j++){
                            game.players.get(nextPlayerIndex(playerIndex, game.players.size(), game.direction)).addCardToHand(game.deck.drawCard());
                        }
                        break;
                    case REVERSE:
                        game.direction = !game.direction;
                        break;
                    case SKIP:
                        if (game.direction){
                            playerIndex += 1;
                        }
                        else {
                            playerIndex -= 1;
                        }
                        break;
                    case FLIP:
                        game.isBright = !game.isBright;
                        break;


                    case WILD:
                        Color selectedColor = view.getPlayerColorChoice();
                        System.out.println(selectedColor + " is chosen");
                        Card wildCard = new Card(CardType.WILD, selectedColor, null, null);
                        game.deck.addToTopOfDiscardPile(wildCard); // Add the WILD card to the discard pile
                        break;


                }


                game.currentPlayer.removeCardFromHand(chosenCard);
               // game.deck.addToDiscardPile(chosenCard);
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