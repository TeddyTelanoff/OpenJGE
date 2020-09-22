package com.treidex.openjge.core;

import openjge.JGEProgram;
import org.lwjgl.glfw.*;

import static org.lwjgl.glfw.GLFW.*;

public class CoreInput {
    private static int[] keys = new int[GLFW_KEY_LAST];
    private static int[] buttons = new int[GLFW_MOUSE_BUTTON_LAST];
    private static double[] mouseX = new double[1], mouseY = new double[1];
    private static double pmouseX, pmouseY;
    private static double deltaMouseX, deltaMouseY;
    private static double scrollX, scrollY;
    private static double deltaScrollX, deltaScrollY;

    private static GLFWKeyCallback keyboard;
    private static GLFWCursorPosCallback mouseMove;
    private static GLFWMouseButtonCallback mouseButtons;
    private static GLFWScrollCallback mouseScroll;

    private CoreInput() {}

    public static void init() {
        glfwGetCursorPos(JGEProgram.getInstance().getWindow().getWindow(), mouseX, mouseY);

        keyboard = GLFWKeyCallback.create((long window, int key, int scancode, int action, int mods) -> keys[key] = action);

        mouseMove = GLFWCursorPosCallback.create((long window, double mouseX, double mouseY) -> {
            CoreInput.mouseX[0] = mouseX;
            CoreInput.mouseY[0] = mouseY;
        });

        mouseButtons = GLFWMouseButtonCallback.create((long window, int button, int action, int mods) -> buttons[button] = action);

        mouseScroll = GLFWScrollCallback.create((long window, double deltaX, double deltaY) -> {
            scrollX += deltaX;
            scrollY += deltaY;

            deltaScrollX = deltaX;
            deltaScrollY = deltaY;
        });
    }

    public static void update() {
        deltaMouseX = mouseX[0] - pmouseX;
        deltaMouseY = mouseY[0] - pmouseY;

        pmouseX = mouseX[0];
        pmouseY = mouseY[0];

        for (int key : keys)
            key = GLFW_KEY_UNKNOWN;
    }

    public static void setCursorMode(int mode) {
        GLFW.glfwSetInputMode(JGEProgram.getInstance().getWindow().getWindow(), GLFW.GLFW_CURSOR, mode);
    }

    public static void destroy() {
        keyboard.free();
        mouseMove.free();
        mouseButtons.free();
        mouseScroll.free();
    }

    public static int[] getKeys() {
        return keys;
    }

    public static int[] getButtons() {
        return buttons;
    }

    public static double getMouseX() {
        return mouseX[0];
    }

    public static double getMouseY() {
        return mouseY[0];
    }

    public static double getDeltaMouseX() {
        return deltaMouseX;
    }

    public static double getDeltaMouseY() {
        return deltaMouseY;
    }

    public static double getScrollX() {
        return scrollX;
    }

    public static double getScrollY() {
        return scrollY;
    }

    public static double getDeltaScrollX() {
        return deltaScrollX;
    }

    public static double getDeltaScrollY() {
        return deltaScrollY;
    }

    public static GLFWKeyCallback getKeyboardCallback() {
        return keyboard;
    }

    public static GLFWCursorPosCallback getMouseMoveCallback() {
        return mouseMove;
    }

    public static GLFWMouseButtonCallback getMouseButtonsCallback() {
        return mouseButtons;
    }

    public static GLFWScrollCallback getMouseScrollCallback() {
        return mouseScroll;
    }
}