package course.oop.controller.state;

import course.oop.view.CommandCall;

// has an execute() method. for executing commands internal to game state
interface Command {
    GameState execute(CommandCall c);
}
