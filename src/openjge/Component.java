package openjge;

import openjge.util.ArrayUtil;

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
    public final <T extends Component> T[] getComponents(Class<T> tclass) {
        return gameObject.<T> getComponents(tclass);
    }

    public final <T extends Component> T getComponent(Class<T> tclass) {
        return gameObject.<T> getComponent(tclass);
    }

    public final <T extends Component> boolean hasComponent(Class<T> tclass) {
        return gameObject.<T> hasComponent(tclass);
    }

    public void addComponent(Component componentToAdd) {
        gameObject.addComponent(componentToAdd);
    }

    public void addComponents(Component... componentsToAdd) {
        gameObject.addComponents(componentsToAdd);
    }

    public final void destroy(Component component) {
        gameObject.destroy(component);
    }

    public final void destroy(GameObject gameObject) {
        gameObject.destroy(gameObject);
    }

    public void destroyAll(GameObject gameObject) {
        gameObject.destroyAll(gameObject);
    }

    public void destroyAll() {
        gameObject.destroyAll();
    }

    public final void startCoroutine(Runnable coroutine) {
        gameObject.startCoroutine(coroutine);
    }

    public void stopCoroutine(Runnable coroutine) {
        gameObject.stopCoroutine(coroutine);
    }

    public void stopAllCoroutines() {
        gameObject.stopAllCoroutines();
    }
    //endregion

    public final GameObject gameObject() {
        return gameObject;
    }

    public final void setGameObject(GameObject gameObject) {
        if (!goChanged) {
            this.gameObject = gameObject;
            transform = gameObject.transform;

            goChanged = true;
        }
    }
}
