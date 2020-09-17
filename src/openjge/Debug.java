package openjge;

public final class Debug {
    private Debug() {}

    private static boolean isDebugging;

    public static void getDebuging() {
        String debugEnv = System.getenv("debug");
        isDebugging = debugEnv != null && (debugEnv.equalsIgnoreCase("true") || debugEnv.equalsIgnoreCase("1"));
    }

    public static boolean isDebuging() {
        return isDebugging;
    }

    public static void log(String msg) {
        if (isDebugging)
            System.out.println(msg);
    }

    public static void logErr(String errorMsg) {
        if (isDebugging)
            System.err.println(errorMsg);
    }

    public static void Assert(boolean condition, String errorMsg) {
        if (isDebugging && !condition) {
            throw new AssertionError(errorMsg);
        }
    }

    public static void Assert(boolean condition) {
        if (isDebugging && !condition) {
            throw new AssertionError();
        }
    }
}