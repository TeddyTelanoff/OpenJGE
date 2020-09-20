package openjge;

public class Coroutine {
    private Coroutine() {}

    public static void waitForWeeks(double amt) {
        waitForDays(amt * 7);
    }

    public static void waitForDays(double amt) {
        waitForHours(amt * 24);
    }

    public static void waitForHours(double amt) {
        waitForMinutes(amt * 60);
    }

    public static void waitForMinutes(double amt) {
        waitForSeconds(amt * 60);
    }

    public static void waitForSeconds(double amt) {
        waitForMillis((long) amt * 1000);
    }

    public static void waitForMillis(long millis) {
        waitForMillis(millis, 0);
    }

    public static void waitForMillis(long millis, int nanos) {
        try {
            Thread.sleep(millis, nanos);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void startCoroutine(Runnable coroutine) {
        new Thread(coroutine).start();
    }
}