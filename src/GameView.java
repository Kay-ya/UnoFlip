import javax.swing.*;
import java.awt.*;

public class GameView extends JFrame{
    private JPanel cardsPanel, centerPanel;
    private Game game;
    private GameController controller;
    private JButton[] cardButtons;
    private JButton deckButton, discardButton;
    public GameView(){
        this.setTitle("Uno Flip");
        game = new Game();
        for (int i = 0; i < 4; i++){
            Player p = new Player( "Player " + i );
            for (int j = 0; j < 7; j++) {
                p.addCardToHand(game.getDeck().drawCard());
            }
            game.addPlayer(p);
        }
        game.setCurrentPlayer(game.getPlayers().get(0));

        createCardPanel(game);
        createCenterPanel(game);

        // frame setup
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.add(cardsPanel, BorderLayout.SOUTH);
        this.add(centerPanel, BorderLayout.CENTER);
        this.pack();
        this.setVisible(true);
    }

    private void createCardPanel(Game game){
        cardsPanel = new JPanel();
        cardButtons = new JButton[game.getCurrentPlayer().getHand().size()];
        for (int i = 0; i < game.getCurrentPlayer().getHand().size(); i++) {
            String cardName = game.getCurrentPlayer().getHand().get(i).toString(game.getSide());
            cardButtons[i] = new JButton(cardName);
            cardsPanel.add(cardButtons[i]);
        }

    }

    private void createCenterPanel(Game game){
        centerPanel = new JPanel();
        deckButton = new JButton("draw from deck");
        discardButton = new JButton(game.getDeck().topCardFromDiscardPile().toString(game.getSide()));
        centerPanel.add(deckButton);
        centerPanel.add(discardButton);
    }
    public JButton[] getCardButtons() {
        return cardButtons;
    }

    public JButton getDeckButton() {
        return deckButton;
    }

    public static void main(String[] args) {
        new GameView();
    }
}
