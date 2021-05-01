import java.util.Random;
import javax.swing.*;
import java.awt.event.*;

public class Game {
    //field
    protected Player[] players;
    protected Grid grid;
    protected boolean gameOver = false;
    protected Random rand;
    protected Timer timer;
    protected int numPlayers;
    protected int numPlayersPlaced = 0;
    public boolean allPlayersPlaced = false;
    protected int gameID;
    protected String [] playerNames;
    protected String windowName;
    protected int boardSizeX;
    protected int boardSizeY;

    protected int frame = 0;
    protected int timerMilliDelay;
    protected int timerMilliDefault = 50;

    public static Game instance;

    public Game(String[] playerNames, String windowName, int boardSizeX, int boardSizeY){
        rand = new Random();
        grid = new Grid(windowName, boardSizeX, boardSizeY);
        this.playerNames = playerNames;
        this.windowName = windowName;
        this.boardSizeX = boardSizeX;
        this.boardSizeY = boardSizeY;

        instance = this;
        timerMilliDelay = timerMilliDefault;

        //the amount of players
        numPlayers = playerNames.length;
        initializePlayers(playerNames);
        gameID = 0;
    }

    protected void initializePlayers(String[] playerNames) {//preping the data
        players = new Player[playerNames.length];

        for(int i = 0; i < players.length; i++) {
            players[i] = new Player(playerNames[i], i+1);
        }
    }

    //starts the game update loop
    protected void startGame() {
        //create game update loop
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if(frame % timerMilliDelay == 0)
                    gameUpdate();
                frame++;
            }
        };

        //Create a timer object that calls actionPerformed on the ActionListener every 1000 milliseconds
        timer = new Timer(1, taskPerformer);
        timer.start();

        //play music
    }

    protected void gameUpdate() {// keeps the game up to date called every frame
        if(checkForWin()) {
            //cancel game loop and return
            timer.stop();

            //need to make some sort of happy graphic display here
            return;
        }
        //move players randomly
        for(int i = 0; i < players.length; i++) {
            players[i].move(rand, grid, players);
        }

        //tell the grid to update
        updateGrid();
    }

    public void setGameSpeed(float newSpeed) {//for the slider changeing the speed of the simulation
        if(newSpeed < 0.1) {
            timerMilliDelay = Integer.MAX_VALUE;
            return;
        }

        timerMilliDelay = (int) (timerMilliDefault / newSpeed);
    }
    
    protected void updateGrid() {//updates the grid every frame
        //execute functions on grid class to update display
        grid.clear();
        grid.addPlayers(players);
        grid.drawGrid();
        grid.makeStatsSection(players);
    }

    protected void updateGrid(int x, int y, Player p) {
        grid.updateGrid(x, y, p);
    }

    protected boolean checkForWin() {
        return false; //logic overriden in parent classes
    }

    //called when a player is added to the grid
    protected void playerPlaced(int x, int y) {}
}