package openjge;

import java.util.ArrayList;
import java.util.List;

public final class GameObject {
    public final String name;
    private GameObject parent;
    public Transform transform;
    public Component[] components;
    public GameObject[] children;

    public GameObject(String name, Transform transform, Component... components) {
        this.name = name;
        this.transform = transform;
        this.components = components;
        children = new GameObject[0];

        ownComponents();
    }

    public GameObject(String name, Transform transform, Component[] components, GameObject[] children) {
        this.name = name;
        this.transform = transform;
        this.components = components;
        this.children = children;

        ownChildren();
        ownComponents();
    }

    public void update() {
        for (Component component : components)
            component.update();
        for (GameObject child : children)
            child.update();
    }

    public void fixedUpdate() {
        for (Component component : components)
            component.fixedUpdate();
        for (GameObject child : children)
            child.fixedUpdate();
    }

    public void lateUpdate() {
        for (Component component : components)
            component.lateUpdate();
        for (GameObject child : children)
            child.lateUpdate();
    }

    public void destroy() {
        for (Component component : components)
            component.onDestroy();
        for (GameObject child : children)
            child.destroy();
    }

    private void ownComponents() {
        transform.setGameObject(this);
        for (Component component : components)
            component.setGameObject(this);
        for (GameObject child : children)
            child.ownComponents();
    }

    private void ownChildren() {
        for (GameObject child : children)
            child.parent = parent;
    }

    public GameObject getParent() {
        return parent;
    }

    //region Useful Methods
    public <T extends Component> T[] getComponents(Class<T> tclass) {
        List<T> array = new ArrayList<T>(2);

        for (Component component : components)
            if (tclass.isInstance(component))
                array.add((T) component);

        return (T[]) array.toArray();
    }

    public <T extends Component> boolean hasComponent(Class<T> tclass) {
        for (Component component : components)
            if (tclass.isInstance(component))
                return true;

        return false;
    }

    public <T extends Component> T getComponent(Class<T> tclass) {
        for (Component component : components)
            if (tclass.isInstance(component))
                return (T) component;

        return null;
    }
    //endregion
}
