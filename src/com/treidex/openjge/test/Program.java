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
            new GameObject("Player", new Transform(),
                new Component[]{
                    new CameraController(2, 1, 0.5f)
                },
                new GameObject[] {
                    new GameObject("Camera", new Transform(), Camera.mainCamera = new Camera(90, 0.01f, 100))
                }
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
