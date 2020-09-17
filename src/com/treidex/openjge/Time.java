package com.treidex.openjge;

public final class Time {
    private static float fixedDeltaTime;
    private static float deltaTime;
    private static long pmillis;

    public static void update() {
        deltaTime = (float) (System.currentTimeMillis() - pmillis) / 1000;
        pmillis = System.currentTimeMillis();
    }

    public static void init(long fixedDeltaTimeMillis) {
        fixedDeltaTime = (float) fixedDeltaTimeMillis / 1000;
    }

    public static float getDeltaTime() {
        return deltaTime;
    }

    public static float getFixedDeltaTime() {
        return fixedDeltaTime;
    }
}