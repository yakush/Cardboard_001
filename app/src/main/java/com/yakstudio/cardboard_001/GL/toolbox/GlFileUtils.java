package com.yakstudio.cardboard_001.GL.toolbox;

import android.content.Context;
import android.support.annotation.RawRes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;

public class GlFileUtils {

    public static String readFile(File file) throws FileNotFoundException, IOException {
        InputStream is = new FileInputStream(file);
        return readStream(is);
    }

    public static String readRaw(Context context, @RawRes int resid) throws IOException {
        InputStream is = context.getResources().openRawResource(resid);
        return readStream(is);
    }

    public static String readAsset(Context context, String name) throws IOException {
        InputStream is = context.getAssets().open(name);
        return readStream(is);
    }

    public static String readStream(InputStream inputStream) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();

        try {
            String read;
            while ((read = reader.readLine()) != null) {
                stringBuilder.append(read+"\n");
            }
            return stringBuilder.toString();
        } finally {
            if (inputStream!=null){
                inputStream.close();
            }
        }
    }


}
