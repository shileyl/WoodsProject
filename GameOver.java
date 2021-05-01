import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOver {

    private  JFrame frame;
    private  JPanel panel;
    private  JButton Imagebutton;
    private ImageIcon image;
    private int sizeX, sizeY;

//this is just for testing the class
   // public static void main(String[]args){
    //    new GameOver();
    //}


    public GameOver(Game instance) {//creating the ending window and adding loop to the game again?
        frame = new JFrame();
        frame.setTitle("GameOver");
        panel = new JPanel();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);//make it visable
        frame.add(panel);//link the 2
        panel.setLayout(null);
        frame.setResizable(false);

        sizeX = WoodsSimulationMenu.sizeX;
        sizeY = WoodsSimulationMenu.sizeY;

        //adding the button to the frame
        createIcon();
        Imagebutton = new JButton(image); 
        Imagebutton.setBounds(0,0,sizeX,sizeY);
        panel.add(Imagebutton);
        Imagebutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("The button has been clicked");
                WoodsSimulationMenu.main(null);
                frame.dispose();
                createNewGame(instance);
            }
        });

        frame.setSize(sizeX, sizeY);
    }

    void createIcon(){//creating the icon for the button
        image = Util.createImageIcon(this, "Assets/GameOver3.png","End Game button");
        image = Util.scaleImageIcon(image, sizeX);
    }

    void createNewGame(Game instance){//loops back to the begining of the simulation 
        if(instance.gameID ==1)
            new GameK2(instance.playerNames, instance.windowName, instance.boardSizeX, instance.boardSizeY);
        else if(instance.gameID ==2)
            new Game35(instance.playerNames, instance.windowName, instance.boardSizeX, instance.boardSizeY);
        else if (instance.gameID ==3)
            new Game68(instance.playerNames, instance.windowName, instance.boardSizeX, instance.boardSizeY);
    }
    
}