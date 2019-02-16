package com.robinson.netanya.squarelaunch;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class MainThread extends Thread{

    private boolean running = false;
    private SurfaceHolder surfaceHolder;
    private GameView gameView;
    private float averageFPS;
    private static Canvas canvas;

    public MainThread(SurfaceHolder surfaceHolder, GameView gameView){
        super();
        this.surfaceHolder = surfaceHolder;
        this.gameView = gameView;
    }

    @Override
    public void run(){
        long startTime;
        long endTime;
        long waitTime;
        long frameCount = 0;
        long totalTime = 0;
        long targetTime = 1000/AppConstants.MAX_FPS;

        while(running){
            startTime = System.nanoTime();
            canvas = null;

            try{
                canvas = this.surfaceHolder.lockCanvas();
                synchronized(surfaceHolder){
                    this.gameView.update();
                    this.gameView.draw(canvas);
                }
            }catch(Exception e){
                e.printStackTrace();
            } finally{
                if(canvas != null){
                    try{
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
            endTime = System.nanoTime();
            long timePerFrameMillis = (endTime - startTime)/1000000;
            waitTime = targetTime - timePerFrameMillis;
            try{
                if (waitTime > 0){
                    this.sleep(waitTime);
                }
            }catch(Exception e){
                e.printStackTrace();
            }

            totalTime += (System.nanoTime() - startTime);
            frameCount++;

            if(frameCount == AppConstants.MAX_FPS) {
                averageFPS = (1000000000 * frameCount) / totalTime;
                frameCount = 0;
                totalTime = 0;
            }
        }
    }

    public void setRunning(boolean running){
        this.running = running;
    }
    public boolean getRunning(){
        return running;
    }
    public double getAverageFPS(){
        return averageFPS;
    }
    public static Canvas getCanvas(){
        return canvas;
    }
}
