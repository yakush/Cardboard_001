package com.yakstudio.cardboard_001.GL;

import com.yakstudio.cardboard_001.GL.materials.Gl3DShader;
import com.yakstudio.cardboard_001.GL.materials.GlMaterial;
import com.yakstudio.cardboard_001.GL.materials.GlShader;
import com.yakstudio.cardboard_001.GL.objects.GlGeometry;
import com.yakstudio.cardboard_001.GL.objects.GlObject;

/**
 * Created by Yak on 21/02/2016.
 */
public class GlRenderer {

    GlMaterial defaultMaterial;

    public void renderScene(GlScene scene){
        //set camera, light, default material...
        //render all objects
        for (GlObject object : scene.getObjects()){

            //start program:
            GlMaterial material = object.getMaterial();
            if (material==null){
                material=defaultMaterial;
            }
            Gl3DShader shader = material.getShader();
            shader.start();

            //prepare:
            scene.getCamera().prepare(shader);

            // lights

            material.prepare(shader);
            object.prepare(shader);

            //render:
            for (GlGeometry geometry : object.getGeometries()) {
                geometry.render(shader);
            }

            //stop program:
            shader.stop();
        }
    }

}
