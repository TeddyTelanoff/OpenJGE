package openjge;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import static org.lwjgl.opengl.GL46.*;

public class Material {
    private Texture texture;
    private Vector2 textureSize;
    private String texturePath;
    private int textureID;

    private Color color;

    private boolean isTexture;

    public Material(Color color) {
        this.color = color;

        isTexture = false;
    }

    public Material(String texturePath) {
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

    public void destroy() {
        glDeleteTextures(textureID);
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
