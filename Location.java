public class Location {
    public int numPlayers = 0;

    public void addPlayer() {
        numPlayers++;
    }

    public void reset() {
        numPlayers = 0;
    }
}