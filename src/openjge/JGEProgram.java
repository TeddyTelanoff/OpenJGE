package openjge;

import com.treidex.openjge.core.*;
import openjge.graphics.Mesh;
import openjge.graphics.Shader;

public class JGEProgram {
    private static JGEProgram instance;

    private Thread mainThread, fixedUpdateThread;
    private Window window;
    private Renderer renderer;

    public final void start() {
        instance = this;
        mainThread = new Thread("Open Java Game Engine Program") {
            @Override
            public void run() {
                init();
                setup();
                fixedUpdateThread.start();
                while (true) {
                    if (window.shouldClose())
                        break;
                    update();
                    if (window.shouldClose())
                        break;
                    render();
                }
                destroy();
            }
        };
        fixedUpdateThread = new Thread("Fixed Update Thread (OpenJGE App)") {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(50);
                        if (window.shouldClose())
                            break;
                        fixedUpdate();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        mainThread.start();
    }

    public void init() {
        window = new Window(this, "Debug", 1280, 720);
        window.create();
        renderer = new Renderer();
    }
    public void setup() {
        Scene.getActive().init();
    }
    public void update() {
        CoreInput.update();
        window.update();
        Scene.getActive().update();
    }
    public void fixedUpdate() {
        window.update();
        Scene.getActive().fixedUpdate();
    }
    public void render() {
        window.render();
        Scene.getActive().render();
    }
    public void destroy() {
        window.destroy();
        Scene.getActive().destroy();
        for (Shader shader : Shader.getShaders())
            shader.destroy();
        System.exit(0);
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