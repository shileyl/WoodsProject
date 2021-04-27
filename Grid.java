import javax.swing.JButton;
import javax.swing.JPanel;

public class Grid {
    
    private Location[][] grid;
    private JButton[][] buttons;
    JPanel panel;

    public Grid(JPanel panel, int sizeX, int sizeY, Player p1, Player p2) {  //Constructor for a square grid
        grid = new Location[sizeX][sizeY];
        buttons = new JButton[sizeX][sizeY];
        this.panel = panel;

        int buttonSizeX = (WoodsSimulationMenu.sizeX - 100)/sizeX;
        int buttonSizeY = (WoodsSimulationMenu.sizeY - 100)/sizeY;

        for(int y = 0; y < sizeY; y++) {
            for(int x = 0; x < sizeX; x++) {
                grid[x][y] = new Location();
                buttons[x][y] = new JButton();
                
                JButton button = buttons[x][y];
                button = new JButton();
                button.setVisible(true);
                button.setBounds(50 + buttonSizeX * x, 50 + buttonSizeY * y, buttonSizeX, buttonSizeY);
                System.out.println("x: " + (50 + buttonSizeX * x) + " y: " + (50 + buttonSizeY * y));
                panel.add(button);
            }
        }

        grid[0][0].p = p1;            //Player at location 0,0 is player 1
        grid[sizeX-1][sizeY-1].p = p2;  //Player at location size-1, size-1 is player 2
    }
}
