public class Game68 extends Game {
    
    //Game class for grades 6 through 8
    public Game68(String[] playerNames, String windowName, int boardSizeX, int boardSizeY){
        super(playerNames, windowName, boardSizeX, boardSizeY);
        for(int i = 0; i < players.length; i++)
            players[i].setLocation((boardSizeX - 1) * (i % 2), (boardSizeY - 1) * (i == 1 || i == 2 ? 1 : 0));

        updateGrid();
        startGame();
    }

    protected void gameUpdate() {
        super.gameUpdate();
    }
}