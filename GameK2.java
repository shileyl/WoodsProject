public class GameK2 extends Game {
    
    //Game class for grades K through 2
    public GameK2(String[] playerNames, String windowName, int boardSizeX, int boardSizeY){
        super(playerNames, windowName, boardSizeX, boardSizeY);
        for(int i = 0; i < players.length; i++)
            players[i].setLocation((boardSizeX - 1) * (i % 2), (boardSizeY - 1) * (i == 1 || i == 2 ? 1 : 0));
        startGame();
    }

    protected void gameUpdate() {
        super.gameUpdate();
    }
}