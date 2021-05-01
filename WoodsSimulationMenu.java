import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.event.MouseInputAdapter;

public class WoodsSimulationMenu implements ActionListener{
    private static JFrame frame;
    public static JPanel panel;
    private static JLabel prompt;
    //private static JLabel Title;
    private static JTextField userInput;
    private static JButton cont;
    private static JLabel NPromps;
    private static JLabel NPromps2;
    private static JLabel coords;
    private static JTextField userInput2;
    private static JTextField userInput3;
    private static Game game;
    public static int sizeX, sizeY;
    public static int x, y;
    public static int movementProcedure = 0;
    private static JLabel radioButtonsText;
    private static JRadioButton randomMP, otherMP, anotherMP;
    private static ButtonGroup radioButtonGroup;


    public static void main(String[] args){
        sizeX = 700;
        sizeY = 700;
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

                x = e.getX();
                y = e.getY();

                var text = String.format("x: %d, y: %d", x, y);

                coords.setText(text);
            }
        });

        //Title of the Program
        prompt = new JLabel("Woods Simulation");
        prompt.setFont(new Font("Verdana", Font.PLAIN, 18));
        //prompt.setBounds(250,20,200,25);//x,y,width,height
        prompt.setBounds(0,20,sizeX,25);
        prompt.setHorizontalAlignment(0);
        panel.add(prompt);

        //Message to the user
        prompt = new JLabel("Enter the Grade Level You Want To Play");
        //prompt.setBounds(200,50,400,25);//x,y,width,height
        prompt.setBounds(0,50,sizeX,25);
        prompt.setHorizontalAlignment(0);
        panel.add(prompt);

        //user input
        userInput = new JTextField();
        //userInput.setBounds(275,90,100,25);
        userInput.setBounds(0,90,sizeX,25);
        userInput.setHorizontalAlignment(0);
        userInput.addActionListener(new WoodsSimulationMenu());
        panel.add(userInput);

        //for the interactions
        NPromps = new JLabel();//for game one
        //NPromps.setBounds(200,150,700,200);
        NPromps.setBounds(0,220,sizeX,34);
        NPromps.setHorizontalAlignment(0);
        panel.add(NPromps);

        userInput2 = new JTextField();//multi putpose textfield
        userInput2.setVisible(false);
        //userInput2.setBounds(200,260,100,25);
        userInput2.setBounds(0,260,sizeX,25);
        userInput2.setHorizontalAlignment(0);
        panel.add(userInput2);

        //205,290
        NPromps2 = new JLabel();//for game one
        //NPromps2.setBounds(200,220,700,200);
        NPromps2.setBounds(0,300,sizeX,25);
        NPromps2.setHorizontalAlignment(0);
        panel.add(NPromps2);

        userInput3 = new JTextField();//multi putpose textfield
        userInput3.setVisible(false);
        //userInput3.setBounds(200,330,100,25);
        userInput3.setBounds(0,330,sizeX,25);
        userInput3.setHorizontalAlignment(0);
        panel.add(userInput3);

        //continue button
        cont =  new JButton("Continue");
        cont.setVisible(false);
        //cont.setBounds(200,360,100,25);
        cont.setBounds(0,360,sizeX,25);
        cont.setHorizontalAlignment(0);
        panel.add(cont);

        createMovementDropDown();
        frame.setSize(sizeX,sizeY);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String grade = userInput.getText();
        System.out.println(grade.toUpperCase());
        
        switch(grade.toUpperCase()){
            case "K": StartK2Game();userInput.setText("");
                break;
            case "1": StartK2Game();userInput.setText("");
                break;
            case "2": StartK2Game();userInput.setText("");
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
            default: printAlert(8);;
                break;
        }  
    }


    private void Game3() {
        //NPromps.setBounds(100,130,700,200);
        NPromps.setText("<html>Enter the Names of the Players Seperated by a Space. You can add up to 4 Players and<br/>pick the starting positions in the next window.</html>");
        NPromps2.setText("Enter the size of the forest. 2 Numbers, X and Y in that order.");
        userInput2.setVisible(true);
        userInput3.setVisible(true);
        cont.setVisible(true);
        displayRadioButtons();


        cont.addActionListener(new ActionListener(){//when the button is preseed it will execute game1.java
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = userInput2.getText();     //names
                String snum = userInput3.getText();  //grid size text
                continuePressed(2, s, snum);
            }
        });
        //if(s==null||snum==null){ System.out.println("USER ERROR 0002");}
    }


    private void Game2() {
        //create new lables asking for the names of the players and the rules of the game
        //ask how many player there will be
        //NPromps.setBounds(100,130,700,200);
        NPromps.setText("<html>Enter the Names of the Players Seperated by a Space. You can add up to 4 Players and<br/>pick the starting positions in the next window.</html>");
        NPromps2.setText("Enter the size of the forest. 2 Numbers, X and Y in that order.");
        userInput2.setVisible(true);
        userInput3.setVisible(true);
        cont.setVisible(true);


        cont.addActionListener(new ActionListener(){//when the button is preseed it will execute game1.java
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = userInput2.getText();     //names
                String snum = userInput3.getText();  //grid size text
                continuePressed(1, s, snum);
            }
        });
        //if(s==null||snum==null){ System.out.println("USER ERROR 0002");}
    }


    //called when user enters K, 1, or 2 as grade level
    private void StartK2Game() {
        //create new lables asking for the names of the players and the rules of the game
        NPromps.setText("Enter the Names of 2 Players Seperated by a Space.");
        NPromps2.setText("Enter the size of the Forest. Only one Number. It can only be a square");
        userInput2.setVisible(true);
        userInput3.setVisible(true);
        cont.setVisible(true);
        
        cont.addActionListener(new ActionListener(){//when the button is preseed it will execute game1.java
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = userInput2.getText();     //names
                String snum = userInput3.getText();  //grid size text
                continuePressed(0, s, snum);
            }
        });

        //if(s==null||snum==null){ System.out.println("USER ERROR 0003");}
    }

    private static void createMovementDropDown() {
        //display text
        radioButtonsText = new JLabel("Select the movement procedure.");
        radioButtonsText.setBounds(0, 460, sizeX, 40);
        radioButtonsText.setHorizontalAlignment(0); //center text

        //Create a button for each movement procedure
        randomMP = new JRadioButton("Random");
        randomMP.setActionCommand("0");
        randomMP.setBounds(0,500,sizeX,30);
        randomMP.setHorizontalAlignment(0);
        randomMP.setSelected(true);

        otherMP = new JRadioButton("RandomSometimes");
        otherMP.setActionCommand("1");
        otherMP.setBounds(0,530,sizeX,30);
        otherMP.setHorizontalAlignment(0);
        
        anotherMP = new JRadioButton("StickTogether");
        anotherMP.setActionCommand("2");
        anotherMP.setBounds(0,560,sizeX,30);
        anotherMP.setHorizontalAlignment(0);

        //group the radio buttons
        radioButtonGroup = new ButtonGroup();
        radioButtonGroup.add(randomMP);
        radioButtonGroup.add(otherMP);
        radioButtonGroup.add(anotherMP);
        
        //add the buttons to the panel
        panel.add(randomMP);
        panel.add(otherMP);
        panel.add(anotherMP);
        panel.add(radioButtonsText);
        
        //make everything invisible for now
        randomMP.setVisible(false);
        otherMP.setVisible(false);
        anotherMP.setVisible(false);
        radioButtonsText.setVisible(false);

        //create an action listener for each button
        randomMP.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                radioButtonPressed(e.getActionCommand());
            }
        });
        otherMP.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                radioButtonPressed(e.getActionCommand());
            }
        });
        anotherMP.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                radioButtonPressed(e.getActionCommand());
            }
        });
    }

    private static void displayRadioButtons() {
        //set everything visible
        randomMP.setVisible(true);
        otherMP.setVisible(true);
        anotherMP.setVisible(true);
        radioButtonsText.setVisible(true);
    }

    private static void radioButtonPressed(String movementProcedureID) {
        int id = Integer.parseInt(movementProcedureID);
        movementProcedure = id;

        System.out.println("Picked movement procedure " + movementProcedure);
    }

    private static void printAlert(int id){
        String s = "Error!";
        switch(id) {
            case 0:
                s = "Making any part of the Grid bigger than 50 is not recommended!!";
                break;
            case 1:
                s = "Enter at least 2 names.";
                break;
            case 2:
                s = "Enter only 2 names.";
                break;
            case 3:
                s = "Enter less than 4 names.";
                break;
            case 4:
                s = "Only enter 1 number for the size.";
                break;
            case 5:
                s = "Enter 2 numbers X and Y for the grid size.";
                break;
            case 6:
                s = "Enter integer numbers for the grid size.";
                break;
            case 7:
                s = "Grid size must be greater than 1 on x and y axis.";
                break;
            case 8:
                s = "Enter \"K\", or a number 1 through 8 for the grade level.";
                break;
        }
        JOptionPane.showMessageDialog(null, s);
    }

    private static void continuePressed(int grade, String namesString, String sizeString) {
        String[] names = namesString.split(" ");
        String[] size = sizeString.split(" ");
        int[] sizeInt = new int[size.length];

        if(names.length < 2) {
            printAlert(1);
            return;
        }

        if(grade == 0) {
            if(names.length > 2) {
                printAlert(2);
                return;
            }

            if(size.length != 1) {
                printAlert(4);
                return;
            }
        }
        if(grade == 1 || grade == 2) {
            if(names.length > 4) {
                printAlert(3);
                return;
            }
            if(size.length != 2) {
                printAlert(5);
                return;
            }
        }

        for(int i = 0; i < size.length; i++) {
            try{
                sizeInt[i] = Integer.parseInt(size[i]);
            } catch(Exception e) {
                printAlert(6);
                return;
            }
            if(sizeInt[i] < 2) {
                printAlert(7);
                return;
            }
            if(sizeInt[i] > 50)
                printAlert(0);
        }

        //create an instance of the game class based on the grade level
        if(grade == 0)
            game = new GameK2(names, "K-2 Game", sizeInt[0], sizeInt[0]);
        if(grade == 1)
            game = new Game35(names, "3-5 Game", sizeInt[0], sizeInt[1]);
        if(grade == 2)
            game = new Game68(names, "6-8 Game", sizeInt[0], sizeInt[1]);

        frame.dispose();
    }
}