package openjge.graphics;

import openjge.Matrix4;
import openjge.Vector2;
import openjge.Vector3;
import openjge.Vector4;
import openjge.util.FileUtil;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL46.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Shader {
    private static final List<Shader> shaders = new ArrayList<Shader>();
    public static final Shader DEFAULT = new Shader("/shaders/main.vsh", "/shaders/main.fsh");
    public static final Shader ERROR = new Shader("/shaders/error.vsh", "/shaders/error.fsh");

    private String vertexPath, fragmentPath, vertexFile, fragmentFile;
    private int vertexID, fragmentID, programID;

    public Shader(String vertexPath, String fragmentPath) {
        this.vertexPath = vertexPath;
        this.fragmentPath = fragmentPath;

        vertexFile = FileUtil.readFile(vertexPath);
        fragmentFile = FileUtil.readFile(fragmentPath);

        create();
        shaders.add(this);
    }

    private void create() {
        programID = glCreateProgram();
        vertexID = glCreateShader(GL_VERTEX_SHADER);

        glShaderSource(vertexID, vertexFile);
        glCompileShader(vertexID);
        if (glGetShaderi(vertexID, GL_COMPILE_STATUS) == GL_FALSE) {
            System.err.println("Error Compiling Vertex Shader '" + vertexPath + "': " + glGetShaderInfoLog(vertexID));
            error();
            return;
        }

        fragmentID = glCreateShader(GL_FRAGMENT_SHADER);

        glShaderSource(fragmentID, fragmentFile);
        glCompileShader(fragmentID);
        if (glGetShaderi(fragmentID, GL_COMPILE_STATUS) == GL_FALSE) {
            System.err.println("Error Compiling Fragment Shader '" + fragmentPath + "': " + glGetShaderInfoLog(fragmentID));
            error();
            return;
        }

        glAttachShader(programID, vertexID);
        glAttachShader(programID, fragmentID);
        glLinkProgram(programID);
        if (glGetProgrami(programID, GL_LINK_STATUS) == GL_FALSE) {
            System.err.println("Error Linking Shader: " + glGetShaderInfoLog(vertexID));
            error();
            return;
        }

        glValidateProgram(programID);
        if (glGetProgrami(programID, GL_VALIDATE_STATUS) == GL_FALSE) {
            System.err.println("Error Validating Program: " + glGetProgramInfoLog(programID));
        }
    }

    public int getUniformLocation(String name) {
        return glGetUniformLocation(programID, name);
    }

    public void setUniform(String name, float value) {
        glUniform1f(getUniformLocation(name), value);
    }

    public void setUniform(String name, int value) {
        glUniform1i(getUniformLocation(name), value);
    }

    public void setUniform(String name, boolean value) {
        glUniform1i(getUniformLocation(name), value ? 1 : 0);
    }

    public void setUniform(String name, Vector2 value) {
        glUniform2f(getUniformLocation(name), value.x, value.y);
    }

    public void setUniform(String name, Vector3 value) {
        glUniform3f(getUniformLocation(name), value.x, value.y, value.z);
    }

    public void setUniform(String name, Vector4 value) {
        glUniform4f(getUniformLocation(name), value.x, value.y, value.z, value.w);
    }

    public void setUniform(String name, Matrix4 value) {
        FloatBuffer matrix = memAllocFloat(Matrix4.SIZE * Matrix4.SIZE);
        matrix.put(value.getAll()).flip();
        glUniformMatrix4fv(getUniformLocation(name), true, matrix);
    }

    public static List<Shader> getShaders() {
        return shaders;
    }

    public void bind() {
        glUseProgram(programID);
    }

    public void unBind() {
        glUseProgram(0);
    }

    public void destroy() {
        glDetachShader(programID, vertexID);
        glDetachShader(programID, fragmentID);
        glDeleteShader(vertexID);
        glDeleteShader(fragmentID);
        glDeleteProgram(programID);
    }

    private void error() {
        vertexPath = ERROR.vertexPath;
        fragmentPath = ERROR.fragmentPath;

        vertexFile = ERROR.vertexFile;
        fragmentFile = ERROR.fragmentFile;

        vertexID = ERROR.vertexID;
        fragmentID = ERROR.fragmentID;
        programID = ERROR.programID;
    }
}