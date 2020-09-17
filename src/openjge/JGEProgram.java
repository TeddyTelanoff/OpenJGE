package openjge;

import com.treidex.openjge.core.*;
import openjge.graphics.Shader;

public final class JGEProgram {
    private static JGEProgram instance;

    private Thread mainThread, fixedUpdateThread;
    private Window window;
    private Renderer renderer;
    private JGEProgramI inface;
    private String title;
    private int width, height;
    private long fixedUpdateIntervalMilis;

    public interface JGEProgramI {
        void onStart();
    }

    public JGEProgram(JGEProgramI inface, String title, int width, int height, double fixedUpdateIntervalSeconds) {
        this.inface = inface;
        this.title = title;
        this.width = width;
        this.height = height;

        fixedUpdateIntervalMilis = (long) (fixedUpdateIntervalSeconds * 1000);
    }

    public JGEProgram(JGEProgramI inface, String title, int width, int height) {
        this(inface, title, width, height, 0.05);
    }

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
                        Thread.sleep(fixedUpdateIntervalMilis);
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
        Debug.getDebuging();
        if (Debug.isDebuging())
            System.out.println("Debugging Enabled!");

        window = new Window(this, title, width, height);
        window.create();
        renderer = new Renderer();
    }
    public void setup() {
        inface.onStart();
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