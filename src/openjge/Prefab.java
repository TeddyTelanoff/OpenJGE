package openjge;

import openjge.GameObject;

public interface Prefab {
    GameObject instanciate(String name, int layer, Transform transform, Object... args);
}