package openjge;

import com.treidex.opengje.core.*;
import openjge.graphics.*;

public class JGEProgram {
    private Thread thread;
    private Window window;
    private Renderer renderer;

    private Mesh mesh;

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
        renderer = new Renderer();

        mesh = new Mesh(Shader.DEFAULT,
            new Vertex[] {
                new Vertex(new Vector3(-0.75f,  0.5f, -1.0f)),
                new Vertex(new Vector3(-0.5f, -0.75f, 1.0f)),
                new Vertex(new Vector3(0.75f, -0.5f, 1.0f)),
                new Vertex(new Vector3(0.5f,  0.75f, -1.0f))
            }, new int[] {
                0, 1, 2,
                0, 3, 2
            },
            new Material(new Color(0, 1, 0, 0.5f))
        );
    }
    private void update() {
        window.update();
    }
    private void render() {
        window.render();

        renderer.renderMesh(mesh);
    }
    private void destroy() {
        window.destroy();

        mesh.destroy();
    }

    public Thread getThread() {
        return thread;
    }
}