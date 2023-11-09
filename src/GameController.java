import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class GameController implements ActionListener {
    Game game;
    GameView view;
    JButton source;
    Card removeCard;
    public JButton button;
    int size;
    Card handCard, topDiscardCard, drawnCard;


    public GameController(Game game, GameView view){
        this.game = game;
        this.view = view;
        button = new JButton();
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
        String chosenWildCardColor = "";
        source = (JButton) e.getSource();
        topDiscardCard = game.getDeck().topCardFromDiscardPile();
        int i = 0;
        view.updateView(game);
        for (JButton button : view.getCardButtons()) {
            handCard = game.getCurrentPlayer().getHand().get(i);
            if(handCard.getBrightCardType().toString().equals("WILD") || handCard.getBrightCardType().toString().equals("WILD_DRAW")){
                chosenWildCardColor =  view.getWildCardColor();
                topDiscardCard.setBrightColor(Color.valueOf(chosenWildCardColor));
                handCard.setBrightColor(Color.valueOf(chosenWildCardColor));    //sets handCard color to color chosen by player
            }
            if (Objects.equals(source.getText(), button.getText())) {
                game.placeCards(handCard, topDiscardCard);                      //passing to model
                //System.out.println("Card button clicked: " + button.getText());
                break;
            }
            i++;
        }

        if (source == view.getDeckButton()) {
            drawnCard = game.getDeck().drawCard();
            game.getCurrentPlayer().addCardToHand(drawnCard);
            System.out.println("Deck button clicked");
        }

        view.updateView(game);
        addCardListeners();
        //new GameEvent(game, this, removeCard);
    }

}
