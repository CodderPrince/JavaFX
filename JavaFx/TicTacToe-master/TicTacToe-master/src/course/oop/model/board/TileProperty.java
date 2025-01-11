package course.oop.model.board;

import course.oop.model.Game;
import course.oop.model.Marker;

public interface TileProperty {
    String applyEffect(Game g, Tile t);
}

class NoProperty implements TileProperty {
    @Override
    public String applyEffect (Game g, Tile t){
        return "";
    }
}

class ClearBoardProperty implements TileProperty {
   @Override
    public String applyEffect (Game g, Tile t) {
       g.clearBoard();
       return "Oops we lost your board! :)";
   }
}

class EraseProperty implements TileProperty {
    @Override
    public String applyEffect (Game g, Tile t) {
        t.clearMarker();
        return "You failed to place a tile.";
    }
}

// Makes the game spin indefinitely
class SpinProperty implements TileProperty {
    @Override
    public String applyEffect (Game g, Tile t){
        g.spin();
        return "Spin out";
    }
}

class ReboundProperty implements TileProperty {
    @Override
    public String applyEffect(Game g, Tile t){
        g.rebound();
        return "Whoooo";
    }
}

class BlockProperty implements TileProperty {
    @Override
    public String applyEffect(Game g, Tile t){
        t.clearMarker();
        t.placeMarker(Marker.BLOCKED);
        return "Tile blocked!";
    }
}

