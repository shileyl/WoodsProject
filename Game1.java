import javax.swing.*;
import javax.swing.event.MouseInputAdapter;

import java.awt.*;
import java.awt.event.*;

public class Game1 {

    private Player player1;
    private Player player2;
    private JFrame frame;
    private JPanel panel;

    private Grid grid;

    public Game1(String player1,String player2, int n){
        this.player1 = new Player(player1);
        this.player2 = new Player(player2);
        System.out.println(player1+" "+player2+" "+ n);
        init(n);
    }

    void init(int n){
        frame = new JFrame();//the window
        frame.setTitle(" Game 1 (K-2) ");
        panel = new JPanel();//the form
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);//make it visable
        frame.add(panel);//link the 2
        panel.setLayout(null);
        frame.setResizable(false);
        panel.setLayout(new GridLayout(n,n));

        grid = new Grid(panel, n, player1, player2);  //initialize grid class

        
        frame.setSize(WoodsSimulationMenu.sizeX, WoodsSimulationMenu.sizeY);//this needs to be at the end 
    }
    
}
