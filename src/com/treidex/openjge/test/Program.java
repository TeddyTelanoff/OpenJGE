package com.treidex.openjge.test;

import openjge.*;
import openjge.graphics.*;

public class Program {
    static JGEProgram program;

    public static void main(String[] args) {
        program = new JGEProgram(() -> {
            program.getWindow().setBackgroundColor(new Color(57/255, 57/255, 57/255));
            Scene mainScene = new Scene("Main Scene",
                new GameObject("Player", new Transform(),
                    new Component[]{
                        new CameraController(2, 1, 0.5f)
                    },
                    new GameObject[] {
                        new GameObject("Camera", new Transform(), new Camera(70, 0.01f, 1000))
                    }
                ),
                new GameObject("Model", new Transform(new Vector3(0, 0, 0), new Vector3(90, 0, 0), new Vector3(0.125f, 0.125f, 0.125f)),
                    Mesh.loadModel("res/models/test.stl", new Material(Shader.DEFAULT, Color.WHITE, "/textures/test.png"))
                ),
                new GameObject("Model", new Transform(),
                    Mesh.loadModel("res/models/test.obj", new Material(Shader.DEFAULT, Color.TURQUOISE, "/textures/test.png")),
                    new SpinnyBoy()
                )
            );
            Scene.setActive(mainScene);

            Debug.log(Matrix4.view(new Vector3(1, 1, 1), new Vector3(0, 0, 0)));
        }, "Open Java Game Engine Test", 1280, 720, 1d/20);
        program.start();
    }
}
