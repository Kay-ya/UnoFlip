import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.util.ArrayList;

public class GameView extends JFrame implements GameUpdate{
    Game model;
    JScrollPane scrollPane;
    JPanel handPanel, centerPanel, statusPanel;
    JLabel statusLabel, playerLabel;
    JButton btnNextPlayer;
    GameController controller;
    public GameView(){
        super("UnoFlip");
        Container contentPane = this.getContentPane();

        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        model = new Game();
        model.initialize(numberOfPlayers());
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
        updateStatus(Status.PLACEHOLDER);
        statusPanel.add(statusLabel);

        btnNextPlayer = new JButton("Next Player");
        btnNextPlayer.addActionListener(controller);
        statusPanel.add(btnNextPlayer);

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

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    public void updateStatus(Status status){
        String text = "Status: " + status.toString() + "                    ";
        statusLabel.setText(text);
    }

    public void updateCurrentTurn(Player player){
        String text = "Current Turn: " + player.getName() + "                    ";
        playerLabel.setText(text);
    }

    public void updateDiscardPile(Card card, Boolean side){
        centerPanel.removeAll();
        JButton drawButton = new JButton("Draw Card");
        drawButton.setPreferredSize(new Dimension(150,275));
        drawButton.addActionListener(controller);
        centerPanel.add(drawButton);

        JButton btn = cardToButton(card, side);
        //btn.setEnabled(false);
        centerPanel.add(btn);
        centerPanel.revalidate();
        centerPanel.repaint();
    }

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
    public int numberOfPlayers() {
        Object[] option = {2, 3, 4};
        Object selectNumberOfPlayers = JOptionPane.showInputDialog(this, "Choose the number of players:", "Select Players", JOptionPane.PLAIN_MESSAGE, null, option, option[0]);
        return (int) selectNumberOfPlayers;
    }

    public static void main(String[] args) {
        new GameView();
    }

    @Override
    public void handleNextPlayerEvent(NextPlayerEvent e) {
        Player player = e.getPlayer();
        updateStatus(Status.PLACEHOLDER);
        updateCurrentTurn(player);
        updateHandCards(player.getHand(), model.getSide());
    }

    @Override
    public void handleDrawCardEvent(DrawCardEvent e) {
        ArrayList<Card> hand = e.gethand();
        updateStatus(Status.PLACEHOLDER);
        updateHandCards(hand, model.getSide());
    }

    @Override
    public void handlePlaceCardEvent(PlaceCardEvent e) {
        ArrayList<Card> hand = e.getHand();
        Card topDiscard = e.getTopDiscard();
        updateHandCards(hand, model.getSide());
        updateDiscardPile(topDiscard, model.getSide());

    }

    public CardColor getWildCardColor(){
        CardColor[] option;
        if (model.getSide()){
            option = new CardColor[]{CardColor.BLUE, CardColor.RED, CardColor.GREEN, CardColor.YELLOW};
        }
        else {
            option = new CardColor[]{CardColor.PINK, CardColor.TEAL, CardColor.ORANGE, CardColor.PURPLE};
        }

        CardColor colorSelected = (CardColor) JOptionPane.showInputDialog(this, "Choose the color:", "Select Color", JOptionPane.PLAIN_MESSAGE, null, option, option[0]);
        return colorSelected;
    }
}
