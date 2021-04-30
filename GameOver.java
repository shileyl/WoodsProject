import java.awt.*;
import javax.swing.*;

public class GameOver {

    private  JFrame frame;
    private  JPanel panel;
    private  JButton Imagebutton;
    private ImageIcon image;

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
        Imagebutton.setBounds(100,100,100,100);
        panel.add(Imagebutton);

        frame.setSize(300, 300);
    }

    void createIcon(){
        image = Util.createImageIcon(this, "Assets/2boys.png","this is a 2 boys");
        image = Util.scaleImageIcon(image, 20);
    }
    
}