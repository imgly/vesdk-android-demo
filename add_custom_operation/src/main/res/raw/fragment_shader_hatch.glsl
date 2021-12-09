precision mediump float;

varying vec2 v_texCoord;
uniform #INPUT_TYPE u_image;

uniform float width;
uniform float height;
uniform float delta;

uniform float intensity;

const float EPSILON = 0.0000001;
const vec3 weights = vec3(0.3, 0.59, 0.1);

vec4 getColorAt(float x, float y) {
    return clamp(texture2D(u_image, vec2(x, y) / vec2(width, height)), 0.0, 1.0);
}

void main() {

    float x = v_texCoord.x * float(width);
    float y = v_texCoord.y * float(height);

    vec4 color = getColorAt(x, y);

    float lum = clamp(dot(color.rgb, weights), 0.0, 1.0);

    // sample neightbors
    vec3 topLeft  = getColorAt(x - 1.0, y - 1.0).rgb;
    vec3 top      = getColorAt(x      , y - 1.0).rgb;
    vec3 topRight = getColorAt(x + 1.0, y - 1.0).rgb;

    vec3 bottomLeft  = getColorAt(x - 1.0, y + 1.0).rgb;
    vec3 bottom      = getColorAt(x      , y + 1.0).rgb;
    vec3 bottomRight = getColorAt(x + 1.0, y + 1.0).rgb;

    vec3 left  = getColorAt(x - 1.0, y).rgb;
    vec3 right = getColorAt(x + 1.0, y).rgb;

    float horizontalSobel = dot( topLeft + 2.0 * top  + topRight   - bottomLeft - 2.0 * bottom - bottomRight, weights);
    float verticalSobel   = dot(-topLeft - 2.0 * left - bottomLeft + topRight   + 2.0 * right  + bottomRight, weights);
    float edge = sqrt(horizontalSobel * horizontalSobel + verticalSobel * verticalSobel);

    float flippedX = height - x - 1.0;

    bool isPixelBlack =
        // filter noise
        (edge > 0.4 * (2.0 - intensity))
        // if dark enough use first hatch stage, that is strokes with distance of 'delta'.
        || (lum < 0.38 * (2.0 - intensity) && mod(abs(flippedX - y), delta) <= 1.5)
        // if even darker use second hatch stage, that is strokes with distance of a third 'delta'.
        || (lum < 0.25 * (2.0 - intensity) && mod(abs(flippedX - y), delta * 0.3) <= 1.5)
        // if too dark, just output black
        || (lum < 0.18 * (2.0 - intensity));

    vec4 outputColor = vec4(
        !isPixelBlack,
        !isPixelBlack,
        !isPixelBlack,
        1.0
    );

    gl_FragColor = outputColor;
}