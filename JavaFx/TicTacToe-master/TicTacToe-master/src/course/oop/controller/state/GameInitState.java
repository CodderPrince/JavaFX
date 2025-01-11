package course.oop.controller.state;

import course.oop.model.Game;
import course.oop.model.GameConfig;
import course.oop.util.Utilities;
import course.oop.view.CommandCall;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class GameInitState implements GameState {

    private final GameConfig gameConfig;

    public GameInitState(GameConfig gameConfig) {
        this.gameConfig = gameConfig;
    }

    public Game produceGame() {
        Game g = new Game(gameConfig);
        g.clearEffects();
        return g;
    }

    @Override
    public GameState consumeCommand(CommandCall c) {
        return null;
    }

    @Override
    public String getPrompt() {
        return "Game initialization";
    }

    @Override
    public void printInitialText() {
        System.out.println(Utilities.HELP_START + "Initializing game..." + Utilities.ANSI_RESET);

    }

    @Override
    public Parent asNode() {
        return null;
    }
}
