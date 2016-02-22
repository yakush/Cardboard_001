package com.yakstudio.cardboard_001.GL;

import com.yakstudio.cardboard_001.GL.lights.GlLight;
import com.yakstudio.cardboard_001.GL.objects.GlObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yak on 21/02/2016.
 */
public class GlScene {


    private GlCamera camera;
    private List<GlObject> objects = new ArrayList<>();
    private List<GlLight> lights=new ArrayList<>();

    public GlCamera getCamera() {
        return camera;
    }

    public void setCamera(GlCamera camera) {
        this.camera = camera;
    }

    public List<GlObject> getObjects() {
        return objects;
    }

    public List<GlLight> getLights() {
        return lights;
    }
}
