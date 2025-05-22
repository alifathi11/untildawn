#ifdef GL_ES
precision mediump float;
#endif

uniform sampler2D u_texture;
uniform vec2 u_playerPos;      // in screen coordinates
uniform float u_radius;
uniform float u_viewportHeight;

varying vec2 v_texCoords;

void main() {
    vec4 baseColor = texture2D(u_texture, v_texCoords);

    // Convert current fragment to screen space
    vec2 screenPos = vec2(gl_FragCoord.x, u_viewportHeight - gl_FragCoord.y);

    // Distance from the center of the light
    float dist = distance(screenPos, u_playerPos);

    // Soft circular falloff with lower intensity
    float glow = smoothstep(u_radius, 0.0, dist) * 0.2;  // 20% opacity max

    // Lighten the area slightly with white (like a soft flashlight)
    vec3 finalColor = mix(baseColor.rgb, vec3(1.0), glow);

    gl_FragColor = vec4(finalColor, baseColor.a);
}
