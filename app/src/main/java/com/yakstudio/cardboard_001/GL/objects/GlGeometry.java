package com.yakstudio.cardboard_001.GL.objects;

import com.yakstudio.cardboard_001.GL.materials.Gl3DShader;
import com.yakstudio.cardboard_001.GL.materials.GlShader;

/**
 * Created by Yak on 21/02/2016.
 */
public abstract class GlGeometry {

    public abstract void prepare(Gl3DShader shader);

    public abstract void render(Gl3DShader shader);

}
