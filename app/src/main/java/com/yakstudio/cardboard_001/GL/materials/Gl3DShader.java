package com.yakstudio.cardboard_001.GL.materials;

import android.content.Context;
import android.opengl.GLES20;
import android.support.annotation.RawRes;
import android.util.Log;

import com.yakstudio.cardboard_001.GL.toolbox.GlFileUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yak on 21/02/2016.
 */
public class Gl3DShader extends GlShader {

    private static final String TAG = Gl3DShader.class.getSimpleName();
    //-----------------------------------------------------------------------------------------
    // CONSTS
    //-----------------------------------------------------------------------------------------
    public static final String ATTR_NAME_VERTEX = "a_vertex";
    public static final String ATTR_NAME_NORMAL = "a_normals";
    public static final String ATTR_NAME_UV = "a_uv";

    public static final String UNIFORM_NAME_MODEL_MATRIX = "u_model";
    public static final String UNIFORM_NAME_VIEW_MATRIX = "u_view";
    public static final String UNIFORM_NAME_PROJECTION_MATRIX = "u_projection";

    //-----------------------------------------------------------------------------------------

    public Gl3DShader(String vtxShaderSrc, String fragShaderSrc) {
        super(vtxShaderSrc, fragShaderSrc);
    }

    public Gl3DShader(Context context, @RawRes int vtxShader, @RawRes int fragShader) throws IOException {
        super(context, vtxShader, fragShader);
    }

    public Gl3DShader(Context context, String vtxShaderAsset, String fragShaderAsset) throws IOException {
        super(context, vtxShaderAsset, fragShaderAsset);
    }

    public Gl3DShader(File vtxShaderFile, File fragShaderFile) throws IOException {
        super(vtxShaderFile, fragShaderFile);
    }

    //-----------------------------------------------------------------------------------------
    // shader attributes
    public int getVertexAttributeLocation() {
        return getAttribLocation(ATTR_NAME_VERTEX);
    }

    public int getNormalsAttributeLocation() {
        return getAttribLocation(ATTR_NAME_NORMAL);
    }

    public int getUvsAttributeLocation() {
        return getAttribLocation(ATTR_NAME_UV);
    }

    //-----------------------------------------------------------------------------------------
    // shader uniforms
    public int getModelUniformLocation() {
        return getUniformLocation(UNIFORM_NAME_MODEL_MATRIX);
    }

    public int getViewUniformLocation() {
        return getUniformLocation(UNIFORM_NAME_VIEW_MATRIX);
    }

    public int getProjectionUniformLocation() {
        return getUniformLocation(UNIFORM_NAME_PROJECTION_MATRIX);
    }

    //-----------------------------------------------------------------------------------------
    // load uniforms:
    public void loadModelMatrix(float[] matrix) {
        int location = getModelUniformLocation();
        if (location >= 0)
            GLES20.glUniformMatrix4fv(location, 1, false, matrix, 0);
    }

    public void loadViewMatrix(float[] matrix) {
        int location = getViewUniformLocation();
        if (location >= 0)
            GLES20.glUniformMatrix4fv(location, 1, false, matrix, 0);
    }

    public void loadProjectionMatrix(float[] matrix) {
        int location = getProjectionUniformLocation();
        if (location >= 0)
            GLES20.glUniformMatrix4fv(location, 1, false, matrix, 0);
    }
}
