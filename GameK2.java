public class GameK2 extends Game {
    
    //Game class for grades K through 2
    public GameK2(String[] playerNames, String windowName, int boardSizeX, int boardSizeY){
        super(playerNames, windowName, boardSizeX, boardSizeY);
        players[0].setLocation(0, 0);
        players[1].setLocation(boardSizeX - 1, boardSizeY - 1);

        updateGrid();
        startGame();
    }

    protected void gameUpdate() {
        super.gameUpdate();
    }

    protected boolean checkForWin() {
        if(players[0].x == players[1].x)
            if(players[0].y == players[1].y)
                return true;
        return false;
    }
}