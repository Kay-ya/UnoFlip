import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.*;

import static java.awt.Color.red;

public class UNOFrame extends JApplet {

    JButton[] player1;
    JButton[] player2;
    JButton[] player3;
    JButton[] player4;

    public UNOFrame(){
        player1 = new JButton[7];
        player2 = new JButton[7];
        player3 = new JButton[7];
        player4 = new JButton[7];
    }

    public void init() {
        Box east = Box.createVerticalBox();
        for (int i = 0; i < 7; i++) {
            player1[i] = new JButton("Player1 Card" + i);
            player1[i].setLayout(null);
            east.add(player1[i]).setBounds(60, 400, 220, 30);
            player1[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    for(int i=0; i<7;i++){
                        ((JButton) e.getSource()).setText("Played");
                        east.remove(player1[0]);
                    }
                }
            });
        }

        Box south = Box.createHorizontalBox();
        for (int i = 0; i < 7; i++){
            player2[i] = new JButton("Player2 Card" + i);
            south.add(player2[i]);
            player2[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    for(int i=0; i<7; i++){
                        ((JButton) e.getSource()).setText("Played");
                        south.remove(player2[0]);
                    }
                }
            });

        }
        Box west = Box.createVerticalBox();
        for(int i = 0; i < player1.length; i++){
            player3[i] = new JButton("Player3 Card" + i);
            west.add(player3[i]);
            player3[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    for(int i=0; i<7 ; i++){
                        ((JButton) e.getSource()).setText("Played");
                        west.remove(player3[0]);
                    }
                }
            });
        }
        Box north = Box.createHorizontalBox();
        for(int i = 0; i < 7; i++){
            player4[i] = new JButton("Player4 Card" + i);
            north.add(player4[i]).setLocation(100,10);
            player4[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    for(int i=0; i<7; i++){
                        ((JButton) e.getSource()).setText("played");
                        //((JButton) e.getSource()).setText("Played");
                        north.remove(player4[0]);
                    }
                }
            });
        }
        Container cp = getContentPane();
        cp.add(BorderLayout.EAST, east);
        cp.add(BorderLayout.SOUTH, south);
        cp.add(BorderLayout.WEST, west);
        cp.add(BorderLayout.NORTH, north);
    }

    public static void main(String[] args) {
        run(new UNOFrame(), 1200, 700);
    }

    public static void run(JApplet applet, int width, int height) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(applet);
        frame.setSize(width, height);
        Game g = new Game();
        //if(g.players.size() == 4) {
            applet.init();
        //}
        applet.start();
        frame.setBackground(Color.black);
        frame.setVisible(true);
    }
}