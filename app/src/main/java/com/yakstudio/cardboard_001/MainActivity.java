package com.yakstudio.cardboard_001;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.vrtoolkit.cardboard.CardboardActivity;
import com.google.vrtoolkit.cardboard.CardboardView;
import com.google.vrtoolkit.cardboard.Eye;
import com.google.vrtoolkit.cardboard.HeadTransform;
import com.google.vrtoolkit.cardboard.Viewport;

import javax.microedition.khronos.egl.EGLConfig;

public class MainActivity extends CardboardActivity implements CardboardView.StereoRenderer {

    private static final String TAG = "MainActivity";


    //-----------------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setup cardboard
        CardboardView cardboardView = (CardboardView) findViewById(R.id.cardboard_view);
        cardboardView.setRestoreGLStateEnabled(false);
        cardboardView.setRenderer(this);
        setCardboardView(cardboardView);

    }

    //-----------------------------------------------------------------------------------------
    // -- EVENTS
    //-----------------------------------------------------------------------------------------

    @Override
    public void onCardboardTrigger() {

    }

    //-----------------------------------------------------------------------------------------
    // -- SURFACE
    //-----------------------------------------------------------------------------------------

    @Override
    public void onSurfaceCreated(EGLConfig eglConfig) {

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

    @Override
    public void onNewFrame(HeadTransform headTransform) {

    }

    //-----------------------------------------------------------------------------------------
    @Override
    public void onFinishFrame(Viewport viewport) {

    }

    //-----------------------------------------------------------------------------------------
    @Override
    public void onDrawEye(Eye eye) {

    }

}
