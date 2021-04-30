public class Game68 extends Game {

    protected int [] x;
    protected int [] y;
    
    //Game class for grades 6 through 8
    public Game68(String[] playerNames, String windowName, int boardSizeX, int boardSizeY){
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

    protected void gameUpdate() {
        super.gameUpdate();
    }

    protected boolean checkForWin() {
        for(int i =0;i<players.length;i++){
            if((players[0].x != players[i].x) || (players[0].y != players[i].y)){
                return false;
            }
        }
        new GameOver();
        return true;

    }

}