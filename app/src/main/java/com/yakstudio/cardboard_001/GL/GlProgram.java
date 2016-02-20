package com.yakstudio.cardboard_001.GL;

import android.support.annotation.IdRes;


public abstract class GlProgram {

    private int vertexShader =0;
    private int fragmentShader=0;


    public GlProgram(String vertexShader, String fragmentShader){

    }

    public GlProgram(@IdRes int vertexShader, @IdRes int fragmentShader){

    }

    private void init(){

    }

    //-----------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------

    public void compile(){
    }

    public void delete(){
    }

    public void use(){
    }

    public int getUniform(String name){
        //TODO:getUniform
        return 0;
    }

    public int getAttrib(String name){
        //TODO:getAttrib
        return 0;
    }
}
