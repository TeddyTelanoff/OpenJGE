package openjge.graphics;

import openjge.Color;
import openjge.Vector2;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import static org.lwjgl.opengl.GL46.*;

public final class Material {
    public static final Material DEFAULT = new Material(Color.GRAY);

    private Shader shader;
    private Texture texture;
    private Vector2 textureSize;
    private String texturePath;
    private int textureID;

    private Color color;

    private boolean isTexture;

    private Material(Shader shader) {
        this.shader = shader;
    }

    public Material(Shader shader, Color color) {
        this(shader);

        this.color = color;

        isTexture = false;
    }

    public Material(Shader shader, String texturePath) {
        this(shader);

        this.texturePath = texturePath;

        try {
            texture = TextureLoader.getTexture(texturePath.split("[.]")[texturePath.split("[.]").length - 1],
                    Material.class.getResourceAsStream(texturePath), GL_NEAREST);
            textureSize = new Vector2(texture.getWidth(), texture.getHeight());
            textureID = texture.getTextureID();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }

        isTexture = true;
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

    public boolean isTexture() {
        return isTexture;
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
