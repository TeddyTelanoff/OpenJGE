in vec4 color;
in vec2 textureCoord;

out vec4 outColor;

void main() {
    outColor = texture(tex, textureCoord) + color;
}