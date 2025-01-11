package course.oop.controller.state;

import course.oop.fileio.FileIO;
import course.oop.model.Player;
import course.oop.util.Utilities;
import course.oop.view.CommandCall;
import course.oop.view.StoreView;
import course.oop.view.TTTView;
import javafx.scene.Parent;

import java.util.HashMap;
import java.util.Map;

public class StoreState implements GameState {
    private final Map<String, Command> commands;
    private final TTTView view;

    public StoreState(){
        this.view = new StoreView();
        this.commands = new HashMap<>();
        //this.view = new StoreView();
        Command BUY = c -> {
            String player = c.getArgv()[1];
            String item = c.getArgv()[2];
            Player p = FileIO.loadPlayer(player);
            if (p.charge(100)) p.unlockEmoji(Utilities.parseIntValue(item));
            return StoreState.this;
        };

        Command MAIN_MENU = c -> new InitialState();

        this.commands.put("buy", BUY);
        this.commands.put("mainmenu", MAIN_MENU);

    }
    @Override
    public GameState consumeCommand(CommandCall c) {
        String cmd = c.getArgv()[0];
        if (!commands.containsKey(cmd)) return null;
        return commands.get(cmd.toLowerCase()).execute(c);
    }

    @Override
    public String getPrompt() {
        return null;
    }

    @Override
    public void printInitialText() {

    }

    @Override
    public Parent asNode() {
        return view.getRoot();
    }
}
