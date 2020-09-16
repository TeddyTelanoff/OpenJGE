package openjge;

public class Transform extends Component {
    public Vector3 position, rotation, scale;

    public Transform(Vector3 position, Vector3 rotation, Vector3 scale) {
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
    }

    public Matrix4 getMatrix() {
        return Matrix4.transform(position, rotation, scale);
    }

    public Vector3 getForward() {
        Matrix4 matrix = getMatrix();

        float x = matrix.get(2, 0);
        float y = matrix.get(2, 1);
        float z = matrix.get(2, 2);

        return new Vector3(x, y, z);
    }

    public Vector3 getRight() {
        Matrix4 matrix = getMatrix();

        float x = matrix.get(0, 0);
        float y = matrix.get(0, 1);
        float z = matrix.get(0, 2);

        return new Vector3(x, y, z);
    }

    public Vector3 getUp() {
        Matrix4 matrix = getMatrix();

        float x = matrix.get(1, 0);
        float y = matrix.get(1, 1);
        float z = matrix.get(1, 2);

        return new Vector3(x, y, z);
    }
}