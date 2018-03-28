package com.example.cristian.opengles;

import android.content.Context;
import android.opengl.GLES10;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Cristian on 25/03/2018.
 */

@RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
public class Stage extends GLSurfaceView {

    private float w,h;
    private int screenW,screenH;

    private FloatBuffer vertexBuffer;

    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    public Stage(Context context, AttributeSet attrs) {
        super(context, attrs);
        setEGLConfigChooser(8,8,8,8,0,0);
        setRenderer(new MyRenderer());

        float vertices[] = {
                -0.5f,-0.5f,0.0f,
                0.5f,-0.5f,0.0f,
                -0.5f,0.5f,0.0f,
                0.5f,0.5f,0.0f

        };

        ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
        vbb.order(ByteOrder.nativeOrder());
        vertexBuffer = vbb.asFloatBuffer();
        vertexBuffer.put(vertices);
        vertexBuffer.position(0);
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

            gl10.glPushMatrix();
            gl10.glClear(GLES10.GL_COLOR_BUFFER_BIT);
            gl10.glTranslatef(w/2,h/2,0);
            gl10.glScalef(120,100,0);
            gl10.glVertexPointer(3,GL10.GL_FLOAT,0,vertexBuffer);
            gl10.glDrawArrays(GL10.GL_TRIANGLE_STRIP,0,4);
            gl10.glPopMatrix();

        }
    }
}
