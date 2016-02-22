package com.yakstudio.cardboard_001.GL.toolbox;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;

/**
 * Created by Yak on 21/02/2016.
 */
public class GlBufferUtils {

    public static FloatBuffer createFloatBuffer(float[] floatArr){

        int floatSize = 4;

        FloatBuffer floatBuffer = ByteBuffer
                .allocateDirect(floatArr.length * floatSize)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer()
                .put(floatArr);

        floatBuffer.position(0);

        return floatBuffer;
    }

    public static IntBuffer createIntBuffer(int[] intArr) {
        int intSize = 4;

        IntBuffer intBuffer = ByteBuffer
                .allocateDirect(intArr.length * intSize)
                .order(ByteOrder.nativeOrder())
                .asIntBuffer()
                .put(intArr);

        intBuffer.position(0);

        return intBuffer;
    }

    public static ShortBuffer createShortBuffer(short[] arr) {
        int elementSize= 2; //short

        ShortBuffer buffer = ByteBuffer
                .allocateDirect(arr.length * elementSize)
                .order(ByteOrder.nativeOrder())
                .asShortBuffer()
                .put(arr);

        buffer.position(0);


        return buffer;
    }

}
