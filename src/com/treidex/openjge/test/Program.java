package com.treidex.openjge.test;

import openjge.*;
import openjge.graphics.*;

public class Program extends JGEProgram {
    public static void main(String[] args) {
        Program program = new Program();
        program.start();
    }

    public Mesh mesh;

    public void init() {
        super.init();

        mesh = new Mesh(new Vertex[] {
            new Vertex(new Vector3(-0.5f,  0.5f)),
            new Vertex(new Vector3(-0.5f, -0.5f)),
            new Vertex(new Vector3( 0.5f, -0.5f))
        }, new int[] {
            0, 1, 2
        }, new Material(new Color(0, 1, 0)));

        Scene mainScene = new Scene("Main Scene",
            new GameObject("Camera", new Transform(new Vector3(0, 0, 0), new Vector3(0, 0, 0), new Vector3(1, 1, 1)),
                Camera.mainCamera = new Camera(90, 0.01f, 100),
                new CameraController(2, 1f)
            ),
            new GameObject("Camera", new Transform(new Vector3(0, 0, 0), new Vector3(0, 0, 0), new Vector3(3, 3, 3)),
                Mesh.loadModel("res/models/test.obj", new Material(Color.PURPLE))
            )
        );
        Scene.setActive(mainScene);
    }

    @Override
    public void render() {
        super.render();


    }
}
