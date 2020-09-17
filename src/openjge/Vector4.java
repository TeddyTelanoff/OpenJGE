package openjge;

public class Vector4 implements Cloneable, java.io.Serializable {
    public static final Vector4 ZERO() {
        return new Vector4(0, 0, 0, 0);
    }

    public static final Vector4 ONE() {
        return new Vector4(1, 1, 1, 1);
    }

    public float x, y, z, w;

    public Vector4(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public Vector4(float x, float y, float z) {
        this(x, y, z, 1);
    }

    public Vector4(float x, float y) {
        this(x, y, 1);
    }

    public Vector4(float[] array) {
        this.x = array[0];
        this.y = array[1];
        this.z = array[2];
        this.w = array[3];
    }

    public Vector4() {}

    //region Static Operators
    public static Vector4 add(Vector4 vec1, Vector4 vec2) {
        return new Vector4(vec1.x + vec2.x, vec1.y + vec2.y, vec1.z + vec2.z, vec1.w + vec2.w);
    }

    public static Vector4 sub(Vector4 vec1, Vector4 vec2) {
        return new Vector4(vec1.x + vec2.x, vec1.y + vec2.y, vec1.z + vec2.z, vec1.w - vec2.w);
    }

    public static Vector4 mul(Vector4 vec, float value) {
        return new Vector4(vec.x * value, vec.y * value, vec.z * value, vec.w * value);
    }

    public static Vector4 div(Vector4 vec, float value) {
        return new Vector4(vec.x / value, vec.y / value, vec.z / value, vec.w / value);
    }

    public static Vector4 neg(Vector4 vec) {
        return new Vector4(-vec.x, -vec.y, -vec.z, -vec.w);
    }
    //endregion

    //region Instance Operators
    public Vector4 add(Vector4 other) {
        return set(add(this, other));
    }

    public Vector4 sub(Vector4 other) {
        return set(sub(this, other));
    }

    public Vector4 mul(float value) {
        return set(mul(this, value));
    }

    public Vector4 div(float value) {
        return set(div(this, value));
    }

    public Vector4 neg() {
        return set(neg(this));
    }

    public Vector4 set(Vector4 other) {
        return set(other.x, other.y, other.z, other.w);
    }

    public Vector4 set(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;

        return this;
    }

    public Vector4 set(float x, float y, float z) {
        return set(x, y, z, 1);
    }

    public Vector4 set(float x, float y) {
        return set(x, y, 1);
    }

    public Vector4 set(float[] array) {
        return set(array[0], array[1], array[2], array[3]);
    }

    public float[] toArray() {
        return new float[] { x, y, z, w };
    }

    public Vector2 toVector2() {
        return new Vector2(x, y);
    }

    public Vector3 toVector3() {
        return new Vector3(x, y);
    }

    @Override
    public String toString() {
        return "[ " + x + ", " + y + ", " + z + ", " + w + " ]";
    }

    @Override
    public Vector4 clone() {
        return new Vector4(this.x, this.y, this.z, this.w);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) x;
        result = prime * result + (int) y;
        result = prime * result + (int) z;
        result = prime * result + (int) w;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Vector4 other)
            return this.x == other.x && this.y == other.y && this.z == other.z && this.w == other.w;
        return false;
    }
    //endregion

    //region Static Methods
    public static float dot(Vector4 vec1, Vector4 vec2) {
        return vec1.x * vec2.x + vec1.y * vec2.y + vec1.z * vec2.z + vec1.w * vec2.w;
    }
    //endregion

    //region Instance Methods
    public float magSquared() {
        return this.x*this.x + this.y*this.y + this.z*this.z + this.w*this.w;
    }

    public float mag() {
        return (float) Math.sqrt(magSquared());
    }

    public Vector4 normalized() {
        return div(mag());
    }

    public Vector4 normalize() {
        return set(normalized());
    }

    public float dot(Vector4 vec) {
        return dot(this, vec);
    }
    //endregion
}
