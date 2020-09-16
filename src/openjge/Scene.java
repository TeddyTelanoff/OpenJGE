package openjge;

public final class Scene {
    private static Scene active;
    public final String name;
    public GameObject[] gameObjects;

    public Scene(String name, GameObject... gameObjects) {
        this.name = name;
        this.gameObjects = gameObjects;
    }

    public static void setActive(Scene scene) {
        if (getActive() != null)
            getActive().destroy();
        active = scene;
    }

    public void update() {
        for (GameObject gameObject : gameObjects)
            gameObject.update();
        lateUpdate();
    }

    public void fixedUpdate() {
        for (GameObject gameObject : gameObjects)
            gameObject.fixedUpdate();
    }

    private void lateUpdate() {
        for (GameObject gameObject : gameObjects)
            gameObject.lateUpdate();
    }

    public void render() {

    }

    public void destroy() {
        for (GameObject gameObject : gameObjects)
            gameObject.destroy();
    }

    public static Scene getActive() {
        return active;
    }
}
