import javax.swing.*;
import java.awt.*;

public class ButtonActionListener extends JFrame {
    //This will be the main panel.
    //We are going to put several buttons only in the "EAST" part of it.
    public ButtonActionListener() {
    JPanel panel1 = new JPanel();
    panel1.setLayout(new

    BorderLayout());

    //We create a sub-panel. Notice, that we don't use any layout-manager,
    //Because we want it to use the default FlowLayout
    JPanel subPanel = new JPanel();

    subPanel.add(new

    JButton( "1"));
    subPanel.add(new

    JButton( "2"));
    subPanel.add(new

    JButton( "3"));

    //Now we simply add it to your main panel.
    panel1.add(subPanel,BorderLayout.EAST);

        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
}

    public static void main(String[] args) {
        new ButtonActionListener();
    }
}
