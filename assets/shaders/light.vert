attribute vec4 a_position;
attribute vec2 a_texCoord0;

uniform mat4 u_projTrans;

varying vec2 v_texCoords;
varying vec4 v_position;

void main() {
    v_texCoords = a_texCoord0;
    v_position = u_projTrans * a_position;
    gl_Position = v_position;
}
