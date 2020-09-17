package com.treidex.openjge.test;

import openjge.*;

public class SpinnyBoy extends Component {
    private float tmp;

    @Override
    public void fixedUpdate() {
        transform.rotation.y += 3;
        tmp += 0.02f;
        transform.rotation.x = (float) Math.sin(tmp) * 10;
    }
}
