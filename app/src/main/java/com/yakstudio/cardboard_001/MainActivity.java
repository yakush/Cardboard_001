package com.yakstudio.cardboard_001;

import android.opengl.GLES20;
import android.opengl.Matrix;
import android.os.Bundle;
import android.util.Log;

import com.google.vrtoolkit.cardboard.CardboardActivity;
import com.google.vrtoolkit.cardboard.CardboardView;
import com.google.vrtoolkit.cardboard.Eye;
import com.google.vrtoolkit.cardboard.HeadTransform;
import com.google.vrtoolkit.cardboard.Viewport;
import com.yakstudio.cardboard_001.GL.GlCamera;
import com.yakstudio.cardboard_001.GL.GlRenderer;
import com.yakstudio.cardboard_001.GL.GlScene;
import com.yakstudio.cardboard_001.GL.materials.GlMaterial;
import com.yakstudio.cardboard_001.GL.materials.SimpleMaterial;
import com.yakstudio.cardboard_001.GL.objects.GlObject;
import com.yakstudio.cardboard_001.GL.objects.GlPlane;

import java.util.Arrays;

import javax.microedition.khronos.egl.EGLConfig;

public class MainActivity extends CardboardActivity implements CardboardView.StereoRenderer {

    private static final String TAG = "MainActivity";
    private GlRenderer renderer;
    private GlScene scene;
    private GlCamera cam;
    private GlPlane planeGeo;
    private GlObject planeObject;
    private GlMaterial material;

    private CardboardView cardboardView;


    //-----------------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setup cardboard
        cardboardView = (CardboardView) findViewById(R.id.cardboard_view);
        cardboardView.setRestoreGLStateEnabled(false);
        cardboardView.setRenderer(this);
        setCardboardView(cardboardView);

    }

    //-----------------------------------------------------------------------------------------
    // -- EVENTS
    //-----------------------------------------------------------------------------------------
    int flying=0;
    float[] cameraPosition = new float[16];

    @Override
    public void onCardboardTrigger() {
        flying=(flying+1)%4; //0,1,2,3 = stop,FWD,stop,back
    }

    //-----------------------------------------------------------------------------------------
    // -- SURFACE
    //-----------------------------------------------------------------------------------------

    @Override
    public void onSurfaceCreated(EGLConfig eglConfig) {

        //camera start position
        Matrix.setLookAtM(
                cameraPosition , 0,
                0.0f, 0.0f, 10f,      // eye
                0.0f, 0.0f, 0.0f,       // center
                0.0f, 1.0f, 0.0f        // up
        );

        //create stuff:
        renderer = new GlRenderer();

        scene = new GlScene();
        cam  = new GlCamera();
        scene.setCamera (cam);

        planeGeo = new GlPlane(1,1);
        planeGeo.load();
        planeObject = new GlObject();
        planeObject.getGeometries().add(planeGeo);

        material = new SimpleMaterial(this);

        planeObject.setMaterial(material);

        scene.getObjects().add(planeObject);
    }

    //-----------------------------------------------------------------------------------------
    @Override
    public void onSurfaceChanged(int i, int i1) {

    }

    //-----------------------------------------------------------------------------------------
    @Override
    public void onRendererShutdown() {

    }

    //-----------------------------------------------------------------------------------------
    // -- RENDER
    //-----------------------------------------------------------------------------------------

    long lastUpdate= System.currentTimeMillis();
    float[] headFwd =new float[3];

    private void updateAnimation(){
        long now = System.currentTimeMillis();
        if (now-lastUpdate>1000/30) {
            planeObject.getTransformation().rotate(0, 10, 0);
            lastUpdate=now;

            float dist= 0.1f;
            switch (flying) {
                case 0:
                case 2:
                    //STOP
                    break;

                case 1:
                    //FFD
                    Matrix.translateM(cameraPosition,0,dist*headFwd[0],dist*headFwd[1],dist*headFwd[2]);
                    break;

                case 3:
                    //BACK
                    Matrix.translateM(cameraPosition,0,-dist*headFwd[0],-dist*headFwd[1],-dist*headFwd[2]);
                    break;

            }
        }
    }

    @Override
    public void onNewFrame(HeadTransform headTransform) {

        GLES20.glClearColor(0.3f, 0.1f, 0.1f, 0.5f);
        headTransform.getForwardVector(headFwd,0);

        updateAnimation();
    }

    //-----------------------------------------------------------------------------------------
    @Override
    public void onFinishFrame(Viewport viewport) {

    }

    //-----------------------------------------------------------------------------------------
    @Override
    public void onDrawEye(Eye eye) {
        //GLES20.glEnable(GLES20.GL_DEPTH_TEST);
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
       // GLES20.glsw

        //camera view matrix:
        Matrix.setIdentityM(cam.viewMatrix, 0);
        Matrix.multiplyMM(cam.viewMatrix, 0, cam.viewMatrix, 0, cameraPosition, 0);
        Matrix.multiplyMM(cam.viewMatrix, 0, eye.getEyeView(), 0, cam.viewMatrix, 0);

        Matrix.setIdentityM(cam.projectionMatrix,0);
        Matrix.multiplyMM(cam.projectionMatrix,0,eye.getPerspective(0.1f,100),0,cam.projectionMatrix,0);

        renderer.renderScene(scene);
    }

}
