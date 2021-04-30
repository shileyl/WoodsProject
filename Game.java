import java.util.Random;
import javax.swing.*;
import java.awt.event.*;

public class Game {

    protected Player[] players;
    protected Grid grid;

    protected boolean gameOver = false;
    protected Random rand;
    protected Timer timer;

    protected int numPlayers;
    protected int numPlayersPlaced = 0;
    public boolean allPlayersPlaced = false;

    public static Game instance;

    public Game(String[] playerNames, String windowName, int boardSizeX, int boardSizeY){
        rand = new Random();
        grid = new Grid(windowName, boardSizeX, boardSizeY);

        instance = this;

        //the amount of players
        numPlayers = playerNames.length;
        initializePlayers(playerNames);
    }

    protected void initializePlayers(String[] playerNames) {
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
                gameUpdate();
            }
        };

        //Create a timer object that calls actionPerformed on the ActionListener every 1000 milliseconds
        timer = new Timer(1000, taskPerformer);
        timer.start();

        //play music
    }

    protected void gameUpdate() {
        if(checkForWin()) {
            //cancel game loop and return
            timer.stop();

            //need to make some sort of happy graphic display here
            return;
        }
        //move players randomly
        for(int i = 0; i < players.length; i++) {
            players[i].move(rand, grid);
        }

        //tell the grid to update
        updateGrid();
    }
    
    protected void updateGrid() {
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