package com.yakstudio.cardboard_001.GL;

import android.opengl.Matrix;

public class GlTransformation {

    // translate
    private float tx = 0, ty = 0, tz = 0;
    private float rx = 0, ry = 0, rz = 0;
    private float sx = 1, sy = 1, sz = 1;

    // result matrix
    private float[] matrix = new float[16];
    private float[] normalMatrix = new float[16];

    //-----------------------------------------------------------------------------------------
    public GlTransformation() {
        calcMatrix();
    }

    //-----------------------------------------------------------------------------------------
    public void reset() {
        tx = 0;
        ty = 0;
        tz = 0;
        rx = 0;
        ry = 0;
        rz = 0;
        sx = 1;
        sy = 1;
        sz = 1;
    }

    public void setTranslation(float x, float y, float z) {
        tx = x;
        ty = y;
        tz = z;
        calcMatrix();
    }

    public void move(float dx, float dy, float dz) {
        tx += dx;
        ty += dy;
        tz += dz;
        calcMatrix();
    }


    public void setRotation(float x, float y, float z) {
        rx = x;
        ry = y;
        rz = z;
        calcMatrix();
    }

    public void rotate(float dx, float dy, float dz) {
        rx += dx;
        ry += dy;
        rz += dz;
        calcMatrix();
    }

    public void setScale(float x, float y, float z) {
        sx = x;
        sy = y;
        sz = z;
        calcMatrix();
    }

    public void scale(float dx, float dy, float dz) {
        sx *= dx;
        sy *= dy;
        sz *= dz;
        calcMatrix();
    }

    //-----------------------------------------------------------------------------------------
    // GETTERS/SETTES
    //-----------------------------------------------------------------------------------------

    public float[] getMatrix() {
        return matrix;
    }

    public float[] getNormalMatrix() {
        return normalMatrix;
    }

    public float getTx() {
        return tx;
    }

    public float getTy() {
        return ty;
    }

    public float getTz() {
        return tz;
    }

    public float getRx() {
        return rx;
    }

    public float getRy() {
        return ry;
    }

    public float getRz() {
        return rz;
    }

    public float getSx() {
        return sx;
    }

    public float getSy() {
        return sy;
    }

    public float getSz() {
        return sz;
    }

    //-----------------------------------------------------------------------------------------
    // HELPERS
    //-----------------------------------------------------------------------------------------
    private void calcMatrix() {
        Matrix.setIdentityM(matrix, 0);

        //scale
        Matrix.scaleM(matrix, 0, sx, sy, sz);

        //rotate x..y..z
        Matrix.rotateM(matrix, 0, ry, 0, 1, 0);
        Matrix.rotateM(matrix, 0, rz, 0, 0, 1);
        Matrix.rotateM(matrix, 0, rx, 1, 0, 0);

        //move
        Matrix.translateM(matrix, 0, tx, ty, tz);

        //normals (rotation only)
        Matrix.setIdentityM(normalMatrix, 0);
        Matrix.rotateM(normalMatrix, 0, ry, 0, 1, 0);
        Matrix.rotateM(normalMatrix, 0, rz, 0, 0, 1);
        Matrix.rotateM(normalMatrix, 0, rx, 1, 0, 0);
    }

}
