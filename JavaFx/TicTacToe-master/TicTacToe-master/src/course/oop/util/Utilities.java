package course.oop.util;

public class Utilities {

    public static String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    public static String HELP_START = ANSI_GREEN;
    public static String ERR_START = ANSI_RED;
    public static String CIRCLE = "\u2b55";
    public static String CROSS = "\u274C";
    public static String EMOJI = "\uD83D\uDE39";
    public static String BOARD = ANSI_BLUE;

    public static void convert() {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            BOARD = "";
            HELP_START = "";
            ANSI_RESET = "";
            ERR_START = "";
            CIRCLE = " o ";
            CROSS = " x ";
            EMOJI = "(^.^)";
            System.err.println("Performing windows conversion...");
        }
    }

    public static int parseIntValue(String value) {
        int val = Integer.MIN_VALUE;
        try {
            val = Integer.parseInt(value);
        } catch (Exception e) {
            System.out.println("Invalid integer: " + value);
        }
        return val;
    }
}
