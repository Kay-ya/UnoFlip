import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class GameController implements ActionListener {
    Game game;
    GameView view;

    public GameController(Game game, GameView view){
        this.game = game;
        this.view = view;
        addCardListeners();
        view.getDeckButton().addActionListener(this);
    }

    public void addCardListeners(){
        for (JButton cardButton : view.getCardButtons()) {
            cardButton.addActionListener(this);
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Action performed");
        JButton source = (JButton) e.getSource();
        Card handCard;
        Card topDiscardCard = game.getDeck().topCardFromDiscardPile();
        int i = 0;
        view.updateView(game);
        for (JButton button : view.getCardButtons()){
            handCard = game.getCurrentPlayer().getHand().get(i);
            if (Objects.equals(source.getText(), button.getText())) {
                if (game.getSide() && (handCard.getBrightColor() == topDiscardCard.getBrightColor() || handCard.getBrightCardType() == topDiscardCard.getBrightCardType())){
                    game.getCurrentPlayer().removeCardFromHand(handCard);
                    game.getDeck().addToDiscardPile(handCard);
                }
                else if (!game.getSide() && (handCard.getDarkColor() == topDiscardCard.getDarkColor() || handCard.getDarkCardType() == topDiscardCard.getDarkCardType())){
                    game.getCurrentPlayer().removeCardFromHand(handCard);
                    game.getDeck().addToDiscardPile(handCard);
                }
                System.out.println("Card button clicked: " + button.getText());
                break;
            }
            i++;
        }

        if (source == view.getDeckButton()) {
            Card drawnCard = game.getDeck().drawCard();
            game.getCurrentPlayer().addCardToHand(drawnCard);
            System.out.println("Deck button clicked");
        }

        view.updateView(game);
        addCardListeners();
    }
}
