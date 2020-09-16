package openjge.graphics;

import openjge.Component;
import openjge.Material;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL46.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Mesh extends Component {
    private Shader shader;
    private Vertex[] vertices;
    private int[] indices;
    private Material material;
    private int vao, pbo, ibo, cbo, tbo;

    public Mesh(Shader shader, Vertex[] vertices, int[] indices, Material material) {
        this.shader = shader;
        this.vertices = vertices;
        this.indices = indices;
        this.material = material;

        create();
    }

    private void create() {
        // Vertex
        vao = glGenVertexArrays();
        glBindVertexArray(vao);

        // Position
        FloatBuffer positionBuffer = memAllocFloat(vertices.length * 3);
        float[] positionData = new float[vertices.length * 3];
        for (int i = 0; i < vertices.length; i++) {
            positionData[i * 3] = vertices[i].getPosition().x;
            positionData[i * 3 + 1] = vertices[i].getPosition().y;
            positionData[i * 3 + 2] = vertices[i].getPosition().z;
        }
        positionBuffer.put(positionData).flip();

        pbo = storeData(positionBuffer, 0, 3);

        if (material.isTexture()) {
            // Texture
            FloatBuffer textureBuffer = memAllocFloat(vertices.length * 2);
            float[] textureData = new float[vertices.length * 2];
            for (int i = 0; i < vertices.length; i++) {
                textureData[i * 2] = vertices[i].getTextureCoord().x;
                textureData[i * 2 + 1] = vertices[i].getTextureCoord().y;
            }
            textureBuffer.put(textureData).flip();

            tbo = storeData(textureBuffer, 2, 2);
        } else {
            // Color
            FloatBuffer colorBuffer = memAllocFloat(vertices.length * 4);
            float[] colorData = new float[vertices.length * 4];
            for (int i = 0; i < vertices.length; i++) {
                colorData[i * 4] = material.getColor().r;
                colorData[i * 4 + 1] = material.getColor().g;
                colorData[i * 4 + 2] = material.getColor().b;
                colorData[i * 4 + 3] = material.getColor().a;
            }
            colorBuffer.put(colorData).flip();

            cbo = storeData(colorBuffer, 1, 4);
        }

        // Indices
        IntBuffer indicesBuffer = memAllocInt(indices.length);
        indicesBuffer.put(indices).flip();

        ibo = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL_STATIC_DRAW);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
    }

    private int storeData(FloatBuffer buffer, int index, int size) {
        int bufferID = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, bufferID);
        glBufferData(GL_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
        glVertexAttribPointer(index, size, GL_FLOAT, false, 0, 0);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        return bufferID;
    }

    public void destroy() {
        glDeleteBuffers(pbo);
        glDeleteBuffers(cbo);
        glDeleteBuffers(ibo);
        glDeleteBuffers(tbo);

        glDeleteVertexArrays(vao);

        material.destroy();
        shader.destroy();
    }

    public Shader getShader() {
        return shader;
    }

    public Vertex[] getVertices() {
        return vertices;
    }

    public int[] getIndices() {
        return indices;
    }

    public int getVAO() {
        return vao;
    }

    public int getPBO() {
        return pbo;
    }

    public int getCBO() {
        return cbo;
    }

    public int getTBO() {
        return tbo;
    }

    public int getIBO() {
        return ibo;
    }

    public Material getMaterial() {
        return material;
    }
}
