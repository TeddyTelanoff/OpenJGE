package openjge;

public class Component {
    public Transform transform;
    private GameObject gameObject;
    private boolean goChanged;

    public void init() {}

    public void update() {}

    public void fixedUpdate() {}

    public void lateUpdate() {}

    public void onDestroy() {}

    //region Useful Methods
    public <T extends Component> T[] getComponents(Class<T> tclass) {
        return gameObject.<T> getComponents(tclass);
    }

    public <T extends Component> T getComponent(Class<T> tclass) {
        return gameObject.<T> getComponent(tclass);
    }

    public <T extends Component> boolean hasComponent(Class<T> tclass) {
        return gameObject.<T> hasComponent(tclass);
    }
    //endregion

    public GameObject gameObject() {
        return gameObject;
    }

    public void setGameObject(GameObject gameObject) {
        if (!goChanged) {
            this.gameObject = gameObject;
            transform = gameObject.transform;

            goChanged = true;
        }
    }
}
