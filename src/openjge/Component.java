package openjge;

public class Component {
    private GameObject gameObject;
    private boolean goChanged;

    public void update() {}

    public void fixedUpdate() {}

    public void lateUpdate() {}

    //region Useful Methods
    public <T extends Component> T[] getComponents() {
        return gameObject.<T> getComponents();
    }

    public <T extends Component> T getComponent() {
        return gameObject.<T> getComponent();
    }

    public <T extends Component> boolean hasComponent() {
        return gameObject.<T> hasComponent();
    }
    //endregion

    public GameObject gameObject() {
        return gameObject;
    }

    public void setGameObject(GameObject gameObject) {
        if (!goChanged) {
            this.gameObject = gameObject;

            goChanged = true;
        }
    }
}
