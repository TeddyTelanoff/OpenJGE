package openjge;

import com.treidex.opengje.core.Window;

public class JGEProgram {
    private Window window;
    private Thread thread;

    public static void main(String[] args) {
        JGEProgram program = new JGEProgram();
        program.start();
    }

    public void start() {
        thread = new Thread("Open Java Game Engine Program") {
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
        thread.start();
    }

    private void init() {
        window = new Window(this, "Debug", 1280, 720);
        window.create();
    }
    private void update() {
        window.update();
    }
    private void render() {
        window.render();
    }
    private void destroy() {
        window.destroy();
    }

    public Thread getThread() {
        return thread;
    }
}