package openjge;

public class DirectionalLight extends Light {
    public Color ambient, diffuse, specular;
    public float power;

    public DirectionalLight(Color ambient, Color diffuse, Color specular, float power) {
        this.ambient = ambient;
        this.diffuse = diffuse;
        this.specular = specular;
        this.power = power;
    }
}