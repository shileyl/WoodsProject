import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Button extends JButton {
    
    protected int xPos, yPos;

    public Button(int xPos, int yPos) {
        super();
        this.xPos = xPos;
        this.yPos = yPos;

        addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Game.instance.playerPlaced(xPos, yPos);
            }
        });
    }


}
