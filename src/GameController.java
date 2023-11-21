import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

/**
 * This class serves as the controller in the MVC pattern for the Uno Flip card game.
 * It handles actions such as clicking on cards or the deck, and makes updates to the game state based on this.
 */
public class GameController implements ActionListener {
    Game game;
    GameView view;
    JButton source;
    ArrayList<Card> handIterate;
    public JButton button;
    String chosenWildCardColor;
    int size;
    Card handCard, topDiscardCard, drawnCard;

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
        handIterate = new ArrayList<>();
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
        source = (JButton) e.getSource();
        topDiscardCard = game.getDeck().topCardFromDiscardPile();
        int i = 0;
        view.updateView(game);
        if ((source.getText().equals(CardType.WILD) || (source.getText().equals(CardType.WILD_DRAW)))){
            chosenWildCardColor =  view.getWildLightCardColor();
        }
        for (JButton button : view.getCardButtons()) {
            handCard = game.getCurrentPlayer().getHand().get(i);
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
            if (Objects.equals(source.getText(), button.getText()) )//&& GameView.playerLabel.getText().contains("H"))
                {
                game.placeCards(handCard, topDiscardCard);
                //button.setEnabled(false);
                System.out.println("Card button clicked: " + button.getText());
                break;
            }
            else{
                /**while(GameView.playerLabel.getText().contains("A")){
                    handIterate = game.getCurrentPlayer().getHand();
                    for (Card c: handIterate) {
                        System.out.println(c.getBrightCardType() + " " + c.getBrightColor());
                        if(c.getBrightCardType() == (topDiscardCard.getBrightCardType())){
                            game.placeCards(c,topDiscardCard);
                        }
                    }
                    break;
                 }
                //source.setEnabled(false);**/
            }
            i++;
        }
        if (source == view.getDeckButton()) {
            drawnCard = game.getDeck().drawCard();
            game.getCurrentPlayer().addCardToHand(drawnCard);
            System.out.println("Deck button clicked");
        }
        if(e.getActionCommand() == "Next Player"){
            game.returnNextPlayer();
        }
        view.updateView(game);
        addCardListeners();
    }
}
