package openjge;

import openjge.graphics.Mesh;

import java.lang.reflect.Array;
import java.util.Arrays;

public final class Scene {
    private static Scene active;
    public final String name;
    private GameObject[] gameObjects;

    public Scene(String name, GameObject... gameObjects) {
        this.name = name;
        this.gameObjects = gameObjects;
    }

    public void init() {
        for (GameObject gameObject : gameObjects)
            gameObject.init();
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
        for (GameObject gameObject : gameObjects)
            if (gameObject.hasComponent(Mesh.class))
                JGEProgram.getInstance().getRenderer().renderMesh(gameObject.getComponent(Mesh.class));
    }

    public void destroy(GameObject gameObject) {
        int index = ArrayUtil.indexOf(gameObjects, gameObject);
        Debug.Assert(index != -1, "Game Object doesn't exist in the Scene!");
        gameObjects[index].destroy();
        gameObjects = ArrayUtil.remove(GameObject.class, gameObjects, index);
    }

    public void destroyAll() {
        for (GameObject gameObject : gameObjects)
            gameObject.destroy();
    }

    public void startCoroutine(Runnable coroutine) {
        Coroutine.startCoroutine(coroutine);
    }

    public void stopCoroutine(Runnable coroutine) {
        Coroutine.stopCoroutine(coroutine);
    }

    public void stopAllCoroutines() {
        Coroutine.stopAllCoroutines();
    }

    public GameObject[] gameObjects() {
        return gameObjects;
    }

    public static Scene getActive() {
        return active;
    }

    public static void setActive(Scene scene) {
        Debug.Assert(scene != null, "The Active Scene must not be null!");

        if (getActive() != null)
            getActive().destroyAll();
        active = scene;
    }
}
