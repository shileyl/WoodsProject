import java.awt.Font;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.util.Hashtable;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


//Grid class is responsible for managing the Java Swing variables and anything related to displaying the game information


public class Grid {
    
    private Location[][] locations;
    private Button[][] buttons;
    private JFrame frame;
    private JPanel panel;
    private ImageIcon twoPlayersIcon;
    private ImageIcon threePlayersIcon;
    private ImageIcon fourPlayersIcon;
    private ImageIcon woodsButtons; 
    
    private JLabel speedSliderLabel;
    private JSlider speedSlider;

    int sizeX, sizeY;
    int mouseX, mouseY;
    double buttonSize;
    private JLabel[] pStats;

    public Grid(String windowName, int sizeX, int sizeY) {  //Constructor for grid
        this.sizeX = sizeX;
        this.sizeY = sizeY;

        createWindow(windowName);
        initializeGrid();
        createIcons();
        createSpeedSlider();
    }

    void createWindow(String windowName) {
        frame = new JFrame();   //Create the window
        frame.setTitle(windowName);

        panel = new JPanel();   //Create a panel
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);  //Make it visable
        frame.add(panel);  //Link the 2
        panel.setLayout(null);
        frame.setResizable(true);

        frame.addWindowStateListener(new WindowStateListener(){
            public void windowStateChanged(WindowEvent e) {
                System.out.println(frame.getSize().width + " " + frame.getSize().width);
            }
            
        });
        
