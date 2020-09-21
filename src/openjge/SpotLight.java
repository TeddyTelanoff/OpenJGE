package openjge;

public class SpotLight extends Light {
    public Color color;
    public float power;

    public SpotLight(Color color, float power) {
        this.color = color;
        this.power = power;
    }
}