package openjge;

public class AmbientLight extends Light {
    private static AmbientLight instance;

    public Color color;

    public AmbientLight(Color color) {
        this.color = color;
        instance = this;
    }

    public static AmbientLight getInstance() {
        return instance;
    }
}