        frame.setSize(WoodsSimulationMenu.sizeX, WoodsSimulationMenu.sizeY);//this needs to be at the end 
    }

    void initializeGrid() {
        locations = new Location[sizeX][sizeY];
        buttons = new Button[sizeX][sizeY];

        //some suspicious math to calculate grid size
        double offsetSize = 0.04;
        double offsetX, offsetY;
        double boardSizeX = 0.8 * WoodsSimulationMenu.sizeX;
        double boardSizeY = WoodsSimulationMenu.sizeY;
        double ratio = (double) WoodsSimulationMenu.sizeY / WoodsSimulationMenu.sizeX;

        if((1.0/0.8 * ratio * sizeX) > sizeY) {
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
                buttons[x][y] = new Button(x, y);
                
                buttons[x][y].setVisible(true);
                buttons[x][y].setBounds((int)(offsetX + buttonSize * x), (int)(offsetY + buttonSize * y),
                 (int)buttonSize, (int)buttonSize);
                panel.add(buttons[x][y]);
            }
        }
    }

    void createIcons() {
        twoPlayersIcon = Util.createImageIcon(this, "Assets/2boys.png","this is a 2 boys");
        twoPlayersIcon = Util.scaleImageIcon(twoPlayersIcon, buttonSize);
        threePlayersIcon = Util.createImageIcon(this, "Assets/3boys.png","this is 3 boys");
        threePlayersIcon = Util.scaleImageIcon(threePlayersIcon, buttonSize);
        fourPlayersIcon = Util.createImageIcon(this, "Assets/4boys.png","this is 4 boys");
        fourPlayersIcon = Util.scaleImageIcon(fourPlayersIcon, buttonSize);
        woodsButtons = Util.createImageIcon(this, "Assets/Forest2.jpg","this is the woods");
        woodsButtons = Util.scaleImageIcon(woodsButtons, buttonSize);
    }

    public void makeStatsSection(Player[] players) {
        int fontSize = 20;
        Font f = new Font("Verdana", Font.PLAIN, fontSize);  //font object

        if(pStats == null)
            pStats = new JLabel[players.length * (Player.numStats + 1)];  //an array of JLabels

        int y = (int) (WoodsSimulationMenu.sizeY * 0.1);   //initial y position
        int x = (int)(WoodsSimulationMenu.sizeX * 0.8);  //offset position along x axis
        int width = (int) (WoodsSimulationMenu.sizeX * 0.2);   //text width
        int height = (int) (fontSize * 1.35);    //text height

        for(int i = 0; i < players.length; i++) {  //Loop through each player
            int index = i * (Player.numStats + 1);
            String text = players[i].name;    
            createNewTextLabel(index, f, x, y, width , height, text);   //Create a label for their name
            y += height;
            
            for(int k = 0; k < Player.numStats; k++) {    //loop through each of their stats
                text = players[i].statNames[k] + ": " + players[i].statValues[k];  
                createNewTextLabel(index + k + 1, f, x, y, width , height, text);   //Create a label for their name
                y += height;
            }
        }
        
    }

    void createNewTextLabel(int index, Font f, int x, int y, int w, int h, String text) {
        if(pStats[index] != null) {
            pStats[index].setText(text);
            return;
        }
        pStats[index] = new JLabel(text);
        pStats[index].setFont(f);
        pStats[index].setHorizontalAlignment(JTextField.CENTER);

        pStats[index].setBounds(x, y, w, h);
        panel.add(pStats[index]);
        pStats[index].setVisible(true);
    }

    void createSpeedSlider() {
        int width = (int)(WoodsSimulationMenu.sizeX * 0.2 * 0.75);
        int height = 50;
        
        int xPos = (int)(WoodsSimulationMenu.sizeX * 0.8 + ((WoodsSimulationMenu.sizeX * 0.2) - width)/2.0);
        int yPos = WoodsSimulationMenu.sizeY - (int)(WoodsSimulationMenu.sizeY * 0.2);
        
        speedSliderLabel = new JLabel("Game Speed");
        speedSliderLabel.setBounds(xPos + 5, yPos - 20, width - 5, 20);

        speedSlider = new JSlider(JSlider.HORIZONTAL, 0, 20, 2);
        speedSlider.setBounds(xPos, yPos, width, height);

        speedSlider.setMinorTickSpacing(1);  //add little ticks on slider
        speedSlider.setPaintTicks(true);
        speedSlider.setPaintLabels(true);

        Hashtable<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();
        labelTable.put( 0, new JLabel("0") );    //add labels at different locations on the slider
        labelTable.put( 2, new JLabel("1x") );
        labelTable.put( 10, new JLabel("2x") );
        labelTable.put( 20, new JLabel("3x") );
        speedSlider.setLabelTable( labelTable );

        speedSlider.addChangeListener(new ChangeListener(){
            public void stateChanged(ChangeEvent e) {
                sliderValueChanged();
            }
        });

        speedSlider.setVisible(true);
        speedSliderLabel.setVisible(true);
        panel.add(speedSlider);
        panel.add(speedSliderLabel);
    }

    void sliderValueChanged() {
        float newSpeed = speedSlider.getValue() / 2.0f;
        Game.instance.setGameSpeed(newSpeed);
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
            locations[p.x][p.y].addPlayer(p);  //increase the number of players at this location by 1
        }
    }

    //place a player at a position x, y
    public void updateGrid(int x, int y, Player p) {
        locations[x][y].addPlayer(p);  //increase the number of players at this location by 1
        drawCoordinate(x, y);          //add the icon at the players location
    }

    //draw the grid
    public void drawGrid() {
        for(int y = 0; y < sizeY; y++) {
            for(int x = 0; x < sizeX; x++) {   //loop through grid coordinates
                drawCoordinate(x, y);
            }
        }
    }

    void drawCoordinate(int x, int y) {
        int playersAtCoordinate = locations[x][y].numPlayers;

        //set the proper image icon based on the amount of players at this location
        if(playersAtCoordinate == 0)
            buttons[x][y].setIcon(woodsButtons);
        if(playersAtCoordinate == 1)
            buttons[x][y].setIcon(Util.scaleImageIcon(locations[x][y].image, buttonSize));
        if(playersAtCoordinate == 2)
            buttons[x][y].setIcon(twoPlayersIcon);
        if(playersAtCoordinate == 3)
            buttons[x][y].setIcon(threePlayersIcon);
        if(playersAtCoordinate == 4)
            buttons[x][y].setIcon(fourPlayersIcon);
    }

    void terminateFrame() {
        frame.dispose();//closes

    }
}
