import java.util.Random;

import javax.swing.ImageIcon;

public class Player {
    public String name;
    public ImageIcon image;
    public int x, y;

    private int moves = 0;
    private int rand1, rand2;

    //A string array with a list of the names of the different stats to be displayed
    String[] statNames = {"Moves"}; 
    //A string array with the values of the stats cast to strings
    String[] statValues;
    //length of the above arrays
    public static int numStats = 1; 

    public Player(String name, int id){ 
        //set name and initialize stat values array
        this.name = name;
        this.image = Util.createImageIcon(this, "Assets/boy" + id + ".png", "This is a boy");
        statValues = new String[statNames.length];
    }

    public void setLocation(int x, int y) {
        //set the location of this player
        this.x = x;
        this.y = y;
    }

    public void move(Random r, Grid g) {
        do {
            generateRandomNumbers(r);  //generate random x and y values to move
        } 
        while(!g.isValidPosition(x + rand1, y + rand2));   //check if those values result in valid grid coordinates

        //update the x and y coordinates along with the amount of moves
        x += rand1;
        y += rand2;
        moves++;
    }

    void generateRandomNumbers(Random r) {
        rand1 = rand2 = 0;

        while(rand1 == 0 && rand2 == 0) {  //keep randomizing until at least one of the ints is not zero

            //set rand1 and rand2 to random ints that are -1, 0 or 1
            rand1 = r.nextInt(3) - 1;
            rand2 = r.nextInt(3) - 1;
        }
    }

    void updateStats() {
        statValues[0] = "" + moves;
    }
}
