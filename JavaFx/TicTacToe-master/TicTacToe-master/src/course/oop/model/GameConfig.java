package course.oop.model;

import course.oop.fileio.FileIO;
import course.oop.util.Utilities;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class GameConfig {

    enum Config {PLAYERS, TIMEOUT, PLAYERS_INITIALIZED, PROPERTIES, ULTIMATE, THREE_DIMENSIONAL, N}

    private final int MIN_PLAYERS = 1;
    private final int MAX_PLAYERS = 2;

    String status;
    int n = 3;
    boolean properties = false;
    boolean ultimate = false;
    boolean three_dimensional = false;
    private int numPlayers;
    private long timeoutSeconds;
    private final List<Player>[] teams;
    // mapping from playerId to player
    private HashMap<Integer, Player> players = new HashMap<>();
    private final boolean[] configs;

    public GameConfig() {
        teams = new LinkedList[2];
        teams[0] = new LinkedList<>();
        teams[1] = new LinkedList<>();
        configs = new boolean[Config.values().length];
        configs[Config.TIMEOUT.ordinal()] = true;
    }

    public void setAttribute(String attribute, String value) {
        Config c;
        try {
            c = Config.valueOf(attribute.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println(Utilities.ANSI_RED + "Unknown attribute: " + attribute + Utilities.ANSI_RESET);
            return;
        }
        switch (c) {
            case TIMEOUT: {
                int timeout = Utilities.parseIntValue(value);
                this.setTimeout(timeout);
                return;
            }

            case PROPERTIES: {
                switch (value){
                    case "on": {
                        this.properties = true;
                        break;
                    }
                    case "off": {
                        this.properties = false;
                        break;
                    }
                }
                return;
            }

            case THREE_DIMENSIONAL: {
                switch (value){
                    case "on": {
                        this.three_dimensional = true;
                        break;
                    }
                    case "off": {
                        this.three_dimensional = false;
                        break;
                    }
                }
                return;
            }
            case ULTIMATE: {
                switch (value){
                    case "on": {
                        this.ultimate = true;
                        break;
                    }
                    case "off": {
                        this.ultimate = false;
                        break;
                    }
                }
                return;
            }
            case N: {
                this.n = Utilities.parseIntValue(value);
            }

            default: {
            }
        }
    }

    boolean properties(){
       return properties;
    }
    String getStatus(){
        return status;
    }
    public void setStatus(String status){
        this.status = status;
    }

    private void setTimeout(int timeout) {
        int config = Config.TIMEOUT.ordinal();
        this.timeoutSeconds = timeout;
        if (this.timeoutSeconds < 0)
            System.out.println(Utilities.ANSI_RED + "Invalid timeout: " + this.timeoutSeconds + "s" + Utilities.ANSI_RESET);
        configs[config] = (0 <= this.timeoutSeconds);
    }

    // TODO Don't call until all players are valid
    public void createPlayer(String username, String markerIndex, String team) {

        int markerIndexInt = Integer.parseInt(markerIndex);
        Player p = FileIO.loadPlayer(username);

        // Update Player marker
        p.updateMarkerIndex(markerIndexInt);
        FileIO.writePlayer(p);

        System.out.println("GameConfig.java: " + p.asEntry());

        int teamNumber = Utilities.parseIntValue(team);
        List<Player> teamList = teams[teamNumber - 1];
        teamList.add(p);
    }

    public int getNumPlayers() {
        return players.size();
    }

    public Player getPlayer(int team, int player) {
        System.out.println(players);
        /*
        if (index < 0 || index >= players.size()) {
            return null;
        }
         */
        return teams[team].get(player);
    }

    public long getTimeout() {
        return this.timeoutSeconds;
    }

    public void createComputer(String team) {
        int teamNumber = Utilities.parseIntValue(team);
        List<Player> teamList = teams[teamNumber-1];
        Computer c = new Computer();
        teamList.add(c);
    }

    public int randomTeam(){
        return (int) (Math.random() * (teams.length - 1));
    }

    public int randomPlayer(int teamNumber){
       return (int) (Math.random() * (teams[teamNumber-1].size() - 1)) ;
    }

    public int[] updateTeam(int team, int[] teamPlayer){
        teamPlayer[team] = (teamPlayer[team] + 1) % teams[team].size();
        return teamPlayer;
    }
    public List<Player> getTeam(int team){
        return teams[team];
    }
    public List<Player>[] getTeams(){
        return teams;
    }

}
