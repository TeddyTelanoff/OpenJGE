package com.treidex.openjge;

public final class Time {
    private float fixedDeltaTime;
    private float deltaTime;

    public static void update() {
        
    }

    public float getDeltaTime() {
        return deltaTime;
    }

    public float getFixedDeltaTime() {
        return fixedDeltaTime;
    }
}