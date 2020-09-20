package openjge;

public class Color {
    public static final Color BLACK      = new Color(0);
    public static final Color WHITE      = new Color(1);
    public static final Color GRAY       = new Color(0.5f);
    public static final Color RED        = new Color(1, 0, 0);
    public static final Color ORANGE     = new Color(1, 0.5f, 0);
    public static final Color YELLOW     = new Color(1, 1, 0);
    public static final Color GREEN      = new Color(0, 1, 0);
    public static final Color TURQUOISE  = new Color(0, 1, 1);
    public static final Color BLUE       = new Color(0, 0, 1);
    public static final Color PURPLE     = new Color(1, 0, 1);

    public float r, g, b, a;

    public Color(int argb) {
        set(argb);
    }

    public Color(float r, float g, float b, float a) {
        set(r, g, b, a);
    }

    public Color(float r, float g, float b) {
        set(r, g, b);
    }

    public Color(float g, float a) {
        set(g, a);
    }

    public Color(float g) {
        set(g);
    }

    public void set(int argb) {

    }

    public void set(float r, float g, float b, float a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    public void set(float r, float g, float b) {
        set(r, g, b, 1);
    }

    public void set(float g, float a) {
        set(g, g, g, a);
    }

    public void set(float g) {
        set(g, g, g);
    }

    @Override
    public String toString() {
        return "{ red: " + r + ", green: " + g + ", blue: " + b + ", alpha: " + a + " }";
    }
}