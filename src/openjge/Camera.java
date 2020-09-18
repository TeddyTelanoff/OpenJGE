package openjge;

public final class Camera extends Component {
    public static final int PERSPECTIVE = 0, ORTHOGRAPHIC = 1;

    public static Camera mainCamera;

    public float fov;
    public float near, far;
    public float left, top, right, bottom;

    public final int projection;

    public Camera(float fov, float near, float far) {
        this.fov = fov;
        this.near = near;
        this.far = far;

        projection = PERSPECTIVE;
    }

    public Camera(float near, float far, float left, float top, float right, float bottom) {
        this.near = near;
        this.far = far;
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;

        projection = ORTHOGRAPHIC;
    }

    public void init() {
        Camera.mainCamera = this;
    }

    public Matrix4 getView() {
        return transform.getView();
    }

    public Matrix4 getProjection() {
        switch (projection)
        {
        case PERSPECTIVE:
            return Matrix4.perspective(fov, (float) JGEProgram.getInstance().getWindow().getWidth() / JGEProgram.getInstance().getWindow().getHeight(), near, far);
        case ORTHOGRAPHIC:
            return Matrix4.orthographic(near, far, left, top, right, bottom);

        default:
            return Matrix4.identity();
        }
    }
}