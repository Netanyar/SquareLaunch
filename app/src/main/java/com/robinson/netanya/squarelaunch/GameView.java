package com.robinson.netanya.squarelaunch;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.robinson.netanya.squarelaunch.Scene.SceneManager;

public class GameView extends SurfaceView implements SurfaceHolder.Callback{

    private MainThread thread;
    private SceneManager sceneManager;


    public GameView(Context context) {
        super(context);
        getHolder().addCallback(this);
        setFocusable(true);
        sceneManager = new SceneManager();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread = new MainThread(getHolder(), this);
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while(retry){
            try{
                thread.setRunning(false);
                thread.join();
                retry = false;
            } catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        sceneManager.onTouchEvent(event);
        return true;
    }

    public void draw(Canvas canvas){
        super.draw(canvas);
        sceneManager.draw(canvas);
    }

    public void update(){
        sceneManager.update();
    }
}