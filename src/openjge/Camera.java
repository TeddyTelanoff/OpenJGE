package openjge;

public final class Camera extends Component {
    public static Camera mainCamera;

    public float fov;
    public float near, far;

    public Camera(float fov, float near, float far) {
        this.fov = fov;
        this.near = near;
        this.far = far;
    }

    public void init() {
        Camera.mainCamera = this;
    }

    public Matrix4 getView() {
        return transform.getView();
    }

    public Matrix4 getProjection() {
        return Matrix4.projection(fov, (float) JGEProgram.getInstance().getWindow().getWidth() / JGEProgram.getInstance().getWindow().getHeight(), near, far);
    }
}