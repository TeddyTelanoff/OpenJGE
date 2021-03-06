package openjge.graphics;

import openjge.Component;
import openjge.Vector2;
import openjge.Vector3;
import org.lwjgl.assimp.AIFace;
import org.lwjgl.assimp.AIMesh;
import org.lwjgl.assimp.AIScene;
import org.lwjgl.assimp.AIVector3D;
import org.lwjgl.assimp.Assimp;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL46.*;
import static org.lwjgl.system.MemoryUtil.*;

public final class Mesh extends Component {
    private Vertex[] vertices;
    private int[] indices;
    private Material material;
    private int vao, pbo, ibo, cbo, tbo;

    public Mesh(Vertex[] vertices, int[] indices, Material material) {
        this.vertices = vertices;
        this.indices = indices;
        this.material = material;

        create();
    }

    public static Mesh loadModel(String filePath, Material material) {
        AIScene scene = Assimp.aiImportFile(filePath, Assimp.aiProcess_JoinIdenticalVertices | Assimp.aiProcess_Triangulate);

        if (scene == null) {
            System.err.println("Couldn't load model at " + filePath);
            return null;
        }

        AIMesh mesh = AIMesh.create(scene.mMeshes().get(0));
        int vertexCount = mesh.mNumVertices();

        AIVector3D.Buffer vertices = mesh.mVertices();
        AIVector3D.Buffer normals = mesh.mNormals();

        Vertex[] vertexList = new Vertex[vertexCount];

        for (int i = 0; i < vertexCount; i++) {
            AIVector3D vertex = vertices.get(i);
            Vector3 meshVertex = new Vector3(vertex.x(), vertex.y(), vertex.z());

            AIVector3D normal = normals.get(i);
            Vector3 meshNormal = new Vector3(normal.x(), normal.y(), normal.z());

            Vector2 meshTextureCoord = new Vector2(0, 0);
            if (mesh.mNumUVComponents().get(0) != 0) {
                AIVector3D texture = mesh.mTextureCoords(0).get(i);
                meshTextureCoord.x = texture.x();
                meshTextureCoord.y = texture.y();
            }

            vertexList[i] = new Vertex(meshVertex, meshNormal, meshTextureCoord);
        }

        int faceCount = mesh.mNumFaces();
        AIFace.Buffer indices = mesh.mFaces();
        int[] indicesList = new int[faceCount * 3];

        for (int i = 0; i < faceCount; i++) {
            AIFace face = indices.get(i);
            indicesList[i * 3 + 0] = face.mIndices().get(0);
            indicesList[i * 3 + 1] = face.mIndices().get(1);
            indicesList[i * 3 + 2] = face.mIndices().get(2);
        }

        return new Mesh(vertexList, indicesList, material);
    }

    @Override
    public void onDestroy() {
        destroy();
    }

    private void create() {
        // Vertex
        vao = glGenVertexArrays();
        glBindVertexArray(vao);

        // Position
        FloatBuffer positionBuffer = memAllocFloat(vertices.length * 3);
        float[] positionData = new float[vertices.length * 3];
        for (int i = 0; i < vertices.length; i++) {
            positionData[i * 3 + 0] = vertices[i].getPosition().x;
            positionData[i * 3 + 1] = vertices[i].getPosition().y;
            positionData[i * 3 + 2] = vertices[i].getPosition().z;
        }
        positionBuffer.put(positionData).flip();

        pbo = storeData(positionBuffer, 0, 3);

        // Color
        FloatBuffer colorBuffer = memAllocFloat(vertices.length * 4);
        float[] colorData = new float[vertices.length * 4];
        for (int i = 0; i < vertices.length; i++) {
            colorData[i * 4 + 0] = material.getColor().r;
            colorData[i * 4 + 1] = material.getColor().g;
            colorData[i * 4 + 2] = material.getColor().b;
            colorData[i * 4 + 3] = material.getColor().a;
        }
        colorBuffer.put(colorData).flip();

        cbo = storeData(colorBuffer, 1, 4);

        // Texture
        FloatBuffer textureBuffer = memAllocFloat(vertices.length * 2);
        float[] textureData = new float[vertices.length * 2];
        for (int i = 0; i < vertices.length; i++) {
            textureData[i * 2 + 0] = vertices[i].getTextureCoord().x;
            textureData[i * 2 + 1] = vertices[i].getTextureCoord().y;
        }
        textureBuffer.put(textureData).flip();

        tbo = storeData(textureBuffer, 2, 2);

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
