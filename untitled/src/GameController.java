import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameController implements ActionListener {
    Game model;
    GameView view;

    /**
     *  GameController constructor initializes the model and view parameters.
     * @param model
     * @param view
     */
    public GameController(Game model, GameView view){
        this.model = model;
        this.view = view;
    }

    /**
     * Gets the user selected changes on the GUI for the specific action performed
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton source = (JButton) e.getSource();

            if (source.getText().equals("Next Player")) {
                model.nextPlayer();
                view.enablePanel();
                if (model.getCurrentPlayer().getName().contains("AI")) {
                    view.disablePanel();
                }
            } else if (source.getText().equals("Draw Card")) {
                model.drawCard();
                view.disablePanel();
            } else {
                CardColor selectedColor = null;
                if (source.getToolTipText().contains("WILD")){
                    selectedColor = view.getWildCardColor();
                }
                try {
                    String strNumber = source.getActionCommand();
                    int cardIndex = Integer.parseInt(strNumber);
                    boolean isPlaced = model.placeCard(cardIndex, selectedColor);
                    if (isPlaced) {
                        view.disablePanel();
                    }
                } catch (NumberFormatException error) {
                    System.out.println("The string is not a valid integer.");
                }
            }
        }
        else if (e.getSource() instanceof JMenuItem){
            Object source = e.getActionCommand();
             if (source.equals("Save")) {
                model.saveGame("SaveUnoGame1.ser");
            } else if (source.equals("Load")) {
                model.loadGame("SaveUnoGame1.ser");
            } else if (source.equals("Undo")) {
                System.out.println("undo pressd");
                model.saveGame("SaveUndoGameState.ser");
                model.loadGame("Undo.ser");
            }else if (source.equals("Redo")) {
                 System.out.println("redo pressed");
                 model.loadGame("SaveUndoGameState.ser");
                 view.disablePanel();
            }
        }
    }
}
