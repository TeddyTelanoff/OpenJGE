package openjge;

public class Component {
    public final GameObject gameObject;

    public Component(GameObject gameObject) {
        this.gameObject = gameObject;
    }

    public <T extends Component> T[] getComponents() {
        return gameObject.<T> getComponents();
    }

    public <T extends Component> T getComponent() {
        return gameObject.<T> getComponent();
    }

    public <T extends Component> boolean hasComponent() {
        return gameObject.<T> hasComponent();
    }
}
