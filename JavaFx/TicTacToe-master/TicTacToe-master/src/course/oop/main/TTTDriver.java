package course.oop.main;

import course.oop.util.Utilities;

class TTTDriver {

    public static void main(String[] args) {
        Utilities.convert();

        String circle = Utilities.CIRCLE;
        //"\u2b55";
        String cross = Utilities.CROSS;
        //"\u274c";
        System.out.printf("%s%s%s%s%s Welcome to TIC TAC TOE! %s%s%s%s%s%n", circle, circle, circle, circle, circle, cross, cross, cross, cross, cross);
        System.out.println();System.out.println();
        System.out.println("\tThis is tic-tac-toe in an interactive shell.");
        System.out.printf("\tUse the %shelp%s command whenever you need help.\n", Utilities.HELP_START, Utilities.ANSI_RESET);
        System.out.printf("\tIf you need information on how to use a command, type %shelp [command]%s\n", Utilities.HELP_START, Utilities.ANSI_RESET);
        System.out.println();
        System.out.println();
        System.out.println();

        //FileIO.init();

        //FileIO.init();
        //Controller c = new Controller();
        //View.act(c);

    }
}
