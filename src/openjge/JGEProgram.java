package openjge;

import com.treidex.opengje.core.*;
import openjge.graphics.*;

public class JGEProgram {
    private static JGEProgram instance;

    private Thread mainThread, fixedUpdateThread;
    private Window window;
    private Renderer renderer;

    public static void main(String[] args) {

    }

    public void start() {
        mainThread = new Thread("Open Java Game Engine Program") {
            @Override
            public void run() {
                init();
                while (!window.shouldClose()) {
                    update();
                    render();
                }
                destroy();
            }
        };
        fixedUpdateThread = new Thread("Fixed Update Thread (OpenJGE App)") {
            @Override
            public void run() {
                while (!window.shouldClose()) {
                    try {
                        Thread.sleep(50);
                        fixedUpdate();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        mainThread.start();
        fixedUpdateThread.start();
    }

    private void init() {
        window = new Window(this, "Debug", 1280, 720);
        window.create();
        renderer = new Renderer();
    }
    private void update() {
        window.update();
        Scene.getActive().update();
    }
    private void fixedUpdate() {
        window.update();
        Scene.getActive().fixedUpdate();
    }
    private void render() {
        window.render();
        Scene.getActive().render();
    }
    private void destroy() {
        window.destroy();
        Scene.getActive().destroy();
    }

    public Thread getMainThread() {
        return mainThread;
    }

    public Thread getFixedUpdateThread() {
        return fixedUpdateThread;
    }

    public Renderer getRenderer() {
        return renderer;
    }

    public Window getWindow() {
        return window;
    }

    public static JGEProgram getInstance() {
        return instance;
    }
}