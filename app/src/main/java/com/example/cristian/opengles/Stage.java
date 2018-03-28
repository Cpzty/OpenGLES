package com.example.cristian.opengles;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Cristian on 25/03/2018.
 */

@RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
public class Stage extends GLSurfaceView {

    private float w,h;
    private int screenW,screenH;

    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    public Stage(Context context, AttributeSet attrs) {
        super(context, attrs);
        setEGLConfigChooser(8,8,8,8,0,0);
        setRenderer(new MyRenderer());
    }

    private final class MyRenderer implements GLSurfaceView.Renderer{


        @Override
        public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {

            gl10.glEnable(GL10.GL_ALPHA_TEST);
            gl10.glEnable(GL10.GL_BLEND);
            gl10.glBlendFunc(GL10.GL_ONE,GL10.GL_ONE_MINUS_SRC_ALPHA);


            //disables 3D
            gl10.glDisable(GL10.GL_DEPTH_TEST);

            gl10.glEnableClientState(GL10.GL_VERTEX_ARRAY);


        }

        @Override
        public void onSurfaceChanged(GL10 gl10, int width, int height) {

            gl10.glClearColor(0,0,0,1.0f);

            if(width>height){
                h=600;
                w=width * h/height;


            }else{
                w=600;
                h = height *w/width;
            }

            screenH = height;
            screenW = width;

            gl10.glViewport(0,0,screenW,screenH);
            gl10.glMatrixMode(GL10.GL_PROJECTION);
            gl10.glLoadIdentity();
            gl10.glOrthof(0,w,h,0,-1,1);
            gl10.glMatrixMode(GL10.GL_MODELVIEW);
            gl10.glLoadIdentity();

        }

        @Override
        public void onDrawFrame(GL10 gl10) {

        }
    }
}
