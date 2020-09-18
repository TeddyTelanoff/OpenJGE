package openjge;

public final class Mathf {
    private Mathf() {}

    public static float lerp(float start, float stop, float lerp) {
        return (1 - lerp) * start + lerp * stop;
    }
}