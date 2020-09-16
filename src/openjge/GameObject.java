package openjge;

import java.util.ArrayList;
import java.util.List;

public final class GameObject {
    public final String name;
    public Transform transform;
    public Component[] components;

    public GameObject(String name, Transform transform, Component... components) {
        this.name = name;
        this.transform = transform;
        this.components = components;

        ownComponents();
    }

    public void update() {
        for (Component component : components)
            component.update();
    }

    public void fixedUpdate() {
        for (Component component : components)
            component.fixedUpdate();
    }

    public void lateUpdate() {
        for (Component component : components)
            component.lateUpdate();
    }

    public void destroy() {
        for (Component component : components)
            component.onDestroy();
    }

    private void ownComponents() {
        for (Component component : components)
            component.setGameObject(this);
    }

    //region Useful Methods
    public <T extends Component> T[] getComponents() {
        List<T> array = new ArrayList<T>(2);

        for (Component component : components)
            array.add((T) component);

        return (T[]) array.toArray();
    }

    public <T extends Component> boolean hasComponent() {
        for (Component component : components)
            try {
                this.<T> castText((T) component);
                return true;
            } catch (ClassCastException e) {}

        return false;
    }

    public <T extends Component> T getComponent() {
        for (Component component : components)
            try {
                castText((T) component);
                return (T) component;
            } catch (ClassCastException e) {}

        return null;
    }

    private <T> void castText(T obejct) {}
    //endregion
}
