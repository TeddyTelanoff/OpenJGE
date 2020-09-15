package openjge;

public class Vector3 implements Cloneable, java.io.Serializable {
    public static final Vector3 ZERO = new Vector3(0, 0, 0);
    public static final Vector3 ONE = new Vector3(1, 1, 1);

    public float x, y, z;

    public Vector3(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3(float x, float y) {
        this(x, y, 1);
    }

    public Vector3(float[] array) {
        this.x = array[0];
        this.y = array[1];
        this.z = array[2];
    }

    //region Static Operators
    public static Vector3 add(Vector3 vec1, Vector3 vec2) {
        return new Vector3(vec1.x + vec2.x, vec1.y + vec2.y, vec1.z + vec2.z);
    }

    public static Vector3 sub(Vector3 vec1, Vector3 vec2) {
        return new Vector3(vec1.x + vec2.x, vec1.y + vec2.y, vec1.z + vec2.z);
    }

    public static Vector3 mul(Vector3 vec, float value) {
        return new Vector3(vec.x * value, vec.y * value, vec.z * value);
    }

    public static Vector3 div(Vector3 vec, float value) {
        return new Vector3(vec.x / value, vec.y / value, vec.z / value);
    }

    public static Vector3 neg(Vector3 vec) {
        return new Vector3(-vec.x, -vec.y, -vec.z);
    }
    //endregion

    //region Instance Operators
    public Vector3 add(Vector3 other) {
        return set(add(this, other));
    }

    public Vector3 sub(Vector3 other) {
        return set(sub(this, other));
    }

    public Vector3 mul(float value) {
        return set(mul(this, value));
    }

    public Vector3 div(float value) {
        return set(div(this, value));
    }

    public Vector3 neg() {
        return set(neg(this));
    }

    public Vector3 set(Vector3 other) {
        return set(other.x, other.y, other.z);
    }

    public Vector3 set(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;

        return this;
    }

    public Vector3 set(float x, float y) {
        return set(x, y, 1);
    }

    public float[] toArray() {
        return new float[] { x, y, z };
    }

    public Vector2 toVector2() {
        return new Vector2(x, y);
    }

    public Vector4 toVector4() {
        return new Vector4(x, y, z);
    }

    @Override
    public String toString() {
        return "[ " + x + ", " + y + ", " + z + " ]";
    }

    @Override
    public Vector4 clone() {
        return new Vector4(this.x, this.y, this.z);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) x;
        result = prime * result + (int) y;
        result = prime * result + (int) z;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Vector3))
            return false;
        Vector3 other = (Vector3) obj;
        return this.x == other.x && this.y == other.y && this.z == other.z;
    }
    //endregion

    //region Static Methods
    public static float dot(Vector3 vec1, Vector3 vec2) {
        return vec1.x * vec2.x + vec1.y * vec2.y + vec1.z * vec2.z;
    }
    //endregion

    //region Instance Methods
    public float magSquared() {
        return this.x*this.x + this.y*this.y + this.z*this.z;
    }

    public float mag() {
        return (float) Math.sqrt(magSquared());
    }

    public Vector3 normalized() {
        return div(mag());
    }

    public Vector3 normalize() {
        return set(normalized());
    }

    public float dot(Vector3 vec) {
        return dot(this, vec);
    }
    //endregion
}