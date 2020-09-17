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
        return Vector3.forward().mul(getView());
    }

    public Vector3 getRight() {
        return Vector3.right().mul(getView());
    }

    public Vector3 getUp() {
        return Vector3.up().mul(getView());
    }

    private Matrix4 getView() {
        return Matrix4.view(position, rotation);
    }
}