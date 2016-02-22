package com.yakstudio.cardboard_001.GL.objects;

import android.opengl.GLES20;

import com.yakstudio.cardboard_001.GL.materials.Gl3DShader;
import com.yakstudio.cardboard_001.GL.materials.GlShader;
import com.yakstudio.cardboard_001.GL.toolbox.GlBufferUtils;

import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

public class GlPlane extends GlGeometry {

    private float height;
    private float width;

    private int vtxsBufferID;
    private int idxsBufferID;

    public GlPlane(float width, float height) {
        this.width = width;
        this.height = height;
    }

    public void load() {

        int[] buffers = new int[1];

        //gl buffers:
        GLES20.glGenBuffers(1, buffers, 0);
        vtxsBufferID = buffers[0];

        GLES20.glGenBuffers(1, buffers, 0);
        idxsBufferID = buffers[0];


        float w2 = width / 2;
        float h2 = height / 2;
        // 0  2
        // 1  3
        float[] vtxs = new float[]{
                // Front face
                -w2, +h2, 0f,
                -w2, -h2, 0f,
                +w2, +h2, 0f,
                +w2, -h2, 0f,
        };

        short[] idxs = new short[]{
                0, 1, 2,
                1, 3, 2
        };

        FloatBuffer vtxsBuff = GlBufferUtils.createFloatBuffer(vtxs);
        ShortBuffer idxsBuffer = GlBufferUtils.createShortBuffer(idxs);

        //REM : size is in bytes!

        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vtxsBufferID);
        GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, vtxs.length * 4, vtxsBuff, GLES20.GL_STATIC_DRAW);


        GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, idxsBufferID);
        GLES20.glBufferData(GLES20.GL_ELEMENT_ARRAY_BUFFER, idxs.length * 2, idxsBuffer, GLES20.GL_STATIC_DRAW);

        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);
        GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, 0);
    }

    public void cleanUp() {
        //delete buffers:
        int[] buffers = new int[]{vtxsBufferID, idxsBufferID};
        GLES20.glDeleteBuffers(2, buffers, 0);

        vtxsBufferID = 0;
        idxsBufferID = 0;
    }

    @Override
    public void prepare(Gl3DShader shader) {

    }

    @Override
    public void render(Gl3DShader shader) {
        //bind

        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vtxsBufferID);
        GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, idxsBufferID);

        //vtxs
        int a_vertex = shader.getVertexAttributeLocation();
        if (a_vertex >= 0) {
            GLES20.glEnableVertexAttribArray(a_vertex);
            GLES20.glVertexAttribPointer(a_vertex, 3, GLES20.GL_FLOAT, false, 0, 0);
        }

        //draw
        GLES20.glDrawElements(GLES20.GL_TRIANGLES, 6 /*total # vertices*/, GLES20.GL_UNSIGNED_SHORT, 0);

        //disable
        if (a_vertex >= 0)
            GLES20.glDisableVertexAttribArray(a_vertex);

        //unbind
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);
        GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, 0);
    }
}
