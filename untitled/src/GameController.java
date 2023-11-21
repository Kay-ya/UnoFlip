import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * This class serves as the controller in the MVC pattern for the Uno Flip card game.
 * It handles actions such as clicking on cards or the deck, and makes updates to the game state based on this.
 */
public class GameController implements ActionListener {
    Game game;
    GameView view;
    JButton source;
    public JButton button;
    String chosenWildCardColor;
    int size;
    Card handCard, topDiscardCard, drawnCard;
    Player player = new Player("");

    /**
     * Constructor for the GameController class.
     *
     * @param game The Game object representing the UnoFlip! game.
     * @param view The GameView object representing the graphical user interface of the game.
     */
    public GameController(Game game, GameView view){
        this.game = game;
        this.view = view;
        button = new JButton();
        addCardListeners();
        view.getDeckButton().addActionListener(this);
        chosenWildCardColor = "";
    }

    /**
     * Adds action listeners to all card buttons in the player's hand.
     */
    public void addCardListeners(){
        for (JButton cardButton : view.getCardButtons()) {
            cardButton.addActionListener(this);
        }
    }

    /**
     * Returns the wild light card.
     * @return String
     */
    public String wildLightCard()
    {
        chosenWildCardColor = view.getWildLightCardColor();
        return chosenWildCardColor;
    }

    /**
     * Handles the action events triggered by user interactions.
     *
     * @param e The ActionEvent representing the user's action.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Action performed");
        boolean isMatchFound = false;
        source = (JButton) e.getSource();
        topDiscardCard = game.getDeck().topCardFromDiscardPile();
        int i = 0;
        view.updateView(game);
        if ((source.getText().equals(CardType.WILD) || (source.getText().equals(CardType.WILD_DRAW)))){
            chosenWildCardColor =  view.getWildLightCardColor();
        }
        for (JButton button : view.getCardButtons()) {
            handCard = game.getCurrentPlayer().getHand().get(i);
            System.out.println("$$$$ HAND CARD " + handCard.getBrightColor() + " " + handCard.getBrightCardType() );
            if ((source.getText().equals("WILD WILD") || (source.getText().equals("WILD_DRAW WILD")))) {
                if (game.getSide() && handCard.getBrightCardType() == CardType.WILD) {
                    game.setChosenWildLightCardColor(view.getWildLightCardColor());
                } else if (game.getSide() && handCard.getBrightCardType() == CardType.WILD_DRAW) {
                    game.setChosenWildLightCardColor(view.getWildLightCardColor());
                } else if (!game.getSide() && handCard.getDarkCardType() == CardType.WILD) {
                    game.setChosenWildDarkCardColor(view.getWildDarkCardColor());
                } else if (!game.getSide() && handCard.getDarkCardType() == CardType.WILD_DRAW) {
                    game.setChosenWildDarkCardColor(view.getWildDarkCardColor());
                }
            }
            System.out.println("source.getText() " + source.getText() );
            System.out.println("button.getText() " + button.getText() );
//            if (Objects.equals(source.getText(), button.getText())) {
//                game.placeCards(handCard, topDiscardCard);
//                System.out.println("Card button clicked: " + button.getText());
//                break;
//            }

            //----------------- Human player replaced with AI player
            if(e.getActionCommand() == "Next Player"){
                game.getCurrentPlayer().displayHand(true);
                if(game.getSide() && (handCard.getBrightColor() == topDiscardCard.getBrightColor() || handCard.getBrightCardType() == topDiscardCard.getBrightCardType())) {
                    game.placeCards(handCard, topDiscardCard);
                    isMatchFound = true;
                }
                if(!game.getSide() && (handCard.getDarkColor() == topDiscardCard.getDarkColor() || handCard.getDarkCardType() == topDiscardCard.getDarkCardType())) {
                    game.placeCards(handCard, topDiscardCard);
                    isMatchFound = true;
                }
                game.getCurrentPlayer().displayHand(true);
            }
            if(isMatchFound){
                game.returnNextPlayer();
                break;
            }
            //-----------------
            i++;
        }
        if(!isMatchFound){
            drawnCard = game.getDeck().drawCard();
            game.getCurrentPlayer().addCardToHand(drawnCard);
            game.returnNextPlayer();
            System.out.println("New Card Drawn");
        }
        if (source == view.getDeckButton()) {
            drawnCard = game.getDeck().drawCard();
            game.getCurrentPlayer().addCardToHand(drawnCard);
            System.out.println("Deck button clicked");
        }
        view.updateView(game);
        addCardListeners();
    }
}
