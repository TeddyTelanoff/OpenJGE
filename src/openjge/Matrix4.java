package openjge;

import org.lwjglx.util.vector.Matrix;

import java.util.Arrays;

public class Matrix4{
    public static final int SIZE = 4;

    private float[] elements = new float[SIZE * SIZE];

    public static Matrix4 identity() {
        Matrix4 result = new Matrix4();

        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                result.set(i, j, 0);

        result.set(0, 0, 1);
        result.set(1, 1, 1);
        result.set(2, 2, 1);
        result.set(3, 3, 1);

        return result;
    }

    //region Static Methods
    public static Matrix4 translate(Vector3 translate) {
        Matrix4 result = identity();

        result.set(3, 0, translate.x);
        result.set(3, 1, translate.y);
        result.set(3, 2, translate.z);

        return result;
    }

    public static Matrix4 rotate(float angle, Vector3 axis) {
        Matrix4 result = identity();

        float cos = (float) Math.cos(Math.toRadians(angle));
        float sin = (float) Math.sin(Math.toRadians(angle));
        float C = 1 - cos;

        result.set(0, 0, cos + axis.x * axis.x * C);
        result.set(0, 1, axis.x * axis.y * C - axis.z * sin);
        result.set(0, 2, axis.x * axis.z * C + axis.y * sin);
        result.set(1, 0, axis.y * axis.x * C + axis.z * sin);
        result.set(1, 1, cos + axis.y * axis.y * C);
        result.set(1, 2, axis.y * axis.z * C - axis.x * sin);
        result.set(2, 0, axis.z * axis.x * C - axis.y * sin);
        result.set(2, 1, axis.z * axis.y * C + axis.x * sin);
        result.set(2, 2, cos + axis.z * axis.z * C);

        return result;
    }

    public static Matrix4 scale(Vector3 scalar) {
        Matrix4 result = identity();

        result.set(0, 0, scalar.x);
        result.set(1, 1, scalar.y);
        result.set(2, 2, scalar.z);

        return result;
    }

    public static Matrix4 transform(Vector3 position, Vector3 rotation, Vector3 scale) {
        Matrix4 result;

        Matrix4 translationMatrix = translate(position);
        Matrix4 rotXMatrix = rotate(rotation.x, new Vector3(1, 0, 0));
        Matrix4 rotYMatrix = rotate(rotation.y, new Vector3(0, 1, 0));
        Matrix4 rotZMatrix = rotate(rotation.z, new Vector3(0, 0, 1));
        Matrix4 scaleMatrix = scale(scale);

        Matrix4 rotationMatrix = multiply(rotXMatrix, multiply(rotYMatrix, rotZMatrix));

        result = multiply(translationMatrix, multiply(rotationMatrix, scaleMatrix));

        return result;
    }

    public static Matrix4 perspective(float fov, float aspect, float near, float far) {
        Matrix4 result = identity();

        float tanFOV = (float) Math.tan(Math.toRadians(fov / 2));
        float range = far - near;

        result.set(0, 0, 1.0f / (aspect * tanFOV));
        result.set(1, 1, 1.0f / tanFOV);
        result.set(2, 2, -((far + near) / range));
        result.set(2, 3, -1.0f);
        result.set(3, 2, -((2 * far * near) / range));
        result.set(3, 3, 0.0f);

        return result;
    }

    public static Matrix4 orthographic(float near, float far, float left, float top, float right, float bottom) {
        Matrix4 result = identity();

        float range = far - near;

        result.set(0, 0, 2 / (right - left));
        result.set(1, 1, 2 / (top - bottom));
        result.set(2, 2, -2 / range);
        result.set(3, 0, -(right + left) / (right - left));
        result.set(3, 1, -(top + bottom) / (top - bottom));
        result.set(3, 2, -range / (far - near));

        return result;
    }

    public static Matrix4 view(Vector3 position, Vector3 rotation) {
        Matrix4 result;

        Vector3 negative = new Vector3(-position.x, -position.y, -position.z);
        Matrix4 translationMatrix = translate(negative);
        Matrix4 rotXMatrix = rotate(rotation.x, new Vector3(1, 0, 0));
        Matrix4 rotYMatrix = rotate(rotation.y, new Vector3(0, 1, 0));
        Matrix4 rotZMatrix = rotate(rotation.z, new Vector3(0, 0, 1));

        Matrix4 rotationMatrix = multiply(rotYMatrix, multiply(rotZMatrix, rotXMatrix));

        result = multiply(translationMatrix, rotationMatrix);

        return result;
    }

    public static Matrix4 multiply(Matrix4 matrix, Matrix4 other) {
        Matrix4 result = identity();

        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                result.set(i, j, matrix.get(i, 0) * other.get(0, j) +
                        matrix.get(i, 1) * other.get(1, j) +
                        matrix.get(i, 2) * other.get(2, j) +
                        matrix.get(i, 3) * other.get(3, j));

        return result;
    }
    //endregion

    //region Instance Methods

    //endregion

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++)
                sb.append(get(i, j) + " ");
            sb.append('\n');
        }

        return sb.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + java.util.Arrays.hashCode(elements);
        return result;
    }

    public float get(int x, int y) {
        return elements[y * SIZE + x];
    }

    public void set(int x, int y, float value) {
        elements[y * SIZE + x] = value;
    }

    public float[] getAll() {
        return elements;
    }
}