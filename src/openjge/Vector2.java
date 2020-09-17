package openjge;

public class Vector2 implements Cloneable, java.io.Serializable {
    public static final Vector2 ZERO() {
        return new Vector2(0, 0);
    }

    public static final Vector2 ONE() {
        return new Vector2(1, 1);
    }

    public float x, y;

    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2(float[] array) {
        this.x = array[0];
        this.y = array[1];
    }

    public Vector2() {}

    //region Static Operators
    public static Vector2 add(Vector2 vec1, Vector2 vec2) {
        return new Vector2(vec1.x + vec2.x, vec1.y + vec2.y);
    }

    public static Vector2 sub(Vector2 vec1, Vector2 vec2) {
        return new Vector2(vec1.x + vec2.x, vec1.y + vec2.y);
    }

    public static Vector2 mul(Vector2 vec, float value) {
        return new Vector2(vec.x * value, vec.y * value);
    }

    public static Vector2 div(Vector2 vec, float value) {
        return new Vector2(vec.x / value, vec.y / value);
    }

    public static Vector2 neg(Vector2 vec) {
        return new Vector2(-vec.x, -vec.y);
    }
    //endregion

    //region Instance Operators
    public Vector2 add(Vector2 other) {
        return set(add(this, other));
    }

    public Vector2 sub(Vector2 other) {
        return set(sub(this, other));
    }

    public Vector2 mul(float value) {
        return set(mul(this, value));
    }

    public Vector2 div(float value) {
        return set(div(this, value));
    }

    public Vector2 neg() {
        return set(neg(this));
    }

    public Vector2 set(Vector2 other) {
        return set(other.x, other.y);
    }

    public Vector2 set(float x, float y) {
        this.x = x;
        this.y = y;

        return this;
    }

    public Vector2 set(float[] array) {
        return set(array[0], array[1]);
    }

    public float[] toArray() {
        return new float[] { x, y };
    }

    public Vector3 toVector3() {
        return new Vector3(x, y);
    }

    public Vector4 toVector4() {
        return new Vector4(x, y);
    }

    @Override
    public String toString() {
        return "[ " + x + ", " + y + " ]";
    }

    @Override
    public Vector2 clone() {
        return new Vector2(this.x, this.y);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) x;
        result = prime * result + (int) y;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Vector2 other)
            return this.x == other.x && this.y == other.y;
        return false;
    }
    //endregion

    //region Static Methods
    public static float dot(Vector2 vec1, Vector2 vec2) {
        return vec1.x * vec2.x + vec1.y * vec2.y;
    }
    //endregion

    //region Instance Methods
    public float magSquared() {
        return this.x*this.x + this.y*this.y;
    }

    public float mag() {
        return (float) Math.sqrt(magSquared());
    }

    public Vector2 normalized() {
        return div(mag());
    }

    public Vector2 normalize() {
        return set(normalized());
    }

    public float dot(Vector2 vec) {
        return dot(this, vec);
    }
    //endregion
}
