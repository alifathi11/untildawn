#ifdef GL_ES
precision mediump float;
#endif

varying vec4 v_color;
varying vec2 v_texCoords;

uniform sampler2D u_texture;

void main() {
    vec4 texColor = texture2D(u_texture, v_texCoords);
    float gray = dot(texColor.rgb, vec3(0.3, 0.59, 0.11)); // grayscale weight
    gl_FragColor = vec4(vec3(gray), texColor.a) * v_color;
}
