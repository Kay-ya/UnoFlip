import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.util.ArrayList;

import static java.lang.System.exit;


/**
 * This class represents the graphical user interface for the UNO game.
 * It extends JFrame and implements the GameUpdate interface to handle game events.
 */
public class GameView extends JFrame implements GameUpdate{
    Game model;
    JScrollPane scrollPane;
    Container contentPane;
    JPanel handPanel, centerPanel, statusPanel;
    JLabel statusLabel, playerLabel, roundLabel;
    JLabel playerScoreLabel;
    JButton btnNextPlayer;
    GameController controller;


    /**
     * GameView constructor to initialize the JSwing classes and objects used in the view
     */
    public GameView(){
        super("UnoFlip");
        contentPane = this.getContentPane();

        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        model = new Game();
        try {
            int humanPlayer = numberOfPlayers();
            model.initialize(humanPlayer, numberOfAIPlayers(humanPlayer));
            //if()
        }catch (Exception e){
            JOptionPane.showMessageDialog(this, "Number of players must be at least 1");
            System.out.println("Number of players must be at least 1");
            exit(0);
            //new GameView();
        }
        model.addView(this);
        controller = new GameController(model, this);

        // add items to frame
        // Status section at the top
        statusPanel = new JPanel();
        statusPanel.setBackground(Color.LIGHT_GRAY);

        playerLabel = new JLabel();
        updateCurrentTurn(model.getCurrentPlayer());
        statusPanel.add(playerLabel);

        statusLabel = new JLabel();
        updateStatus(Status.NEW_TURN);
        statusPanel.add(statusLabel);

        btnNextPlayer = new JButton("Next Player");
        btnNextPlayer.addActionListener(controller);
        statusPanel.add(btnNextPlayer);

        playerScoreLabel = new JLabel();
        updateScore(model.getCurrentPlayer().getPlayerScore(), model.getCurrentPlayer().getName());
        //contentPane.add(playerScoreLabel);

        roundLabel = new JLabel();
        updateRound(model.getCurrentRound());
        statusPanel.add(roundLabel);

        contentPane.add(statusPanel);

        // center section with draw from deck and top discard
        centerPanel = new JPanel();
        centerPanel.setBackground(Color.DARK_GRAY);
        updateDiscardPile(model.getDeck().topCardFromDiscardPile(), model.getSide());
        contentPane.add(centerPanel);

        // cards in hand section
        handPanel = new JPanel();
        handPanel.setBackground(Color.DARK_GRAY);


        scrollPane = new JScrollPane(handPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        updateHandCards(model.getCurrentPlayer().getHand(), model.getSide());

        contentPane.add(scrollPane);
        int componentCnt = handPanel.getComponents().length;
        for (int i = 0; i < componentCnt; i++) {
            handPanel.getComponent(i).setEnabled(true);
        }
        centerPanel.getComponent(0).setEnabled(true);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }


    /**
     * Displays the updated score of the players
     * @param score
     */
    public void updateScore(int score, String playerName){
        String text = "Score: " + score;
        String player = "Player " + playerName;
        playerScoreLabel.setText(text);
    }

    /**
     * Displays the updated round of the game
     * @param round
     */
    public void updateRound(int round){
        String text = "Round: " + round;
        roundLabel.setText(text);
    }

    /**
     * Displays the updated status of the cards placed by the player in the game
     * @param status
     */
    public void updateStatus(Status status){
        String text = "Status: " + status.toString() + "                    ";
        statusLabel.setText(text);
    }

    /**
     * Displays the updated current turn of each player (Human or AI)
     * @param player
     */
    public void updateCurrentTurn(Player player){
        String text = "Current Turn: " + player.getName() + "                    ";
        playerLabel.setText(text);
    }

    /**
     * Displays the updated discard pile everytime a playable card is played
     * @param card
     * @param side
     */
    public void updateDiscardPile(Card card, Boolean side){
        centerPanel.removeAll();
        JButton drawButton = new JButton("Draw Card");
        drawButton.setPreferredSize(new Dimension(150,275));
        drawButton.addActionListener(controller);
        centerPanel.add(drawButton);

        JButton btn = cardToButton(card, side);

        centerPanel.add(btn);
        centerPanel.revalidate();
        centerPanel.repaint();
    }
    /**
     * Updates the hand displayed by each player upon selecting a card from the deck
     * @param hand
     * @param side
     */
    public void updateHandCards(ArrayList<Card> hand, Boolean side){
        handPanel.removeAll();
        for (int i = 0; i < hand.size(); i++) {
            Card card  = hand.get(i);
            JButton btn = cardToButton(card, side);
            btn.setActionCommand(String.valueOf(i));
            btn.addActionListener(controller);
            handPanel.add(btn);
        }
        scrollPane.revalidate();
        scrollPane.repaint();
        handPanel.revalidate();
        handPanel.repaint();
    }

    /**
     * Updates and enables the center panel cards
     */
    public void enablePanel(){
        this.centerPanel.getComponent(0).setEnabled(true);
    }

    /**
     * Updates and disables the center panel and hand panel cards
     */
    public void disablePanel(){
        int componentCnt = this.handPanel.getComponents().length;
        for (int i = 0; i < componentCnt; i++) {
            this.handPanel.getComponent(i).setEnabled(false);
        }
        this.centerPanel.getComponent(0).setEnabled(false); //Disables draw button
    }

    /**
     * Displays the colour of cards on both dark and light side
     * @param card
     * @param side
     * @return JButton
     */
    private JButton cardToButton(Card card, Boolean side) {
        JButton btn = new JButton(card.toString(side));
        btn.setPreferredSize(new Dimension(150, 275));
        btn.setOpaque(true);
        if(btn.getText().contains("GREEN")) {
            btn.setBackground(Color.GREEN.darker());
        }
        else if (btn.getText().contains("BLUE")) {
            btn.setBackground(Color.BLUE.brighter());
        }
        else if (btn.getText().contains("RED")) {
            btn.setBackground(Color.RED.brighter());
        }
        else if (btn.getText().contains("YELLOW")) {
            btn.setBackground(Color.ORANGE);
        }
        else if (btn.getText().contains("TEAL")) {
            Color Teal = new Color(0,255,255);
            btn.setBackground(Teal);
        }
        else if (btn.getText().contains("PINK")) {
            Color Pink = new Color(255, 192, 203);
            btn.setBackground(Pink);
        }
        else if (btn.getText().contains("ORANGE")) {
            Color Orange = new Color(255, 165, 0);
            btn.setBackground(Orange);
        }
        else if (btn.getText().contains("PURPLE")) {
            Color Purple= new Color(102,0,153);
            btn.setBackground(Purple.darker());
        }
        return btn;
    }

    /**
     * Enables the user to select the number of human players
     * @return int
     */
    public int numberOfPlayers() {
        Object[] option = {0, 1, 2, 3, 4, 5, 6};
        Object selectNumberOfPlayers = JOptionPane.showInputDialog(this, "Choose the number of Human players:", "Select Players", JOptionPane.PLAIN_MESSAGE, null, option, option[0]);
        if(selectNumberOfPlayers != null)
            return (int) selectNumberOfPlayers;
        else
            return 0;
    }

    /**
     * Enables the user to select the number of AI players
     * @param human
     * @return int
     */
    public int numberOfAIPlayers(int human){
        Object[] option = new Object[0];
        if(human == 1) {
           option = new Object[]{1, 2, 3, 4, 5, 6};
        } else if (human == 0) {
            option = new Object[]{2, 3, 4, 5, 6};
        }
        else{
            option = new Object[]{0 ,1, 2, 3, 4, 5, 6};
        }

        Object selectNumberOfPlayers = JOptionPane.showInputDialog(this, "Choose the number of AI players:", "Select AI Players", JOptionPane.PLAIN_MESSAGE, null, option, option[0]);
        if(selectNumberOfPlayers != null)
            return (int) selectNumberOfPlayers;
        else
            return 0;
    }
    public static void main(String[] args) {
        new GameView();
    }

    /**
     * Gets the score and Round following the MVC pattern
     * @param e
     */
    @Override
    public void handleUpdateScoreEvent(UpdateScoreEvent e) {
        updateScore(e.getScore(), e.getPlayer());
        if(e.getScore() > 500){
            JOptionPane.showMessageDialog(this, e.getPlayer() + " WINS!!");
            exit(0);
        }
        updateRound(e.getRound());
    }

    /**
     * Gets the next player when user presses next player button following the MVC pattern
     * @param e
     */
    @Override
    public void handleNextPlayerEvent(NextPlayerEvent e) {
        Player player = e.getPlayer();
        updateStatus(e.getStatus());
        updateCurrentTurn(player);
        updateHandCards(player.getHand(), model.getSide());
    }

    /**
     * Gets the drawn card and updates the hand of the current player
     * @param e
     */
    @Override
    public void handleDrawCardEvent(DrawCardEvent e) {
        ArrayList<Card> hand = e.getHand();
        updateStatus(e.getStatus());
        updateHandCards(hand, model.getSide());
    }

    /**
     * Gets and updates the hand placed on the discard pile by the user
     * @param e
     */
    @Override
    public void handlePlaceCardEvent(PlaceCardEvent e) {
        ArrayList<Card> hand = e.getHand();
        Card topDiscard = e.getTopDiscard();
        updateStatus(e.getStatus());
        updateHandCards(hand, model.getSide());
        updateDiscardPile(topDiscard, model.getSide());

    }

    /**
     * Returns the card color for the selected user input wild card
     * @return CardColor
     */
    public CardColor getWildCardColor(){
        CardColor[] option;
        if (model.getSide()){
            option = new CardColor[]{CardColor.BLUE, CardColor.RED, CardColor.GREEN, CardColor.YELLOW};
        }
        else {
            option = new CardColor[]{CardColor.PINK, CardColor.TEAL, CardColor.ORANGE, CardColor.PURPLE};
        }

        CardColor colorSelected = (CardColor) JOptionPane.showInputDialog(this, "Choose the color:", "Select Color", JOptionPane.PLAIN_MESSAGE, null, option, option[0]);
        while (colorSelected == null){
            colorSelected = (CardColor) JOptionPane.showInputDialog(this, "Please choose a color to proceed:", "Select Color", JOptionPane.PLAIN_MESSAGE, null, option, option[0]);
        }

        return colorSelected;
    }
}