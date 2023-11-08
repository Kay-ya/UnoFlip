import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameView extends JFrame{
    private JPanel cardsPanel, centerPanel, playerPanel;
    private Game game;
    private GameController controller;
    private JButton[] cardButtons;
    private JButton deckButton, discardButton;

    private JButton player1, player2, player3, player4;

    private JMenuBar jMenuBar;
    private JMenu jMenu;
    public JMenuItem exit;

    private JTextField nameTextField1, nameTextField2;

    public GameView(){
        this.setTitle("Uno Flip");
        game = new Game();
        cardsPanel = new JPanel();
        centerPanel = new JPanel();
        deckButton = new JButton("Draw from deck");

        for (int i = 0; i < 4; i++){
            Player p = new Player( "Player " + i );
            for (int j = 0; j < 7; j++) {
                p.addCardToHand(game.getDeck().drawCard());
            }
            game.addPlayer(p);
        }
        game.setCurrentPlayer(game.getPlayers().get(0));
        updateView(game);
        controller = new GameController(game, GameView.this);

        // ---------------------------

        // Player Input
        controller.playerInfo();

        // Player Panel and Buttons
        playerPanel = new JPanel();
        player1 = new JButton("Player 1");
        player2 = new JButton("Player 2");
        player3 = new JButton("Player 3");
        player4 = new JButton("Player 4");
        playerPanel.add(player1);
        playerPanel.add(player2);
        playerPanel.add(player3);
        playerPanel.add(player4);

        //Setting names
        player1.setText(controller.player1);
        player2.setText(controller.player2);
        player3.setText(controller.player3);
        player4.setText(controller.player4);

        // JMenu Set Up
        jMenuBar = new JMenuBar();
        jMenu = new JMenu("Menu");
        exit = new JMenuItem("Exit");
        jMenu.add(exit);
        jMenuBar.add(jMenu);

        // --Action Listener Added for Exit---
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.addMenuListeners();
            }
        });

        // frame setup
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        this.add(jMenuBar, BorderLayout.PAGE_START);             // JMenuBar not visible when player panel is shown
        this.add(playerPanel, BorderLayout.NORTH);                //Vice versa

        this.add(cardsPanel, BorderLayout.SOUTH);
        this.add(centerPanel, BorderLayout.CENTER);
        this.pack();
        this.setVisible(true);

    }
    public JButton[] getCardButtons() {
        return cardButtons;
    }
    public JButton getDeckButton() {
        return deckButton;
    }
    public void updateView(Game game){
        // bottom cards panel
        cardsPanel.removeAll();
        cardButtons = new JButton[game.getCurrentPlayer().getHand().size()];
        for (int i = 0; i < game.getCurrentPlayer().getHand().size(); i++) {
            String cardName = game.getCurrentPlayer().getHand().get(i).toString(game.getSide());
            cardButtons[i] = new JButton(cardName);
            cardsPanel.add(cardButtons[i]);
        }
        cardsPanel.revalidate();
        cardsPanel.repaint();

        // center panel
        centerPanel.removeAll();
        discardButton = new JButton(game.getDeck().topCardFromDiscardPile().toString(game.getSide()));
        centerPanel.add(deckButton);
        centerPanel.add(discardButton);
        centerPanel.revalidate();
        centerPanel.repaint();
    }

    public static void main(String[] args) {
        new GameView();
    }
}
