package course.oop.controller.state;

import course.oop.model.Game;
import course.oop.model.Player;
import course.oop.view.CommandCall;
import course.oop.view.TurnView;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import static course.oop.model.Game.NO_WINNER;

public class TurnState implements GameState {

    private final Map<String, Command> commands;

    private final Game game;
    private final LocalTime timeStarted;
    private boolean turnOver;
    private final TurnView view;

    private final int team;
    private final int[] teamPlayer;

    // pass in information about which team's turn it is and which index of player
    public TurnState(Game g) {
        this(g, -1, new int[]{0,0});
    }

    // Zero-index
    private TurnState(Game g, int team, int[] teamPlayer) {
        if (team == -1) {
            team = g.randomTeam();
        }
        this.team = team;
        this.teamPlayer = teamPlayer;
        this.game = g;
        this.view = new TurnView(game, team, teamPlayer);
        timeStarted = LocalTime.now();

        printInitialText();

        // setup commands
        this.commands = new HashMap<>();
        Command SELECT = c -> {
            String row = c.getArgv()[1];
            String column = c.getArgv()[2];
            String z = "0";
            //assert false;
            if (c.getArgv().length == 4) z = c.getArgv()[3];
            String[] coords = {row, column, z};
            if (!game.selectTile(coords, this.team, teamPlayer)) return TurnState.this;

            int winner = game.determineWinner();

            if (winner == 0) return getNextTurnState();
            if (winner == 3) {
                game.getConfig().setStatus("Tie Breaker!");
                game.clearEffects();
                return new GameInitState(game.getConfig());
            }
            return new EndState(game, winner);
        };
        commands.put("select", SELECT);
        Command ROTATE = c -> {
            String direction = c.getArgv()[1];
            g.rotate(direction);
            return new TurnState(this.game, this.team, this.teamPlayer);
        };
        commands.put("rotate", ROTATE);
        Command FORFEIT = c -> new EndState(g,3);
        commands.put("forfeit", FORFEIT);
    }

    @Override
    public void printInitialText() {
        //printGameBoard();
        System.out.printf("It's %s's turn!%n", game.getPlayer(team, teamPlayer[team]));
    }

    @Override
    public GameState consumeCommand(CommandCall c) {
        if (turnOver) {
            System.out.println("TURN OVER");
            return getNextTurnState();
        }
        if (game.getConfig().getTimeout() > 0 && !turnOver && LocalTime.now().isAfter(timeStarted.plusSeconds(game.getConfig().getTimeout()))) {
            System.out.println("TURN OVER 2");
            return getNextTurnState();
        }
        String cmd = c.getArgv()[0];
        if (!commands.containsKey(cmd)) return null;
        return commands.get(cmd.toLowerCase()).execute(c);
    }

    private GameState getNextTurnState() {
        System.out.printf("Wow! Current player is player %d of team %d. Next is player %d of team %d\n", teamPlayer[team], team,  teamPlayer[(team+1) % 2], team + 1 % 2);
        // update team player
        //teamPlayer = game.getConfig().updateTeam(team, teamPlayer);
        return new TurnState(game, (team+1)%2, game.getConfig().updateTeam(team, teamPlayer));
    }

    @Override
    public String getPrompt() {
        Player p = game.getConfig().getPlayer(team, teamPlayer[team]);
        return String.format("%s %s", p, p.getMarker());
    }

    @Override
    public Parent asNode() {
        return view.getRoot();
    }

}