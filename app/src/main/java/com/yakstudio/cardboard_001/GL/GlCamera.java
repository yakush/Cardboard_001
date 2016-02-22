package com.yakstudio.cardboard_001.GL;

import com.yakstudio.cardboard_001.GL.materials.Gl3DShader;
import com.yakstudio.cardboard_001.GL.materials.GlShader;

/**
 * Created by Yak on 21/02/2016.
 */
public class GlCamera {

    //-----------------------------------------------------------------------------------------
    // fields:
    GlTransformation transformation;
    // frastum
    // fov

    public float[] projectionMatrix = new float[16];
    public float[] viewMatrix = new float[16];

    public void prepare(Gl3DShader shader) {
        //view matrix
        //perspective matrix
        shader.loadProjectionMatrix(projectionMatrix);
        shader.loadViewMatrix(viewMatrix);
    }
}
