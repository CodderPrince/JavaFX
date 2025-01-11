package course.oop.model.board;


import course.oop.model.Game;
import course.oop.model.Marker;
import course.oop.util.Utilities;

import static course.oop.model.Game.NO_WINNER;

public class Tile {

    public static final int numDirections = 8;
    public static final int U       = 0;
    public static final int UR      = 1;
    public static final int R       = 2;
    public static final int DR      = 3;
    public static final int D       = 4;
    public static final int DL      = 5;
    public static final int L       = 6;
    public static final int UL      = 7;
    public static int reverse(int direction) {return (direction + numDirections / 2)%numDirections;}

    private Marker marker;

    private final Tile[] neighborhood = new Tile[numDirections];

    private TileProperty tileProperty;

    public Tile(boolean properties){
        TileProperty tileProperty1 = null;
        if (properties) {
            int random = (int) (random() * 100);

            if (0 <= random && random < 50) tileProperty1 = new NoProperty();
            if (50 <= random && random < 55) tileProperty1 = new EraseProperty();
            if (55 <= random && random < 60) tileProperty1 = new SpinProperty();
            if (60 <= random && random < 70) tileProperty1 = new BlockProperty();
            if (70 <= random && random < 75) tileProperty1 = new ReboundProperty();
            if (75 <= random && random < 80) tileProperty1 = new ClearBoardProperty();
            if (80 <= random && random < 100) tileProperty1 = new NoProperty();
        }
        else tileProperty1 = new NoProperty();
        this.tileProperty = tileProperty1;
    }

    public int getOccupantId() {
        if (!isEmpty()) return marker.getTeamNumber(); else return -1;
    }

    public Tile placeMarker(Marker m) {
        System.err.println("Placing Marker!!!!!!");
        if (!this.isEmpty()) {
            System.out.println(Utilities.ANSI_RED + "Tile already occupied!" + Utilities.ANSI_RESET);
            return null;
        }
        this.marker = m;
        return this;
    }


    public boolean hasAvailableTiles(){
        return isEmpty();
    }



    public boolean isEmpty() {
        return marker == null;
    }

    public void biconnect(int direction, Tile t){
        this.neighborhood[direction] = t;
        t.neighborhood[reverse(direction)] = this;
    }

    public Tile getNeighbor(int direction){
        if (direction < 0 || direction >= numDirections) return null;
        return this.neighborhood[direction];
    }

    public String getMark(){
        if (this.marker == null) return "";
        return this.marker.getMark();
    }
    public void clearMarker(){
        this.marker = null;
    }

    public String triggerProperty(Game g){
        String s = this.tileProperty.applyEffect(g, this);
        this.tileProperty = new NoProperty();
        return s;
    }

    // random double between 0 and 1
    private static int seed = 1000;
    private double random() {
        double x = Math.sin(seed++) * 10000;
        return x - Math.floor(x);
    }
}