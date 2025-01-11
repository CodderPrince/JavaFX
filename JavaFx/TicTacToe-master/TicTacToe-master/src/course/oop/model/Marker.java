package course.oop.model;

public class Marker {

    private final String marker;
    private final int teamNumber;

    //used only ingame. Purpose is to mark, not to store the marker.
    Marker(int teamNumber, String marker) {
        this.marker = marker;
        this.teamNumber = teamNumber;
    }

    public int getTeamNumber() {
        return teamNumber;
    }

    public String getMark() {
        return this.marker;
    }

    public static Marker BLOCKED = new Marker(-1, "clown");

}