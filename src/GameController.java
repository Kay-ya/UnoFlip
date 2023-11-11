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
    int size;
    Card handCard, topDiscardCard, drawnCard;

    /**
     * Constructor for the GameController class.
     *
     * @param game The Game object representing the UnoFlip! game.
     * @param view The GameView object representing the graphical user interface of the game.
     */
    public GameController(Game game, GameView view) {
        this.game = game;
        this.view = view;
        button = new JButton();
        addCardListeners();
        view.getDeckButton().addActionListener(this);
    }

    /**
     * Adds action listeners to all card buttons in the player's hand.
     */
    public void addCardListeners() {
        for (JButton cardButton : view.getCardButtons()) {
            cardButton.addActionListener(this);
        }
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
        for (JButton button : view.getCardButtons()) {
            handCard = game.getCurrentPlayer().getHand().get(i);
            if (Objects.equals(source.getText(), button.getText())) {
                game.placeCards(handCard, topDiscardCard);
                System.out.println("Card button clicked: " + button.getText());
                break;
            }
            i++;
        }

        if (source == view.getDeckButton()) {
            drawnCard = game.getDeck().drawCard();
            game.getCurrentPlayer().addCardToHand(drawnCard);
            System.out.println("Deck button clicked");
        }

        if (e.getActionCommand() == "Next Player") {
            //game.nextPlayerIndex(z, view.n, game.getDirection());
            game.getCurrentPlayer().getHand();
        }

        view.updateView(game);
        addCardListeners();
        //new GameEvent(game, this, removeCard);
    }
}
