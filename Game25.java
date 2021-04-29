public class Game25 extends Game {
    
    //Game class for grades 2 through 5
    public Game25(String[] playerNames, String windowName, int boardSizeX, int boardSizeY){
        super(playerNames, windowName, boardSizeX, boardSizeY);
        players[0].setLocation(0, 0);
        players[1].setLocation(boardSizeX - 1, boardSizeY - 1);
        startGame();
    }

    protected void gameUpdate() {
        super.gameUpdate();
    }
}