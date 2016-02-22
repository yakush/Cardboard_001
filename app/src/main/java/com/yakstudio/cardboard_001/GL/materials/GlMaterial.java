package com.yakstudio.cardboard_001.GL.materials;

/**
 * Created by Yak on 21/02/2016.
 */
public abstract class GlMaterial {

    Gl3DShader shader;

    public abstract void prepare(Gl3DShader shader);

    public Gl3DShader getShader() {
        return shader;
    }
}
