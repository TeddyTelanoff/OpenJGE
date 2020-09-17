package com.treidex.openjge.test;

import openjge.Component;
import openjge.Input;
import openjge.Vector3;

import static org.lwjgl.glfw.GLFW.*;

public class CameraController extends Component {
    public Vector3 vel;
    public float sens, speed, speedDamp;

    public CameraController(float sens, float speed, float speedDamp) {
        this.sens = sens;
        this.speed = speed;
        this.speedDamp = speedDamp;

        vel = new Vector3();

        Input.setCursorMode(GLFW_CURSOR_DISABLED);
    }

    @Override
    public void update() {
        transform.rotation.y -= Input.getDeltaMousePosition().x * sens;
        if (transform.rotation.y >= 360)
            transform.rotation.y = 0;
        if (transform.rotation.y <= -360)
            transform.rotation.y = 0;
        gameObject().children[0].transform.rotation.x -= Input.getDeltaMousePosition().y * sens;
        if (gameObject().children[0].transform.rotation.x >= 90)
            gameObject().children[0].transform.rotation.x = 90;
        if (gameObject().children[0].transform.rotation.x <= -90)
            gameObject().children[0].transform.rotation.x = -90;
    }

    @Override
    public void fixedUpdate() {
        if (Input.isKeyHeld(GLFW_KEY_A) || Input.isKeyDown(GLFW_KEY_A))
            vel.add(transform.getRight().mul(-speed));
        if (Input.isKeyHeld(GLFW_KEY_D) || Input.isKeyDown(GLFW_KEY_D))
            vel.add(transform.getRight().mul(speed));
        if (Input.isKeyHeld(GLFW_KEY_W) || Input.isKeyDown(GLFW_KEY_W))
            vel.add(transform.getForward().mul(speed));
        if (Input.isKeyHeld(GLFW_KEY_S) || Input.isKeyDown(GLFW_KEY_S))
            vel.add(transform.getForward().mul(-speed));
        if (Input.isKeyHeld(GLFW_KEY_SPACE) || Input.isKeyDown(GLFW_KEY_SPACE))
            vel.add(transform.getUp().mul(speed));
        if (Input.isKeyHeld(GLFW_KEY_LEFT_SHIFT) || Input.isKeyDown(GLFW_KEY_LEFT_SHIFT))
            vel.add(transform.getUp().mul(-speed));

        transform.position.add(vel);
        vel.mul(speedDamp);
    }
}
