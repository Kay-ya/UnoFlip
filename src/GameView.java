import apple.laf.JRSUIUtils;

import javax.print.attribute.standard.JobHoldUntil;
import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;
import java.awt.Scrollbar;

public class GameView extends JFrame implements GameUpdate{
    public JPanel cardsPanel, centerPanel, westPanel, eastPanel, northPanel;
    public Game game;
    public Card card;
    public JMenu menu;
    public JMenuBar menuBar;
    public JMenuItem menuItemSave, menuItemExit;
    public GameController controller;
    public JButton[] cardButtons;
    //public ScrollBar scrollBar;
    int n;
    JTextArea area;
    JTextArea textArea;
    JScrollPane scrollPane;//= new JScrollPane(textArea);
    //JTextArea area;
    Player p;
    public int handSize;
    public JButton deckButton, discardButton;
    public GameView(){
        this.setTitle("Uno Flip");
        game = new Game();
        cardsPanel = new JPanel();
        centerPanel = new JPanel();
        game.addGameView(this);
        centerPanel.setBackground(Color.DARK_GRAY);
        cardsPanel.setBackground(Color.DARK_GRAY);
        westPanel = new JPanel();
        eastPanel = new JPanel();
        northPanel = new JPanel();
        menuBar = new JMenuBar();
        menu = new JMenu("Game");
        menuItemSave = new JMenuItem("Save");
        menuItemExit = new JMenuItem("Exit");
        menu.add(menuItemSave);
        menu.add(menuItemExit);
        menuBar.add(menu);
        textArea = new JTextArea(5, 30);
        scrollPane = new JScrollPane(cardsPanel);
        scrollPane.setPreferredSize(new Dimension(450, 110));
        //scrollBar = new Scrollbar(Scrollbar.HORIZONTAL, 0, 60, 0, 300);
        deckButton = new JButton("Deck");
        deckButton.setBorderPainted(false);
        deckButton.setOpaque(true);
        deckButton.setBackground(Color.lightGray);
        //if()
        n = numberOfPlayers();
        for (int i = 0; i < n; i++){
            p = new Player( "Player " + i );
            for (int j = 0; j < 7; j++) {
                p.addCardToHand(card = game.getDeck().drawCard());
            }
            game.addPlayer(p);
        }
        westPanel.setBackground(Color.DARK_GRAY);
        eastPanel.setBackground(Color.DARK_GRAY);

        game.setCurrentPlayer(game.getPlayers().get(0));
        updateView(game);
        controller = new GameController(game, GameView.this);
        System.out.println("THE FUCKING CARD: ");
        //System.out.println(controller.returnCard());
        //System.out.println(controller.returnCard());
        // frame setup
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.add(cardsPanel, BorderLayout.SOUTH);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(westPanel, BorderLayout.WEST);
        this.add(eastPanel, BorderLayout.EAST);
        this.add(menuBar, BorderLayout.NORTH);
        //menu.add(menuBar);
        //menuBar.add(menuItem);
        //this.add(menu);
        //this.add(northPanel, BorderLayout.NORTH);
        JButton nextPlayer = new JButton("Next Player");
        westPanel.add(nextPlayer);
        JLabel status = new JLabel("Status:");
        eastPanel.add(status);
        //scrollPane.setContent
        //add(scrollPane, BorderLayout.SOUTH);
        //this.cardsPanel.add(scrollPane);
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
        handSize = game.getCurrentPlayer().getHand().size();
        cardButtons = new JButton[handSize];
        //for(int j = 0; j<n;j++ ) {
            for (int i = 0; i < handSize; i++) {
                String cardName = game.getCurrentPlayer().getHand().get(i).toString(game.getSide());
                cardButtons[i] = new JButton(cardName);
                cardButtons[i].setPreferredSize(new Dimension(150, 275));
                if(cardButtons[i].getText().contains("GREEN")) {
                    //cardButtons[i].setBorderPainted(false);
                    cardButtons[i].setOpaque(true);
                    cardButtons[i].setBackground(Color.GREEN.darker());
                } else if (cardButtons[i].getText().contains("BLUE")) {
                    //cardButtons[i].setBorderPainted(true);
                    cardButtons[i].setOpaque(true);
                    cardButtons[i].setBackground(Color.BLUE.brighter());
                } else if (cardButtons[i].getText().contains("RED")) {
                    //cardButtons[i].setBorderPainted(false);
                    cardButtons[i].setOpaque(true);
                    cardButtons[i].setBackground(Color.RED.brighter());
                }
                else if(cardButtons[i].getText().contains("YELLOW")){
                    //cardButtons[i].setBorderPainted(false);
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
                /**else if(cardButtons[i].getText().equals("WILD WILD")){
                 System.out.println("WILD");
                 wildCard();
                 }
                 else if (cardButtons[i].getText().equals("WILD_DRAW WILD")) {
                 System.out.println("WILD2");
                 wildDrawTwoCard();
                 }**/
                cardsPanel.add(cardButtons[i]);
                //cardsPanel.add(cardButtons[7]);
            }
        //}
        cardsPanel.revalidate();
        cardsPanel.repaint();
        //controller.returnCard();
        // center panel
        centerPanel.removeAll();
        discardButton = new JButton(game.getDeck().topCardFromDiscardPile().toString(game.getSide()));
        discardButton.setPreferredSize(new Dimension(150,275));
        if(discardButton.getText().contains("GREEN")) {
            //cardButtons[i].setBorderPainted(false);
            discardButton.setOpaque(true);
            discardButton.setBackground(Color.GREEN.darker());
        } else if (discardButton.getText().contains("BLUE")) {
            //cardButtons[i].setBorderPainted(true);
            discardButton.setOpaque(true);
            discardButton.setBackground(Color.BLUE.brighter());
        } else if (discardButton.getText().contains("RED")) {
            //cardButtons[i].setBorderPainted(false);
            discardButton.setOpaque(true);
            discardButton.setBackground(Color.RED.brighter());
        }
        else if(discardButton.getText().contains("YELLOW")){
            //cardButtons[i].setBorderPainted(false);
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
            Color Purple= new Color(102,0,153);
            discardButton.setBackground(Purple.darker());
        }

        deckButton.setPreferredSize(new Dimension(150,275));
        centerPanel.add(deckButton);
        centerPanel.add(discardButton);

        centerPanel.revalidate();
        centerPanel.repaint();
    }

    public int numberOfPlayers(){
        Object[] option = {2, 3, 4};
        Object selectNumberOfPlayers = JOptionPane.showInputDialog(this, "Choose the number of players:", "Select Players", JOptionPane.PLAIN_MESSAGE, null, option, option[0]);
        int number = (int) selectNumberOfPlayers;
        return number;
    }

    public String wildCard(){
        Object[] option = {"RED", "GREEN", "BLUE", "YELLOW"};
        Object selectColor = JOptionPane.showInputDialog(this, "Choose a color:", "Select Color", JOptionPane.PLAIN_MESSAGE, null, option, option[0]);
        String selectedColor = (String) selectColor;
        return selectedColor;
    }

    public String wildDrawTwoCard(){
        Object[] option = {"RED", "GREEN", "BLUE", "YELLOW"};
        Object selectColor = JOptionPane.showInputDialog(this, "Choose a color:", "Select Color", JOptionPane.PLAIN_MESSAGE, null, option, option[0]);
        String selectedColor = (String) selectColor;
        return selectedColor;
    }


    public void getPlayerSize(){
        Game g = new Game();
        ArrayList<Player> play = g.getPlayers();
        System.out.println(play.size());
    }

    public static void main(String[] args) {
        GameView view = new GameView();
        //new DeckController();
        //view.numberOfPlayers();
    }

    @Override
    public void handleUnoUpdate(GameEvent e) {
        String card = e.getDrawCard();
        JButton button = new JButton(card);
        cardsPanel.add(button);

        String removeCard = e.getRemovedCard();
        //cardsPanel.remove();
    }
}
