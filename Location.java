import javax.swing.ImageIcon;

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