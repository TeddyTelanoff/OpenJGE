package openjge.graphics;

import openjge.Vector2;
import openjge.Vector3;

public class Vertex {
    private Vector3 position, normal;
    private Vector2 textureCoord;

    public Vertex(Vector3 position, Vector3 normal, Vector2 textureCoord) {
        this.position = position;
        this.normal = normal;
        this.textureCoord = textureCoord;
    }

    public Vertex(Vector3 position, Vector2 textureCoord) {
        this(position, Vector3.ZERO, textureCoord);
    }

    public Vertex(Vector3 position) {
        this(position, Vector3.ZERO, Vector2.ZERO);
    }

    public Vector3 getPosition() {
        return position;
    }

    public Vector3 getNormal() {
        return normal;
    }

    public Vector2 getTextureCoord() {
        return textureCoord;
    }
}
