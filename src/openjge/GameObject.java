package openjge;

import java.util.ArrayList;
import java.util.List;

public final class GameObject {
    public final String name;
    public final int layer;
    private GameObject parent;
    public Transform transform;
    public Component[] components;
    public GameObject[] children;

    public GameObject(String name, int layer, Transform transform, Component[] components, GameObject[] children) {
        this.name = name;
        this.layer = layer;
        this.transform = transform;
        this.components = components;
        this.children = children;

        ownChildren();
        ownComponents();
    }

    public GameObject(String name, int layer, Transform transform, Component... components) {
        this(name, layer, transform, components, new GameObject[0]);
    }

    public void init() {
        for (Component component : components)
            component.init();
        for (GameObject child : children)
            child.init();
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
            child.parent = this;
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

    public void destroy(Component component) {
        int index = ArrayUtil.indexOf(components, component);
        Debug.Assert(index != -1, "Game Object doesn't exist in the Scene!");
        components[index].onDestroy();
        components = ArrayUtil.remove(Component.class, components, index);
    }

    public void destroy(GameObject gameObject) {
        Scene.getActive().destroy(gameObject);
    }
    //endregion
}
