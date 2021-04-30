import java.util.Random;

import javax.swing.ImageIcon;

//Player class keeps track of position and stats for each player

public class Player {
    public String name;
    public ImageIcon image;
    public int x, y;
    public int rand1, rand2;

    private int id = 0;
    private int moves = 0;
    private int movementProcedure = 0;

    //A string array with a list of the names of the different stats to be displayed
    String[] statNames = {"Moves"}; 
    //A string array with the values of the stats cast to strings
    String[] statValues;
    //length of the above arrays
    public static int numStats = 1; 

    public Player(String name, int id){ 
        //set name and initialize stat values array
        this.id = id;
        this.name = name;
        this.image = Util.createImageIcon(this, "Assets/boy" + id + ".png", "This is a boy");
        statValues = new String[statNames.length];
        updateStats();

        movementProcedure = WoodsSimulationMenu.movementProcedure;
    }

    public void setLocation(int x, int y) {
        //set the location of this player
        this.x = x;
        this.y = y;
    }

    public void move(Random r, Grid g, Player[] players) {
        do {
            pickDirectionToMove(r, players);
        } 
        while(!g.isValidPosition(x + rand1, y + rand2));   //check if those values result in valid grid coordinates

        //update the x and y coordinates along with the amount of moves
        x += rand1;
        y += rand2;

        if(rand1 == rand2 && rand1 == 0) {
            updateStats();
            return;
        }

        moves++;
        updateStats();
    }

    void pickDirectionToMove(Random r, Player[] players) {
        switch(movementProcedure) {
            case 0:
                movementProcedure0(r);  //Movement procedure 0
                return;
            case 1:
                movementProcedure1(r);  //Movement procedure 1
                return;
            case 2:
                movementProcedure2(r, players);  //Movement procedure 2
                return;
        }
    }

    void movementProcedure0(Random r) {  //generate a random x and y to move, players must move each turn
        rand1 = rand2 = 0;

        while(rand1 == 0 && rand2 == 0) {  //keep randomizing until at least one of the ints is not zero

            //set rand1 and rand2 to random ints that are -1, 0 or 1
            rand1 = r.nextInt(3) - 1;
            rand2 = r.nextInt(3) - 1;
        }
    }

    void movementProcedure1(Random r) {  //generate a random x and y to move, players dont have to move each turn
        rand1 = rand2 = 0;

        //set rand1 and rand2 to random ints that are -1, 0 or 1
        rand1 = r.nextInt(3) - 1;
        rand2 = r.nextInt(3) - 1;
    }

    void movementProcedure2(Random r, Player[] players) {  
        //generate a random x and y to move, players dont have to move each turn, 
        //players will also try to stick together
        rand1 = rand2 = 0;

        for(int i = 0; i < id; i++) {
            Player p = players[i];
            if(p.x - p.rand1 == x && p.y - p.rand2 == y) {
                //if we are in this players last location, then move with them
                rand1 = p.rand1;
                rand2 = p.rand2;
                return;
            }
        }

        //set rand1 and rand2 to random ints that are -1, 0 or 1
        rand1 = r.nextInt(3) - 1;
        rand2 = r.nextInt(3) - 1;
    }

    void updateStats() {
        statValues[0] = "" + moves;
    }
}
