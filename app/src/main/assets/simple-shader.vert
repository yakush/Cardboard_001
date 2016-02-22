
attribute vec4 a_vertex;

// matrices
uniform mat4 u_model;
uniform mat4 u_view;
uniform mat4 u_projection;

varying vec4 v_color;

void main() {

   // to frag
   v_color = vec4(0.5+a_vertex.x,0.5+a_vertex.y,0.5,1.0);

   // output
   gl_Position = u_projection * u_view * u_model * a_vertex;
}