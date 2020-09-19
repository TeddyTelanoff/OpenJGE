package openjge;

public class Transform extends Component {
    public Vector3 position, rotation, scale;

    public Transform(Vector3 position, Vector3 rotation, Vector3 scale) {
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
    }

    public Transform() {
        this(new Vector3(), new Vector3(), new Vector3(1, 1, 1));
    }

    public Matrix4 getMatrix() {
        return Matrix4.transform(Vector3.add(position, gameObject().getParent() == null ? Vector3.ZERO() : gameObject().getParent().transform.position),
                Vector3.add(rotation, gameObject().getParent() == null ? Vector3.ZERO() : gameObject().getParent().transform.rotation),
                Vector3.mul(scale, gameObject().getParent() == null ? Vector3.ONE() : gameObject().getParent().transform.scale));
    }

    public Matrix4 getView() {
        return Matrix4.view(Vector3.add(position, gameObject().getParent() == null ? Vector3.ZERO() : gameObject().getParent().transform.position),
                Vector3.add(rotation, gameObject().getParent() == null ? Vector3.ZERO() : gameObject().getParent().transform.rotation));
    }

    public Vector3 getForward() {
        Matrix4 view = getView();

        return new Vector3(view.get(2, 0), view.get(2, 1), -view.get(2, 2));
    }

    public Vector3 getRight() {
        Matrix4 view = getView();

        return new Vector3(view.get(0, 0), view.get(0, 1), -view.get(0, 2));
    }

    public Vector3 getUp() {
        Matrix4 view = getView();

        return new Vector3(view.get(1, 0), view.get(1, 1), -view.get(1, 2));
    }
}