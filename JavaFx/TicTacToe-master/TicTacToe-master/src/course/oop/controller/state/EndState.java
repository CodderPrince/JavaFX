package course.oop.controller.state;

import course.oop.fileio.FileIO;
import course.oop.model.Game;
import course.oop.model.Player;
import course.oop.util.Utilities;
import course.oop.view.CommandCall;
import course.oop.view.ResultsView;
import course.oop.view.TTTView;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EndState implements GameState {

    private final Game game;
    private final int endCode;
    private final TTTView view;

    private final Map<String, Command> commands;

    EndState(Game game, int endCode) {
        this.view = new ResultsView(game);
        this.endCode = endCode;
        this.game = game;

        String result;
        if (endCode == Game.TIE) {
            result = "Tie. Everyone loses.";
            for (List<Player> team : game.getTeams()) for (Player p : team){
                p.addLoss();
                if (p.isHuman()) FileIO.writePlayer(p);
            }
        } else {
            int winningTeam = endCode;
            //int winnerIndex = winner - 1;
            StringBuilder s = new StringBuilder("");
            List<Player> winners = game.getTeam(winningTeam-1);
            s.append("Congratulations to ").append(winners.get(0));
            for (int i = 1; i < winners.size(); i++){
                s.append(", ").append(winners.get(i));
            }
            for (Player winner : winners) {
                winner.awardCash();
                winner.addWin();
                if (winner.isHuman()) FileIO.writePlayer(winner);
            }
            List<Player> losers = game.getTeam(winningTeam % 2);
            for (Player loser : losers) {
                loser.addLoss();
                if (loser.isHuman()) FileIO.writePlayer(loser);
            }
            result = s.toString();
            /*
            for (int i = 1; i <= game.getConfig().getNumPlayers(); i++) {
                Player p = game.getPlayer(i);
                if (winner == i) p.addWin();
                else p.addLoss();
                if (p.isHuman()) FileIO.writePlayer(p);
                System.out.println("EndState.java: " + p.asEntry());
            }
            */
        }

        //game.printGameBoard();
        System.out.println(result);

        this.commands = new HashMap<>();
        Command REMATCH = c -> {
            game.clearEffects();
            return new GameInitState(game.getConfig());
        };
        commands.put("rematch", REMATCH);
        Command MAIN_MENU = c -> new InitialState();
        commands.put("mainmenu", MAIN_MENU);
        /*
        Command PRINT = c -> {
            //game.printGameBoard();
            return EndState.this;
        };
        commands.put("print", PRINT);
         */
        Command SETUP = c -> new GameSetupState();
        commands.put("setup", SETUP);

        for (Player p : FileIO.loadHashMap().values()) System.out.println(p.asEntry());
    }

    //allowed commands: select row column
    @Override
    public GameState consumeCommand(CommandCall c) {
        String cmd = c.getArgv()[0];
        if (!commands.containsKey(cmd)) return null;
        return commands.get(cmd.toLowerCase()).execute(c);
    }

    @Override
    public String getPrompt() {
        return "End-Results";
    }

    public void printInitialText() {
        String circle = Utilities.CIRCLE;
        //"\u2b55";
        String cross = Utilities.CROSS;
        //"\u274c";
        System.out.printf("%s%s%s%s%s End Results %s%s%s%s%s%n", circle, circle, circle, circle, circle, cross, cross, cross, cross, cross);
        String result = "";
        if (endCode == Game.TIE) {
            result = "Tie. Everyone loses.";
        } else {
            int winner = endCode;
            int winnerIndex = winner - 1;
            //result = String.format("Player %d wins! Congratulations %s!%n", winner, game.getPlayer(winner));
        }

        //game.printGameBoard();
        System.out.println(result);
    }

    @Override
    public Parent asNode() {
        return this.view.getRoot();
    }

}