import javax.swing.*;
import java.awt.*;
import java.awt.Color;


public class GameView extends JFrame implements GameUpdate{
    public JPanel cardsPanel, centerPanel, westPanel, northPanel, eastPanel;
    public Game game;
    public Card card;
    public JMenu menu;
    public JMenuBar menuBar;
    JButton nextPlayer, deckButton, discardButton;
    public static JLabel status, playerLabel;
    public JMenuItem menuItemSave, menuItemExit;
    public GameController controller;
    public JButton[] cardButtons;
    JScrollPane scrollPane;
    Player p;
    public int handSize, n, m;

    public static int number;
    public static int AINumber;
    public GameView(){
        this.setTitle("Uno Flip");
        game = new Game();
        cardsPanel = new JPanel();
        centerPanel = new JPanel();
        eastPanel = new JPanel();
        game.addGameView(this);
        centerPanel.setBackground(Color.DARK_GRAY);
        cardsPanel.setBackground(Color.DARK_GRAY);
        eastPanel.setBackground(Color.BLUE);
        westPanel = new JPanel();
        northPanel = new JPanel();
        menuBar = new JMenuBar();
        menu = new JMenu("Game");
        menuItemSave = new JMenuItem("Save");
        menuItemExit = new JMenuItem("Exit");
        menu.add(menuItemSave);
        menu.add(menuItemExit);
        menuBar.add(menu);
        number = 0;
        AINumber = 0;
        //textArea = new JTextArea(5, 30);
        scrollPane = new JScrollPane(cardsPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        deckButton = new JButton("Deck");
        deckButton.setBorderPainted(false);
        deckButton.setOpaque(true);
        deckButton.setBackground(Color.lightGray);
        n = numberOfPlayers();
        m = numberOfAIPlayers();
        for (int i = 0; i < (n); i++){
            p = new Player( "H" + (i+1) );
            playerLabel = new JLabel("H" + (i+1));
            for (int j = 0; j < 7; j++) {
                p.addCardToHand(card = game.getDeck().drawCard());
            }
            game.addPlayer(p);
        }
        for (int i = 0; i < m; i++){
            p = new Player( "AI" + (i+1) );
            playerLabel = new JLabel("AI" + (i+1));
            for (int j = 0; j < 7; j++) {
                p.addCardToHand(card = game.getDeck().drawCard());
            }
            game.addPlayer(p);
        }

        westPanel.setBackground(Color.DARK_GRAY);

        game.setCurrentPlayer(game.getPlayers().get(0));
        updateView(game);
        controller = new GameController(game, this);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.add(scrollPane, BorderLayout.SOUTH);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(westPanel, BorderLayout.WEST);
        this.add(eastPanel, BorderLayout.EAST);
        this.add(menuBar, BorderLayout.NORTH);
        nextPlayer = new JButton("Next Player");
        westPanel.add(nextPlayer);
        nextPlayer.addActionListener(controller);
        nextPlayer.setActionCommand("Next Player");
        status = new JLabel("Status: ");
        status.setOpaque(true);
        status.setBackground(Color.lightGray);
        playerLabel.setOpaque(true);
        playerLabel.setBackground(Color.lightGray);
        westPanel.add(status);
        playerLabel.setText("H1");
        eastPanel.add(playerLabel);
        this.pack();
        this.setVisible(true);
        this.setEnabled(true);
        cardsPanel.setEnabled(true);
        centerPanel.setEnabled(true);

    }

    public JButton[] getCardButtons() {
            return cardButtons;
    }

    public JButton getDeckButton() {
        return deckButton;
    }

    public JButton getPlayerButton(){
        return nextPlayer;
    }
    public void updateView(Game game){
        cardsPanel.removeAll();
        handSize = game.getCurrentPlayer().getHand().size();
        cardButtons = new JButton[handSize];
        for (int i = 0; i < handSize; i++) {
            String cardName = game.getCurrentPlayer().getHand().get(i).toString(game.getSide());
            cardButtons[i] = new JButton(cardName);
            cardButtons[i].setPreferredSize(new Dimension(150, 275));
            if(cardButtons[i].getText().contains("GREEN")) {
                cardButtons[i].setOpaque(true);
                cardButtons[i].setBackground(Color.GREEN.darker());
            } else if (cardButtons[i].getText().contains("BLUE")) {
                cardButtons[i].setOpaque(true);
                cardButtons[i].setBackground(Color.BLUE.brighter());
            } else if (cardButtons[i].getText().contains("RED")) {
                cardButtons[i].setOpaque(true);
                cardButtons[i].setBackground(Color.RED.brighter());
            }
            else if(cardButtons[i].getText().contains("YELLOW")){
                cardButtons[i].setOpaque(true);
                cardButtons[i].setBackground(Color.ORANGE);
            }
            else if (cardButtons[i].getText().contains("TEAL")) {
                cardButtons[i].setOpaque(true);
                Color Teal = new Color(0,255,255);
                cardButtons[i].setBackground(Teal);
            }
            else if (cardButtons[i].getText().contains("PINK")) {
                cardButtons[i].setOpaque(true);
                Color Pink = new Color(255, 192, 203);
                cardButtons[i].setBackground(Pink);
            }
            else if (cardButtons[i].getText().contains("ORANGE")) {
                cardButtons[i].setOpaque(true);
                Color Orange = new Color(255, 165, 0);
                cardButtons[i].setBackground(Orange);
            }
            else if (cardButtons[i].getText().contains("PURPLE")) {
                cardButtons[i].setOpaque(true);
                Color Purple= new Color(102,0,153);
                cardButtons[i].setBackground(Purple.darker());
            }
            cardsPanel.add(cardButtons[i]);
        }
        scrollPane.revalidate();
        scrollPane.repaint();
        cardsPanel.revalidate();
        cardsPanel.repaint();
        centerPanel.removeAll();
        discardButton = new JButton(game.getDeck().topCardFromDiscardPile().toString(game.getSide()));
        discardButton.setPreferredSize(new Dimension(150,275));
        if(discardButton.getText().contains("GREEN")) {
            discardButton.setOpaque(true);
            discardButton.setBackground(Color.GREEN.darker());
        } else if (discardButton.getText().contains("BLUE")) {
            discardButton.setOpaque(true);
            discardButton.setBackground(Color.BLUE.brighter());
        } else if (discardButton.getText().contains("RED")) {
            discardButton.setOpaque(true);
            discardButton.setBackground(Color.RED.brighter());
        }
        else if(discardButton.getText().contains("YELLOW")){
            discardButton.setOpaque(true);
            discardButton.setBackground(Color.ORANGE);
        }
        else if (discardButton.getText().contains("TEAL")) {
            discardButton.setOpaque(true);
            Color Teal = new Color(0,255,255);
            discardButton.setBackground(Teal);
        }
        else if (discardButton.getText().contains("PINK")) {
            discardButton.setOpaque(true);
            Color Pink = new Color(255, 192, 203);
            discardButton.setBackground(Pink);
        }
        else if (discardButton.getText().contains("ORANGE")) {
            discardButton.setOpaque(true);
            Color Orange = new Color(255, 165, 0);
            discardButton.setBackground(Orange);
        }
        else if (discardButton.getText().contains("PURPLE")) {
            discardButton.setOpaque(true);
            Color Purple = new Color(102,0,153);
            discardButton.setBackground(Purple.darker());
        }

        deckButton.setPreferredSize(new Dimension(150,275));
        centerPanel.add(deckButton);
        centerPanel.add(discardButton);

        centerPanel.revalidate();
        centerPanel.repaint();
    }
    public int numberOfPlayers(){
        Object[] option = {2, 3, 4, 5, 6};
        Object selectNumberOfPlayers = JOptionPane.showInputDialog(this, "Choose the number of players:", "Select Players", JOptionPane.PLAIN_MESSAGE, null, option, option[0]);
        number = (int) selectNumberOfPlayers;
        return number;
    }

    public int numberOfAIPlayers(){
        Object[] option = {2, 3, 4, 5, 6};
        Object selectNumberOfPlayers = JOptionPane.showInputDialog(this, "Choose the number of AI players:", "Select AI Players", JOptionPane.PLAIN_MESSAGE, null, option, option[0]);
        AINumber = (int) selectNumberOfPlayers;
        return AINumber;
    }
    public static int sendNumberOfPlayers(){
        return number;
    }

    public static int sendNumberOfAIPlayers(){
        return AINumber;
    }

    /**
     * Gets the wild card color for the light side.
     * @return String
     */
    public String getWildLightCardColor(){
        String[] option = {"RED", "BLUE", "GREEN", "YELLOW"};
        String colorSelected = (String) JOptionPane.showInputDialog(this, "Choose the color:", "Select Color",
                JOptionPane.PLAIN_MESSAGE, null, option, option[0]);
        return colorSelected;

    }
    /**
     * Gets the wild card color for the dark side.
     * @return String
     */
    public String getWildDarkCardColor(){
        String[] option = {"TEAL", "PURPLE", "PINK", "ORANGE"};
        String colorSelected = (String) JOptionPane.showInputDialog(this, "Choose the color:", "Select Color",
                JOptionPane.PLAIN_MESSAGE, null, option, option[0]);
        return colorSelected;
    }
    @Override
    public void handleUnoUpdate(GameEvent e) {
        String card = e.getDrawCard();
        JButton button = new JButton(card);
        cardsPanel.add(button);

        Boolean booleanStatus =e.getStatus();
        if(booleanStatus == true){
            status.setText("Status: Invalid card");
        }
        else if(booleanStatus == false){
            status.setText("Status: Correct Card Selected");
        }
    }
    public void handlePlayerUnoUpdate(GameEvent e){
        Player p = e.getPlayer();
        String name = p.getName();
        System.out.println("PLAYER NAMEE: " + name);
        playerLabel.setText(name);
        System.out.println(p.getName());
    }

    public void handlePlayerPlaced(GameEvent e)
    {
        if(e.getCardPlaced() == true){
            handSize = game.getCurrentPlayer().getHand().size();
            cardButtons = new JButton[handSize];
            for (int i = 0; i < handSize; i++) {
               // cardButtons[i].setEnabled(false);
            }
            //cardsPanel.setVisible(false);
            //this.getComponent(0).setEnabled(false);
            //this.setEnabled(false);

            //centerPanel.setEnabled(false);
        }
        else{
            cardsPanel.setEnabled(true);
        }
    }
    public static void main(String[] args) {
        new GameView();
    }
}
