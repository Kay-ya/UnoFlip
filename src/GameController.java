import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class GameController implements ActionListener {
    Game game;
    GameView view;

    private int numberOfPlayers;

    private String playerName;

    String player1, player2, player3, player4;
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

    /**
     * Action Listener controlling the JMenu items
     */
    public void addMenuListeners(){
        if(view.exit.getText().equals("Exit")){
            int choice = JOptionPane.showConfirmDialog(view, "Do you really want to exit?", "Exit Confirmation", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                System.exit(0);     // Exit the application
            }
        }
    }

    /**
     * Prompts for the number of players and names from the user
     */
    public void playerInfo(){
        numberOfPlayers = Integer.parseInt(JOptionPane.showInputDialog(view,
                    "Enter the number of players (between 2 and 4):", 2));

        while(numberOfPlayers < 2  || numberOfPlayers > 4){
            numberOfPlayers = Integer.parseInt(JOptionPane.showInputDialog(view,
                    "Number of players have to be between 2 and 4!", 2));
        }
        playerName = JOptionPane.showInputDialog(view,
                "Enter player names seperated by ':' ", null);

        String[] result = playerName.split(":");
        if(result.length == 2){
            player1 = result[0];
            player2 = result[1];
        }else if (result.length == 3){
            player1 = result[0];
            player2 = result[1];
            player3 = result[2];
        }else{
            player1 = result[0];
            player2 = result[1];
            player3 = result[2];
            player4 = result[3];
        }

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("e: " + e.getSource());

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
