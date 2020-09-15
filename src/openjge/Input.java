package openjge;

import com.treidex.opengje.core.CoreInput;

import static org.lwjgl.glfw.GLFW.*;

public class Input {
    private Input() {}

    public static boolean isKeyDown(int key) {
        return CoreInput.getKeys()[key] == GLFW_PRESS;
    }

    public static boolean isKeyHeld(int key) {
        return CoreInput.getKeys()[key] == GLFW_REPEAT;
    }

    public static boolean isKeyUp(int key) {
        return CoreInput.getKeys()[key] == GLFW_RELEASE;
    }

    public static boolean isMouseDown(int button) {
        return CoreInput.getButtons()[button] == GLFW_PRESS;
    }

    public static boolean isMouseHeld(int button) {
        return CoreInput.getButtons()[button] == GLFW_REPEAT;
    }

    public static boolean isMouseUp(int button) {
        return CoreInput.getButtons()[button] == GLFW_RELEASE;
    }
}