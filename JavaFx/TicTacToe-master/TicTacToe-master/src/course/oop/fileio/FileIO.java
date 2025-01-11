package course.oop.fileio;

import course.oop.model.Player;

import java.io.*;
import java.util.HashMap;

public class FileIO {

    private static void writeHashMap(HashMap<String, Player> map) {
        try {
            FileOutputStream fos = new FileOutputStream("players.ttt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(map);

            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void init() {
        try {
            FileOutputStream fos = new FileOutputStream("players.ttt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            HashMap<String, Player> players = new HashMap<>();
            players.put("monkey", new Player("monkey", 0));
            players.put("cooldude", new Player("cooldude", 5));
            players.get("monkey").awardCash();

            oos.writeObject(players);

            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void writePlayer(Player p) {
        HashMap<String, Player> players = loadHashMap();
        players.put(p.toString(), p);
        writeHashMap(players);
    }

    public static Player loadPlayer(String username) {
        HashMap<String, Player> map = loadHashMap();
        return map.getOrDefault(username, new Player(username));
    }

    public static HashMap<String, Player> loadHashMap() {
        HashMap<String, Player> map = null;
        try {
            FileInputStream fis = new FileInputStream("players.ttt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            map = (HashMap<String, Player>) ois.readObject();

            ois.close();
            fis.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (map == null) System.out.println("Failed to load map!");
        else System.out.println("Loaded map!");
        return map;
    }
}
