import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameController implements ActionListener {
    Game model;
    GameView view;
    int cardIndex;
    boolean isPlaced;
    CardColor selectedColor;

    /**
     * GameController constructor initializes  the model and view instances
     * @param model
     * @param view
     */
    public GameController(Game model, GameView view){
        this.model = model;
        this.view = view;
        this.cardIndex = 0;
        this.selectedColor = null;
        this.isPlaced = false;
    }

    /**
     * Handles the action performed of the GUI in GameView.java
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        if (source.getText().equals("Next Player")){
            // model.nextPlayer();
            if (source.getText().contains("WILD")){
                selectedColor = view.getWildCardColor();
            }
            model.nextPlayer(selectedColor);
            view.centerPanel.getComponent(0).setEnabled(true);
            if(model.getCurrentPlayer().getName().contains("AI")) {
                int componentCnt = view.handPanel.getComponents().length;
                for (int i = 0; i < componentCnt; i++) {
                    view.handPanel.getComponent(i).setEnabled(false);
                }
                view.centerPanel.getComponent(0).setEnabled(false); //Disables draw button
            }
        }
        else if (source.getText().equals("Draw Card")) {
            model.drawCard();
        }
        else {
            if (source.getText().contains("WILD")){
                selectedColor = view.getWildCardColor();
            }
            try {
                String strNumber = source.getActionCommand();
                cardIndex = Integer.parseInt(strNumber);
                isPlaced = model.placeCard(cardIndex, selectedColor);
                if(isPlaced) {
                    int componentCnt = view.handPanel.getComponents().length;
                    for (int i = 0; i < componentCnt; i++) {
                        view.handPanel.getComponent(i).setEnabled(false);
                    }
                    view.centerPanel.getComponent(0).setEnabled(false);
                }
                model.placeCard(cardIndex, selectedColor);
            } catch (NumberFormatException error) {
                System.out.println("The string is not a valid integer.");
            }
        }


    }
}
