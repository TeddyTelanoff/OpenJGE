package openjge;

public class Vector3 implements Cloneable, java.io.Serializable {
    public static final Vector3 ZERO() {
        return new Vector3(0, 0, 0);
    }

    public static final Vector3 ONE() {
        return new Vector3(1, 1, 1);
    }

    public static final Vector3 forward() {
        return new Vector3(0, 0, -1);
    }

    public static final Vector3 right() {
        return new Vector3(1, 0, 0);
    }

    public static final Vector3 up() {
        return new Vector3(0, 1, 0);
    }

    public float x, y, z;
    private boolean clone;

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

    public Vector3() {
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
    public static Vector3 mul(Vector3 vec1, Vector3 vec2) {
        return new Vector3(vec1.x * vec2.x, vec1.y * vec2.y, vec1.z * vec2.z);
    }
    public static Vector3 mul(Vector3 vec, Matrix4 matrix) {
        return new Vector3(
            vec.x * matrix.get(0, 0) + (vec.y * matrix.get(1, 0)) + (vec.z * matrix.get(2, 0)),
            vec.x * matrix.get(0, 1) + (vec.y * matrix.get(1, 1)) + (vec.z * matrix.get(2, 1)),
            vec.x * matrix.get(0, 2) + (vec.y * matrix.get(1, 2)) + (vec.z * matrix.get(2, 2))
        );
    }

    public static Vector3 div(Vector3 vec, float value) {
        return new Vector3(vec.x / value, vec.y / value, vec.z / value);
    }
    public static Vector3 div(Vector3 vec1, Vector3 vec2) {
        return new Vector3(vec1.x / vec2.x, vec1.y / vec2.y, vec1.z / vec2.z);
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
    public Vector3 mul(Vector3 vec) {
        return set(mul(this, vec));
    }
    public Vector3 mul(Matrix4 matrix) {
        return set(mul(this, matrix));
    }

    public Vector3 div(float value) {
        return set(div(this, value));
    }
    public Vector3 div(Vector3 vec) {
        return set(div(this, vec));
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

    public Vector3 set(float[] array) {
        return set(array[0], array[1], array[2]);
    }

    public float[] toArray() {
        return new float[]{x, y, z};
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
    public Vector3 clone() {
        return new Vector3(this.x, this.y, this.z);
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
        if (obj instanceof Vector3 other)
        return this.x == other.x && this.y == other.y && this.z == other.z;
        return false;
    }
    //endregion

    //region Static Methods
    public static float dot(Vector3 vec1, Vector3 vec2) {
        return vec1.x * vec2.x + vec1.y * vec2.y + vec1.z * vec2.z;
    }
    //endregion

    //region Instance Methods
    public float magSquared() {
        return this.x * this.x + this.y * this.y + this.z * this.z;
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