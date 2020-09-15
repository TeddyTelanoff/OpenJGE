package com.treidex.opengje.core;

import openjge.JGEProgram;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL46.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Window {
    public JGEProgram program;
    private long window;
    private String title;
    private int width, height;
    private int[] posX = new int[1], posY = new int[1];
    private GLFWWindowSizeCallback resizeCallback;
    private boolean isResized;

    public Window(JGEProgram program, String title, int width, int height) {
        this.program = program;
        this.title = title;
        this.width = width;
        this.height = height;
    }

    public void create() {
        GLFWErrorCallback.createPrint(System.err).set();

        if (!glfwInit())
            throw new IllegalStateException("Unable to initialize GLFW");

        window = glfwCreateWindow(width, height, title, NULL, NULL);
        if (window == NULL)
            throw new RuntimeException("Failed to create GLFW window");

        GLFWVidMode vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        posX[0] = (vidMode.width() - width) / 2;
        posY[0] = (vidMode.height() - height) / 2;
        glfwSetWindowPos(window, posX[0], posY[0]);
        glfwMakeContextCurrent(window);
        GL.createCapabilities();

        glfwShowWindow(window);
    }

    public void update() {
        if (isResized) {
            glViewport(0, 0, width, height);

            isResized = false;
        }

        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glfwPollEvents();
    }

    public void render() {
        glfwSwapBuffers(window);
    }

    public void destroy() {
        CoreInput.destroy();
        resizeCallback.free();
        glfwWindowShouldClose(window);
        glfwDestroyWindow(window);
        glfwTerminate();
    }

    private void createCallbacks() {
        resizeCallback = GLFWWindowSizeCallback.create((long window, int newWidth, int newHeight) -> {
            width = newWidth;
            height = newHeight;

            isResized = true;
        });
        glfwSetWindowSizeCallback(window, resizeCallback);

        glfwSetKeyCallback(window, CoreInput.getKeyboardCallback());
        glfwSetCursorPosCallback(window, CoreInput.getMouseMoveCallback());
        glfwSetMouseButtonCallback(window, CoreInput.getMouseButtonsCallback());
        glfwSetScrollCallback(window, CoreInput.getMouseScrollCallback());
    }

    public boolean shouldClose() {
        return glfwWindowShouldClose(window);
    }

    public void setCursorState(int state) {
        glfwSetInputMode(window, GLFW_CURSOR, state);
    }

    public long getWindow() {
        return window;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String newTitle) {
        title = newTitle;
        glfwSetWindowTitle(window, newTitle);
    }
}