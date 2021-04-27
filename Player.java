public class Player {
    public String name;
    int x, y;

    public Player(String name){ 
        this.name = name;
    }

    public void SetLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
