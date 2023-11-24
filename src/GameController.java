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
            model.nextPlayer();
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
                model.placeCard(cardIndex, selectedColor);
            } catch (NumberFormatException error) {
                System.out.println("The string is not a valid integer.");
            }
        }
    }
}
