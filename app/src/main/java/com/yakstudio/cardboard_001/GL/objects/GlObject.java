package com.yakstudio.cardboard_001.GL.objects;

import com.yakstudio.cardboard_001.GL.materials.Gl3DShader;
import com.yakstudio.cardboard_001.GL.materials.GlMaterial;
import com.yakstudio.cardboard_001.GL.materials.GlShader;
import com.yakstudio.cardboard_001.GL.GlTransformation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yak on 21/02/2016.
 */
public class GlObject {

    //-----------------------------------------------------------------------------------------
    // fields:
    private GlTransformation transformation = new GlTransformation();
    private List<GlObject> children = new ArrayList<>();
    private GlMaterial material;

    private List<GlGeometry> geometries = new ArrayList<>();
    //-----------------------------------------------------------------------------------------
    // CTOR


    //-----------------------------------------------------------------------------------------
    // PUBLIC
    public void prepare(Gl3DShader shader) {

        // prepare self (model matrix):
        shader.loadModelMatrix(getTransformation().getMatrix());

        // prepare geometry
        for (GlGeometry geometry : geometries) {
            geometry.prepare(shader);
        }
    }

    //-----------------------------------------------------------------------------------------


    public GlTransformation getTransformation() {
        return transformation;
    }

    public List<GlObject> getChildren() {
        return children;
    }

    public GlMaterial getMaterial() {
        return material;
    }

    public void setMaterial(GlMaterial material) {
        this.material = material;
    }

    public List<GlGeometry> getGeometries() {
        return geometries;
    }

}
