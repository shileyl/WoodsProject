import javax.swing.*;
import javax.swing.event.MouseInputAdapter;

import java.awt.*;
import java.awt.event.*;

public class Game1 {

    private static int n;
    private static String player1;
    private static String player2;
    private static JFrame frame;
    private static JPanel panel;

    public Game1(String player1,String player2, int n){
        this.player1 = player1;
        this.player2 = player2;
        this.n = n;
        System.out.println(player1+" "+player2+" "+ n);
        init();

    }

    static void init(){
        frame = new JFrame();//the window
        frame.setTitle(" Game 1 (K-2) ");
        panel = new JPanel();//the form
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);//make it visable
        frame.add(panel);//link the 2
        panel.setLayout(null);
        frame.setResizable(false);
        //panel.setLayout(new GridLayout(n,n));//not a good idea


        
        frame.setSize(700,700);//this needs to be at the end 
    }
    
}