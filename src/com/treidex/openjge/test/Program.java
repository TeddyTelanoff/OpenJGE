package com.treidex.openjge.test;

import openjge.*;
import openjge.graphics.*;

public class Program {
    static JGEProgram program;

    public static void main(String[] args) {
        program = new JGEProgram(() -> {
            program.getWindow().setBackgroundColor(Color.BLACK);
            Scene mainScene = new Scene("Main Scene",
                new GameObject("Player", new Transform(),
                    new Component[]{
                        new CameraController(2, 1, 0.5f)
                    },
                    new GameObject[] {
                        new GameObject("Camera", new Transform(), new Camera(90, 0.01f, 100))
                    }
                ),
                new GameObject("Camera", new Transform(new Vector3(0, 0, 0), new Vector3(0, 0, 0), new Vector3(3, 3, 3)),
                    Mesh.loadModel("res/models/test.obj", new Material(Shader.DEFAULT, "/textures/test.png")),
                    new SpinnyBoy()
                )
            );
            Scene.setActive(mainScene);
        }, "Open Java Game Engine Test", 1280, 720, 1d/20);
        program.start();
    }
}
