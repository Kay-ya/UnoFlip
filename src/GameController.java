import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameController implements ActionListener {
    Game model;
    GameView view;
    public GameController(Game model, GameView view){
        this.model = model;
        this.view = view;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        if (source.getText().equals("Next Player")){

            CardColor selectedColor = null;
            if (source.getText().contains("WILD")){
                selectedColor = view.getWildCardColor();
            }
            model.nextPlayer(selectedColor);
            view.centerPanel.getComponent(0).setEnabled(true);

            // AI - Disables the cards panel - Milestone  3-----------------
            if(model.getCurrentPlayer().getName().contains("AI")) {
                int componentCnt = view.handPanel.getComponents().length;
                for (int i = 0; i < componentCnt; i++) {
                    view.handPanel.getComponent(i).setEnabled(false);
                }
                view.centerPanel.getComponent(0).setEnabled(false); //Disables draw button
            }
            // ------------------
        }
        else if (source.getText().equals("Draw Card")) {
            model.drawCard();
        }

        else {
            CardColor selectedColor = null;
            if (source.getText().contains("WILD")){
                selectedColor = view.getWildCardColor();
            }
            try {
                String strNumber = source.getActionCommand();
                int cardIndex = Integer.parseInt(strNumber);

                //Disables the cards panel for Human player //Milestone  3--------------------
                boolean isPlaced = model.placeCard(cardIndex, selectedColor);
                if(isPlaced) {
                    int componentCnt = view.handPanel.getComponents().length;
                    for (int i = 0; i < componentCnt; i++) {
                        view.handPanel.getComponent(i).setEnabled(false);
                    }
                    view.centerPanel.getComponent(0).setEnabled(false);
                }
            } catch (NumberFormatException error) {
                System.out.println("The string is not a valid integer.");
            }
        }
    }
}