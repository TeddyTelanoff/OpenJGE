#version 330 core

varying vec4 color;
varying vec2 textureCoord;

uniform sampler2D tex;
uniform vec4 ambient;

void main() {
    gl_FragColor = vec4(1, 1, 1, 1) - ((vec4(1, 1, 1, 1) - texture(tex, textureCoord)) * (vec4(1, 1, 1, 1) - color));
}