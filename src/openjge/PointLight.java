package openjge;

public class PointLight extends Light {
    public Color color;
    public float power;

    public PointLight(Color color, float power) {
        this.color = color;
        this.power = power;
    }
}