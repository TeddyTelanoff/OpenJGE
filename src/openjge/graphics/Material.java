package openjge.graphics;

import openjge.Color;
import openjge.Vector2;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import java.io.IOException;

import static org.lwjgl.opengl.GL46.*;

public final class Material {
    public static final Material DEFAULT = new Material(Color.GRAY);

    private Shader shader;
    private Texture texture;
    private Vector2 textureSize;
    private String texturePath;
    private int textureID;

    private Color color;

    private Material(Shader shader) {
        this.shader = shader;
    }

    public Material(Shader shader, Color color, String texturePath) {
        this(shader);

        this.color = color;
        this.texturePath = texturePath;

        try {
            texture = TextureLoader.getTexture(texturePath.split("[.]")[1], Material.class.getResourceAsStream(texturePath), GL_NEAREST);
            textureSize = new Vector2(texture.getWidth(), texture.getHeight());
            textureID = texture.getTextureID();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Material(Shader shader, Color color) {
        this(shader);

        this.color = color;
    }

    public Material(Shader shader, String texturePath) {
        this(shader, Color.BLACK, texturePath);
    }

    public Material(Color color) {
        this(Shader.DEFAULT, color);
    }

    public Material(String texturePath) {
        this(Shader.DEFAULT, texturePath);
    }

    public void destroy() {
        glDeleteTextures(textureID);
    }

    public Shader getShader() {
        return shader;
    }

    public Color getColor() {
        return color;
    }

    public Texture getTexture() {
        return texture;
    }

    public String getTexturePath() {
        return texturePath;
    }

    public Vector2 getTextureSize() {
        return textureSize;
    }

    public int getTextureID() {
        return textureID;
    }
}
