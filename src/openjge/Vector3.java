package openjge;

public class Vector3 {
    public float x, y, z;

    public Vector3(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static Vector3 add(Vector3 vec1, Vector3 vec2) {
        return new Vector3(vec1.x + vec2.x, vec1.y + vec2.y, vec1.z + vec2.z);
    }

    public static Vector3 sub(Vector3 vec1, Vector3 vec2) {
        return new Vector3(vec1.x + vec2.x, vec1.y + vec2.y, vec1.z + vec2.z);
    }

    public static Vector3 mul(Vector3 vec, float other) {
        return new Vector3(vec.x * other, vec.y * other, vec.z * other);
    }

    public static Vector3 div(Vector3 vec, float other) {
        return new Vector3(vec.x / other, vec.y / other, vec.z / other);
    }
}
