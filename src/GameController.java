import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameController implements ActionListener {
    Game game;
    GameView view;

    public GameController(Game game, GameView view){
        this.game = game;
        this.view = view;
        for (JButton cardButton : view.getCardButtons()) {
            cardButton.addActionListener(this);
        }
        view.getDeckButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();

        for ( int i = 0; i < view.getCardButtons().length; i++) {
            JButton button = view.getCardButtons()[i];
            if (source == button) {

                System.out.println("Card button clicked: " + button.getText());
                return;
            }
        }

        if (source == view.getDeckButton()) {
            System.out.println("Deck button clicked");
        }
    }
}
