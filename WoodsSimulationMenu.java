import javax.swing.*;
import javax.swing.event.MouseInputAdapter;

import java.awt.*;
import java.awt.event.*;

public class WoodsSimulationMenu implements ActionListener{

    private static JFrame frame;
    private static JPanel panel;
    private static JLabel prompt;
    //private static JLabel Title;
    private static JTextField userInput;
    private static JButton contunue;
    private static JLabel NPromps;
    private static JLabel NPromps2;
    private static JLabel coords;
    private static JTextField userInput2;
    private static JTextField userInput3;

    public static void main(String[] args){
        frame = new JFrame();//the window
        frame.setTitle("Woods Simulation");
        panel = new JPanel();//the form
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);//make it visable
        frame.add(panel);//link the 2
        panel.setLayout(null);
        frame.setResizable(false);
        //panel.setLayout(new GridLayout(9,9));

        //X and y pos of the mouse. makes setting boundries easier
        coords = new JLabel("hello this is a test");
        coords.setBounds(10,10,150,25);
        panel.add(coords);
        frame.addMouseMotionListener(new MouseInputAdapter(){
            @Override
            public void mouseMoved(MouseEvent e) {

                super.mouseMoved(e);

                int x = e.getX();
                int y = e.getY();

                var text = String.format("x: %d, y: %d", x, y);

                coords.setText(text);
            }
        });

        //Title of the Program
        prompt = new JLabel("Woods Simulation");
        prompt.setFont(new Font("Verdana", Font.PLAIN, 18));
        prompt.setBounds(250,20,200,25);//x,y,width,height
        panel.add(prompt);

        //Message to the user
        prompt = new JLabel("Enter the Grade Level You Want To Play");
        prompt.setBounds(200,50,400,25);//x,y,width,height
        panel.add(prompt);

        //user input
        userInput = new JTextField();
        userInput.setBounds(275,90,100,25);
        userInput.addActionListener(new WoodsSimulationMenu());
        panel.add(userInput);

        //for the interactions
        NPromps = new JLabel();//for game one
        NPromps.setBounds(200,150,700,200);
        panel.add(NPromps);

        userInput2 = new JTextField();//multi putpose textfield
        userInput2.setVisible(false);
        userInput2.setBounds(200,260,100,25);
        panel.add(userInput2);

        //205,290
        NPromps2 = new JLabel();//for game one
        NPromps2.setBounds(200,220,700,200);
        panel.add(NPromps2);

        userInput3 = new JTextField();//multi putpose textfield
        userInput3.setVisible(false);
        userInput3.setBounds(200,330,100,25);
        panel.add(userInput3);

        //continue button
        contunue =  new JButton("Contunue");
        contunue.setVisible(false);
        contunue.setBounds(200,360,100,25);
        panel.add(contunue);



        frame.setSize(700,700);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        String grade = userInput.getText();
        System.out.println(grade.toUpperCase());

        switch(grade.toUpperCase()){
            case "K": Game1();userInput.setText("");
                break;
            case "1": Game1();userInput.setText("");
                break;
            case "2": Game1();userInput.setText("");
                break;
            case "3": Game2();userInput.setText("");
                break;
            case "4": Game2();userInput.setText("");
                break;
            case "5": Game2();userInput.setText("");
                break;
            case "6": Game3();userInput.setText("");
                break;
            case "7": Game3();userInput.setText("");
                break;
            case "8": Game3();userInput.setText("");
                break;

        }  
    }


    private void Game3() {
        

        //if(s==null||snum==null){ System.out.println("USER ERROR 0002");}
    }


    private void Game2() {
        //create new lables asking for the names of the players and the rules of the game
        //ask how many player there will be
        NPromps.setBounds(100,150,700,200);
        NPromps.setText("Enter the Names of the Players Septerated by a Space. You can add up to 4 Players");
        NPromps2.setText("Enter the size of the forest. 2 Numbers, X and Y in that order.");
        userInput2.setVisible(true);
        userInput3.setVisible(true);
        contunue.setVisible(true);

        contunue.addActionListener(new ActionListener(){//when the button is preseed it will execute game1.java
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = userInput2.getText();
                String[] names = s.split(" ");
                //String snum = userInput3.getText(); 
                String[] snums = userInput3.getText().split(" ");//convert this to an int this is gona be 2 ints
                int x = Integer.parseInt(snums[0]);
                int y = Integer.parseInt(snums[1]);
                new Game2(names,x,y);
            }
        });
        //if(s==null||snum==null){ System.out.println("USER ERROR 0002");}
    }


    private void Game1() {
        //create new lables asking for the names of the players and the rules of the game
        NPromps.setText("Enter the Names of 2 Players Septerated by a Space.");
        NPromps2.setText("Enter the size of the Forest. Only one Number. It can only be a square");
        userInput2.setVisible(true);
        userInput3.setVisible(true);
        contunue.setVisible(true);
        
        contunue.addActionListener(new ActionListener(){//when the button is preseed it will execute game1.java
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = userInput2.getText();
                String[] names = s.split(" ");
                String snum = userInput3.getText();//convert this to an int 
                new Game1(names[0], names[1], Integer.parseInt(snum));
                //frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
        });

        //if(s==null||snum==null){ System.out.println("USER ERROR 0003");}
    }
}