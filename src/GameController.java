import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class GameController implements ActionListener {
    Game game;
    GameView view;
    JButton source;
    public JButton button;
    String chosenWildCardColor;
    int size;
    Card handCard, topDiscardCard, drawnCard;

    public GameController(Game game, GameView view){
        this.game = game;
        this.view = view;
        button = new JButton();
        addCardListeners();
        view.getDeckButton().addActionListener(this);
        chosenWildCardColor = "";
    }

    public void addCardListeners(){
        for (JButton cardButton : view.getCardButtons()) {
            cardButton.addActionListener(this);
        }
    }

    public String wildLightCard()
    {
        chosenWildCardColor = view.getWildLightCardColor();
        return chosenWildCardColor;
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        System.out.println("Action performed");
        source = (JButton) e.getSource();
        topDiscardCard = game.getDeck().topCardFromDiscardPile();
        int i = 0;
        view.updateView(game);
        for (JButton button : view.getCardButtons()) {
            handCard = game.getCurrentPlayer().getHand().get(i);
            if(game.getSide() && handCard.getBrightCardType() == CardType.WILD){
                chosenWildCardColor =  view.getWildLightCardColor();
                topDiscardCard.setBrightColor(Color.valueOf(chosenWildCardColor));
                handCard.setBrightColor(Color.valueOf(chosenWildCardColor));
                  //sets handCard color to color chosen by player
            }
            else if(game.getSide() && handCard.getBrightCardType() == CardType.WILD_DRAW){
                chosenWildCardColor =  view.getWildLightCardColor();
                topDiscardCard.setBrightColor(Color.valueOf(chosenWildCardColor));
                handCard.setBrightColor(Color.valueOf(chosenWildCardColor));
            }
            else if(!game.getSide() && handCard.getDarkCardType() == CardType.WILD){
                chosenWildCardColor =  view.getWildDarkCardColor();
                topDiscardCard.setDarkColor(Color.valueOf(chosenWildCardColor));
                handCard.setDarkColor(Color.valueOf(chosenWildCardColor));
            }
            else if(!game.getSide() && handCard.getDarkCardType() == CardType.WILD_DRAW){
                chosenWildCardColor =  view.getWildDarkCardColor();
                topDiscardCard.setDarkColor(Color.valueOf(chosenWildCardColor));
                handCard.setDarkColor(Color.valueOf(chosenWildCardColor));
            }
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

        if(e.getActionCommand() == "Next Player"){
                //game.nextPlayerIndex(z, view.n, game.getDirection());
                //game.getCurrentPlayer().getHand();
                game.returnNextPlayer();

            }

        view.updateView(game);
        addCardListeners();
        //new GameEvent(game, this, removeCard);
    }
}

