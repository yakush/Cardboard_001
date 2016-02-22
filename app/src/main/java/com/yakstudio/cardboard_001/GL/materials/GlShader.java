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
public class GlShader {

    private static final String TAG = GlShader.class.getSimpleName();

    private int programID = 0;
    private int vtxShaderID = 0;
    private int fragShaderID = 0;

    private String vtxShaderSrc;
    private String fragShaderSrc;

    private Map<String, Integer> attribLocations = new HashMap<>();
    private Map<String, Integer> uniformLocations = new HashMap<>();

    public GlShader(String vtxShaderSrc, String fragShaderSrc) {
        this.vtxShaderSrc = vtxShaderSrc;
        this.fragShaderSrc = fragShaderSrc;
    }

    public GlShader(Context context, @RawRes int vtxShader, @RawRes int fragShader) throws IOException {
        this(GlFileUtils.readRaw(context, vtxShader), GlFileUtils.readRaw(context, fragShader));
    }

    public GlShader(Context context, String vtxShaderAsset, String fragShaderAsset) throws IOException {
        this(GlFileUtils.readAsset(context, vtxShaderAsset), GlFileUtils.readAsset(context, fragShaderAsset));
    }

    public GlShader(File vtxShaderFile, File fragShaderFile) throws IOException {
        this(GlFileUtils.readFile(vtxShaderFile), GlFileUtils.readFile(fragShaderFile));
    }

    //-----------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------

    public void start() {
        //assertPrepared();
        GLES20.glUseProgram(programID);
    }

    public void stop() {
        GLES20.glUseProgram(0);
    }

    public void compile() {
        if (programID == 0) {

            Log.d(TAG, "preparing program");

            //cleanup
            attribLocations.clear();
            uniformLocations.clear();

            vtxShaderID = 0;
            fragShaderID = 0;
            programID = 0;

            //shaders:
            vtxShaderID = compileShader(GLES20.GL_VERTEX_SHADER, vtxShaderSrc);
            fragShaderID = compileShader(GLES20.GL_FRAGMENT_SHADER, fragShaderSrc);

            //program:
            programID = GLES20.glCreateProgram();
            if (programID != 0) {
                GLES20.glAttachShader(programID, vtxShaderID);
                GLES20.glAttachShader(programID, fragShaderID);
                GLES20.glLinkProgram(programID);
                int[] linkStatus = new int[1];
                GLES20.glGetProgramiv(programID, GLES20.GL_LINK_STATUS, linkStatus, 0);
                if (linkStatus[0] != GLES20.GL_TRUE) {
                    String info = GLES20.glGetProgramInfoLog(programID);
                    GLES20.glDeleteProgram(programID);
                    programID = 0;
                    throw new RuntimeException("Could not link program: " + info);
                }
            }
        } else {
            //already prepared...
            Log.d(TAG, "already prepared...");

        }
    }

    public void delete() {
        if (programID != 0) {
            GLES20.glDetachShader(programID, vtxShaderID);
            GLES20.glDetachShader(programID, fragShaderID);

            GLES20.glDeleteShader(vtxShaderID);
            GLES20.glDeleteShader(fragShaderID);

            GLES20.glDeleteProgram(programID);
        }
        programID = 0;
        vtxShaderID = 0;
        fragShaderID = 0;
    }

    //-----------------------------------------------------------------------------------------
    // shader attributes

    public int getAttribLocation(String name) {
        //assertPrepared();

        Integer location = attribLocations.get(name);
        if (location == null) {
            location = GLES20.glGetAttribLocation(programID, name);
            attribLocations.put(name, location);
        }

        return location;
    }

    //-----------------------------------------------------------------------------------------
    // shader uniforms
    public int getUniformLocation(String name) {
        //assertPrepared();

        Integer handle = uniformLocations.get(name);
        if (handle == null) {
            handle = GLES20.glGetUniformLocation(programID, name);
            uniformLocations.put(name, handle);
        }

        return handle;
    }

    //-----------------------------------------------------------------------------------------
    // HELPERS
    //-----------------------------------------------------------------------------------------

    private static int compileShader(int shaderType, String source) {

        int shader = GLES20.glCreateShader(shaderType);
        if (shader != 0) {
            GLES20.glShaderSource(shader, source);
            GLES20.glCompileShader(shader);
            int[] compiled = new int[1];
            GLES20.glGetShaderiv(shader, GLES20.GL_COMPILE_STATUS, compiled, 0);
            if (compiled[0] == 0) {
                String info = GLES20.glGetShaderInfoLog(shader);
                GLES20.glDeleteShader(shader);
                //throw new RuntimeException("Could not compile shader : " + info);
                throw new RuntimeException("error compiling shader\n" + info);
            }
        }
        return shader;
    }
}
