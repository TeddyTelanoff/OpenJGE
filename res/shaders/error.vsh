#version 330 core

layout(location = 0) in vec3 pos;
layout(location = 1) in vec4 col;
layout(location = 2) in vec2 texCoord;

uniform mat4 transform;
uniform mat4 view;
uniform mat4 projection;

varying vec4 color;

void main() {
    gl_Position = projection * view * transform * vec4(pos, 1);
    color = col;
}