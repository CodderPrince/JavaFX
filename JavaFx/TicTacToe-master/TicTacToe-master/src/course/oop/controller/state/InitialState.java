package course.oop.controller.state;

import course.oop.view.CommandCall;
import course.oop.view.MainView;
import course.oop.view.TTTView;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.util.HashMap;
import java.util.Map;

// initial
public class InitialState implements GameState {

    private final Map<String, Command> commands;

    private final TTTView view;


    public InitialState() {
        this.view = new MainView();
        commands = new HashMap<>();
        Command SETUP = c -> new GameSetupState();
        commands.put("setup", SETUP);
        Command STORE = c -> new StoreState();
        commands.put("store", STORE);
    }

    @Override
    public GameState consumeCommand(CommandCall c) {
        String command = c.getArgAt(0).toLowerCase();
        if (!commands.containsKey(command)) return null;
        return commands.get(command).execute(c);
    }

    @Override
    public String getPrompt() {
        return "Main Menu";
    }

    @Override
    public void printInitialText() {

    }

    @Override
    public Parent asNode() {
        return view.getRoot();
    }

}
