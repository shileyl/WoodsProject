import java.util.Random;

public class Player {
    public String name;
    public int x, y;
    int moves = 0;
    int rand1, rand2;

    public Player(String name){ 
        this.name = name;
    }

    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move(Random r, Grid g) {
        do {
            generateRandomNumbers(r);
        } 
        while(!g.isValidPosition(x + rand1, y + rand2));

        x += rand1;
        y += rand2;
    }

    void generateRandomNumbers(Random r) {
        rand1 = rand2 = 0;

        while(rand1 == 0 && rand2 == 0) {  //keep randomizing until at least one of the ints is not zero

            //set rand1 and rand2 to random ints that are -1, 0 or 1
            rand1 = r.nextInt(3) - 1;
            rand2 = r.nextInt(3) - 1;
        }
    }
}
