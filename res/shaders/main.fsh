#version 330 core

in vec4 color;
in vec2 textureCoord;

out vec4 outColor;

uniform sampler2D tex;

void main() {
    outColor = texture(tex, textureCoord) + color;
}