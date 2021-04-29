import java.util.Random;
import javax.swing.*;
import java.awt.event.*;

public class Game {

    private Player[] players;
    private Grid grid;

    boolean gameOver = false;
    Random rand;
    Timer timer;

    public Game(String[] playerNames, String windowName, int boardSizeX, int boardSizeY){
        rand = new Random();
        grid = new Grid(windowName, boardSizeX, boardSizeY);
        initializePlayers(playerNames);
        players[0].setLocation(0, 0);
        players[1].setLocation(boardSizeX - 1, boardSizeY - 1);
        updateGrid();
        

        startGame();
    }

    void initializePlayers(String[] playerNames) {
        players = new Player[playerNames.length];

        for(int i = 0; i < players.length; i++) {
            players[i] = new Player(playerNames[i]);
        }
    }

    //starts the game update loop
    void startGame() {
        //create game update loop
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                gameUpdate();
            }
        };

        //Create a timer object that calls actionPerformed on the ActionListener every 1000 milliseconds
        timer = new Timer(1000 ,taskPerformer);
        timer.start();

        //play music
    }

    void gameUpdate() {
        //move players randomly
        for(int i = 0; i < players.length; i++) {
            players[i].move(rand, grid);
        }

        //tell the grid to update
        updateGrid();
    }
    
    void updateGrid() {
        grid.clear();
        grid.addPlayers(players);
        grid.drawGrid();
    }
}