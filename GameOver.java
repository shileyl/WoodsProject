import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOver {

    private  JFrame frame;
    private  JPanel panel;
    private  JButton Imagebutton;
    private ImageIcon image;

//this is just for testing the class
    public static void main(String[]args){
        new GameOver();
    }


    public GameOver() {
        frame = new JFrame();
        frame.setTitle("GameOver");
        panel = new JPanel();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);//make it visable
        frame.add(panel);//link the 2
        panel.setLayout(null);
        frame.setResizable(false);

        //adding the button to the frame
        createIcon();
        Imagebutton = new JButton(image); 
        Imagebutton.setBounds(0,0,300,300);
        panel.add(Imagebutton);
        Imagebutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("The button has been clicked");
                WoodsSimulationMenu.main(null);
                frame.dispose();
            }
        });

        frame.setSize(300, 300);
    }

    void createIcon(){
        image = Util.createImageIcon(this, "Assets/GameOver1.png","End Game button");
        image = Util.scaleImageIcon(image, 300);
    }
    
}
