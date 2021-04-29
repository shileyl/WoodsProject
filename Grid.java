import java.awt.Image;
import java.awt.Font;
import java.awt.Dimension;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
    double buttonSize;

    private JLabel[] pStats;

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

        //some suspicious math to calculate grid size
        double offsetSize = 0.04;
        double offsetX, offsetY;
        double boardSizeX = 0.8 * WoodsSimulationMenu.sizeX;
        double boardSizeY = WoodsSimulationMenu.sizeY;

        if((1.0/0.8 * sizeX) > sizeY) {
            offsetX = 2.0 * offsetSize * boardSizeX;
            buttonSize = (boardSizeX - offsetX) / sizeX;
            offsetY = (boardSizeY - (buttonSize * sizeY)) / 2.0;
        } else {
            offsetY = offsetSize * boardSizeY;
            buttonSize = (boardSizeY - (2.0 * offsetY)) / sizeY;
            offsetX = (boardSizeX - (buttonSize * sizeX)) / 2.0;
        }

        //Loop through x and y coordinates
        for(int y = 0; y < sizeY; y++) {
            for(int x = 0; x < sizeX; x++) {
                locations[x][y] = new Location();   //Create a new Location and Button object
                buttons[x][y] = new JButton(woodsButtons);
                
                buttons[x][y].setVisible(true);
                buttons[x][y].setBounds((int)(offsetX + buttonSize * x), (int)(offsetY + buttonSize * y),
                 (int)buttonSize, (int)buttonSize);

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
        //get the url of an image
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            //create an image icon object if the image file was found
            ImageIcon ii = new ImageIcon(imgURL, description);
            //rescale the image to the button size
            Image newImg = ii.getImage().getScaledInstance((int)buttonSize, (int)buttonSize, java.awt.Image.SCALE_SMOOTH);
            ii = new ImageIcon(newImg); //re-initialize the image icon object with the newly scaled image
            return ii;
        } else {
            //print out an error if the file could not be found
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    public void makeStatsSection(Player[] players) {
        int fontSize = 14;
        pStats = new JLabel[players.length];  //an array of JLabels
        Font f = new Font("Verdana", Font.PLAIN, fontSize);  //font object

        int y = (int) (WoodsSimulationMenu.sizeY * 0.1);   //initial y position
        double offsetX = WoodsSimulationMenu.sizeX * 0.8;  //offset position along x axis
        double widthX = WoodsSimulationMenu.sizeX * 0.2;   //the amount of space to work with on x axis

        for(int i = 0; i < players.length; i++) {
            String text = players[i].name;
            pStats[i] = new JLabel(text);
            pStats[i].setFont(f);

            int width = fontSize * text.length();
            int x = (int) (offsetX + (widthX - width)/2.0);
            int height = (int) (fontSize * 1.35);

            pStats[i].setBounds(x, y, width, height);
            panel.add(pStats[i]);
            y += height;
            
            pStats[i].setVisible(true);
        }
    }

    //check to see if the x and y coordinates entered are valid
    public boolean isValidPosition(int x, int y) {
        if(x < 0 || y < 0)  //make sure they are both positive
            return false;
        if(x > sizeX - 1 || y > sizeY - 1)  //make sure they are not beyond max x and y coordinates
            return false;

        return true;
    }

    //completely clear grid
    public void clear() {
        for(int y = 0; y < sizeY; y++) {
            for(int x = 0; x < sizeX; x++) {  //loop through all the locations
                locations[x][y].reset();  //sets the number of players at this location to 0
            }
        }
    }

    //update locations based on where the players are
    public void addPlayers(Player[] players) {
        for(int i = 0; i < players.length; i++) {
            Player p = players[i];
            locations[p.x][p.y].addPlayer();  //increase the number of players at this location by 1
        }
    }

    //draw the grid
    public void drawGrid() {
        for(int y = 0; y < sizeY; y++) {
            for(int x = 0; x < sizeX; x++) {   //loop through grid coordinates

                int playersAtCoordinate = locations[x][y].numPlayers;

                //set the proper image icon based on the amount of players at this location
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
