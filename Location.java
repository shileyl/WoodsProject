import javax.swing.ImageIcon;

//Location class keeps track of how many players are at a grid location

public class Location {
    public int numPlayers = 0;
    public ImageIcon image;

    public void addPlayer(Player p) {
        numPlayers++;
        image = p.image;
    }

    public void reset() {
        numPlayers = 0;
    }
}