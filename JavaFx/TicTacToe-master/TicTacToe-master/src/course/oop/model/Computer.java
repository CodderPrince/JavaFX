package course.oop.model;

public class Computer extends Player {

    Computer() {
        this("Bot #" + (int) (Math.random() * 1000));
    }

    private Computer(String username) {
        super(username, 39);
    }

    @Override
    public boolean isHuman() {
        return false;
    }

    @Override
    public boolean isComputer(){ return true; }

    @Override
    public String getMarker(){return "39";}


}
