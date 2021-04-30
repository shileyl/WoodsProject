import javax.swing.*;
import java.awt.event.*;

public class OurButtons extends JButton {

    protected int x,y;

    OurButtons(int x, int y) {
        super();
        this.x = x;
        this.y = y;
        this.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Click();
            }
        });
    }

    void Click() {
        System.out.println(x + " " + y);
    }
    
    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

}
