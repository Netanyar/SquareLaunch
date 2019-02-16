package com.robinson.netanya.squarelaunch.Scene;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Typeface;
import android.view.MotionEvent;

import com.robinson.netanya.squarelaunch.AppConstants;
import com.robinson.netanya.squarelaunch.ImageLoader.CustomImage;
import com.robinson.netanya.squarelaunch.GameObjects.ObstacleManager;
import com.robinson.netanya.squarelaunch.R;

public class GameoverScene implements Scene{

    private SceneManager sceneManager;
    private Paint scorePaint = new Paint();
    private CustomImage resetButton;

    public GameoverScene(SceneManager sceneManager){
        this.sceneManager = sceneManager;
        scorePaint.setTextSize(130);
        scorePaint.setTypeface(Typeface.DEFAULT_BOLD);
        scorePaint.setColor(Color.RED);
        scorePaint.setTextAlign(Paint.Align.CENTER);
        resetButton = new CustomImage(R.drawable.reset);
        resetButton.setRectWidth(300);
        resetButton.setHeightInRatio();
        resetButton.setCenterX(AppConstants.SCREEN_WIDTH/2);
        resetButton.setCenterY(3*AppConstants.SCREEN_HEIGHT/4);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        canvas.drawText("Score: " + ObstacleManager.getScore(), AppConstants.SCREEN_WIDTH/2, 1*AppConstants.SCREEN_HEIGHT/4,scorePaint);
        canvas.drawText("Highscore: " + ObstacleManager.getHighscore(), AppConstants.SCREEN_WIDTH/2, 2*AppConstants.SCREEN_HEIGHT/4,scorePaint);
        resetButton.draw(canvas);

    }

    @Override
    public void update() {

    }

    private boolean resetTriggered = false;

    @Override
    public void onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            if(resetButton.rectangleIntersection(new Point((int) event.getX(), (int) event.getY()))){
                resetTriggered = true;
                resetButton.scaleRectWidth(0.9f, true);
                resetButton.scaleRectHeight(0.9f, true);
            }
        }
        if(event.getAction() == MotionEvent.ACTION_UP){
            if(resetTriggered){
                ObstacleManager.resetScore();
                sceneManager.setActiveScene(SceneType.GAME_PLAY);
                resetButton.scaleRectWidth(1.11f, true);
                resetButton.scaleRectHeight(1.11f, true);
                resetTriggered = false;
            }
        }
    }
}
