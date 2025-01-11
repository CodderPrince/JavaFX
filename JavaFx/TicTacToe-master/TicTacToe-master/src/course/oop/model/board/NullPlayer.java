package course.oop.model.board;

import course.oop.model.Player;

public class NullPlayer extends Player {
    public NullPlayer() {
        super("null", -1);
    }

    @Override
    public boolean isHuman(){
        return false;
    }

    @Override
    public boolean isComputer(){
        return false;
    }

}
