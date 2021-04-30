public class Game35 extends Game {
    
    protected int [] x;
    protected int [] y;

    //Game class for grades 2 through 5
    public Game35(String[] playerNames, String windowName, int boardSizeX, int boardSizeY){
        super(playerNames, windowName, boardSizeX, boardSizeY);
    }

    //called when a player was placed on the grid
    protected void playerPlaced(int x, int y) {
        if(allPlayersPlaced)
            return;
        
        updateGrid(x, y, players[numPlayersPlaced]);
        players[numPlayersPlaced].x = x;
        players[numPlayersPlaced].y = y;
        numPlayersPlaced++;

        //start the game when all the players are placed
        allPlayersPlaced = numPlayersPlaced == numPlayers;
        if(allPlayersPlaced) {
            updateGrid();
            startGame();
        }
    }

    protected boolean checkForWin() {
        for(int i =0;i<players.length;i++){
            if((players[0].x != players[i].x) || (players[0].y != players[i].y)){
                return false;
            }
        }
        grid.terminateFrame();
        new GameOver();
        return true;

    }

}