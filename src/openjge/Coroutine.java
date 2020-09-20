package openjge;

import java.util.ArrayList;
import java.util.List;

public class Coroutine {
    private static List<Thread> coroutines = new ArrayList<Thread>(3);

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
        Thread routine;
        coroutines.add(routine = new Thread(coroutine));
        routine.start();
    }

    public static void stopCoroutine(Runnable coroutine) {
        int index = coroutines.indexOf(new Thread(coroutine));
        Debug.Assert(index >= 0, "Coroutine doesn't Exist!");
        coroutines.get(index).interrupt();
        coroutines.remove(index);
    }

    public static void stopAllCoroutines() {
        for (Thread coroutine : coroutines)
            coroutine.interrupt();
        coroutines.removeAll(coroutines);
    }

    public static List<Thread> getCoroutines() {
        return coroutines;
    }
}