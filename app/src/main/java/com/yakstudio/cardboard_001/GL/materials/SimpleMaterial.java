package com.yakstudio.cardboard_001.GL.materials;

import android.content.Context;

import java.io.IOException;

/**
 * Created by Yak on 21/02/2016.
 */
public class SimpleMaterial extends GlMaterial {

    public SimpleMaterial(Context context) {
        // compile shader...
        try {
            shader= new Gl3DShader(context,"simple-shader.vert","simple_shader.frag");
            shader.compile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void prepare(Gl3DShader shader) {
        
    }

}
