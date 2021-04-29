import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Grid {
    
    private Location[][] locations;
    private JButton[][] buttons;

    private JFrame frame;
    private JPanel panel;
    
    private ImageIcon playerIcon;
    private ImageIcon twoPlayersIcon;
    private ImageIcon threePlayersIcon;
    private ImageIcon fourPlayersIcon;
    private ImageIcon woodsButtons; 

    int sizeX, sizeY;

    public Grid(String windowName, int sizeX, int sizeY) {  //Constructor for grid
        this.sizeX = sizeX;
        this.sizeY = sizeY;

        createWindow(windowName);
        initializeGrid();
        createIcons();
    }

    void createWindow(String windowName) {
        frame = new JFrame();   //Create the window
        frame.setTitle(windowName);

        panel = new JPanel();   //Create a panel
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);  //Make it visable
        frame.add(panel);  //Link the 2
        panel.setLayout(null);
        frame.setResizable(false);
        //panel.setLayout(new GridLayout(n,n));//not a good idea
        
        frame.setSize(WoodsSimulationMenu.sizeX, WoodsSimulationMenu.sizeY);//this needs to be at the end 
    }

    void initializeGrid() {
        locations = new Location[sizeX][sizeY];
        buttons = new JButton[sizeX][sizeY];

        int buttonSizeX = (WoodsSimulationMenu.sizeX - 100)/sizeX;
        int buttonSizeY = (WoodsSimulationMenu.sizeY - 100)/sizeY;

        //Loop through x and y coordinates
        for(int y = 0; y < sizeY; y++) {
            for(int x = 0; x < sizeX; x++) {
                locations[x][y] = new Location();   //Create a new Location and Button object
                buttons[x][y] = new JButton(woodsButtons);
                
                buttons[x][y].setVisible(true);
                buttons[x][y].setBounds(50 + buttonSizeX * x, 50 + buttonSizeY * y, buttonSizeX, buttonSizeY);

                panel.add(buttons[x][y]);
            }
        }
    }

    void createIcons() {
        playerIcon = createImageIcon("Assets/boy.png","This is a Boy");
        twoPlayersIcon = createImageIcon("Assets/boy.png","This is a Boy");
        woodsButtons = createImageIcon("Assets/Forest2.jpg","this is the woods");
    }

    protected ImageIcon createImageIcon(String path,String description) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    //check to see if the x and y coordinates entered are valid
    public boolean isValidPosition(int x, int y) {
        if(x < 0 || y < 0)
            return false;
        if(x > sizeX - 1 || y > sizeY - 1)
            return false;

        return true;
    }

    //completely clear grid
    public void clear() {
        for(int y = 0; y < sizeY; y++) {
            for(int x = 0; x < sizeX; x++) {
                locations[x][y].reset();
            }
        }
    }

    //update locations based on where the players are
    public void addPlayers(Player[] players) {
        for(int i = 0; i < players.length; i++) {
            Player p = players[i];
            locations[p.x][p.y].addPlayer();
        }
    }

    //draw the grid
    public void drawGrid() {
        for(int y = 0; y < sizeY; y++) {
            for(int x = 0; x < sizeX; x++) {

                int playersAtCoordinate = locations[x][y].numPlayers;

                if(playersAtCoordinate == 0)
                    buttons[x][y].setIcon(woodsButtons);
                if(playersAtCoordinate == 1)
                    buttons[x][y].setIcon(playerIcon);
                if(playersAtCoordinate == 2)
                    buttons[x][y].setIcon(twoPlayersIcon);
                if(playersAtCoordinate == 3)
                    buttons[x][y].setIcon(threePlayersIcon);
                if(playersAtCoordinate == 4)
                    buttons[x][y].setIcon(fourPlayersIcon);
            }
        }
    }
}
