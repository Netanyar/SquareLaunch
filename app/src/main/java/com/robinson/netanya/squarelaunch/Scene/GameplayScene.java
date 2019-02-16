package com.robinson.netanya.squarelaunch.Scene;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;

import com.robinson.netanya.squarelaunch.AppConstants;
import com.robinson.netanya.squarelaunch.GameObjects.Floor;
import com.robinson.netanya.squarelaunch.GameObjects.GameObject;
import com.robinson.netanya.squarelaunch.GameObjects.ObstacleManager;
import com.robinson.netanya.squarelaunch.GameObjects.RectPlayer;

import java.util.ArrayList;

public class GameplayScene implements Scene{

    private SceneManager sceneManager;
    private ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
    private ObstacleManager obstacleManager;
    private Paint scorePaint = new Paint();
    private Paint highscorePaint = new Paint();

    public GameplayScene(SceneManager sceneManager){
        this.sceneManager = sceneManager;
        populateGameObjects();
        obstacleManager = new ObstacleManager((RectPlayer) gameObjects.get(1), sceneManager);
        scorePaint.setTextSize(130);
        scorePaint.setTypeface(Typeface.DEFAULT_BOLD);
        scorePaint.setColor(Color.RED);
        scorePaint.setTextAlign(Paint.Align.CENTER);
        highscorePaint.setTextSize(40);
        highscorePaint.setTypeface(Typeface.DEFAULT_BOLD);
        highscorePaint.setColor(Color.RED);
        highscorePaint.setTextAlign(Paint.Align.CENTER);
        obstacleManager.reset();
    }

    public void populateGameObjects(){
        gameObjects.add(new Floor());
        gameObjects.add(new RectPlayer());
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        for(GameObject gameObject : gameObjects){
            gameObject.draw(canvas);
        }
        obstacleManager.draw(canvas);
        canvas.drawText("" + ObstacleManager.getScore(), 2*AppConstants.SCREEN_WIDTH/30, 2*AppConstants.SCREEN_HEIGHT/20, scorePaint);
        canvas.drawText("High: " + ObstacleManager.getHighscore(), 1*AppConstants.SCREEN_WIDTH/20, 3*AppConstants.SCREEN_HEIGHT/20, highscorePaint);
    }

    @Override
    public void update() {
        for (GameObject gameObject : gameObjects) {
            gameObject.update();
        }
        obstacleManager.update();
    }

    @Override
    public void onTouchEvent(MotionEvent event) {
        RectPlayer rectP = (RectPlayer) gameObjects.get(1);
        long time = 0;
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if(rectP.getJumpNumber() < 2){
                    rectP.setJumping(true);
                    time = System.currentTimeMillis();
                    rectP.setYVelocity(650.0f);
                    rectP.setJumpNumber(rectP.getJumpNumber() + 1);
                }
                break;

            case MotionEvent.ACTION_UP:
                long timeElapsed = System.currentTimeMillis() - time;
                break;
        }

    }
}
