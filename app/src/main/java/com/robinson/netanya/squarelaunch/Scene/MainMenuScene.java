package com.robinson.netanya.squarelaunch.Scene;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.widget.Toast;

import com.robinson.netanya.squarelaunch.AppConstants;
import com.robinson.netanya.squarelaunch.ImageLoader.CustomImage;
import com.robinson.netanya.squarelaunch.R;
import com.robinson.netanya.squarelaunch.Utils.TextUtils;

public class MainMenuScene implements Scene{

    private CustomImage playButton;
    private CustomImage statsButton;
    private CustomImage settingsButton;
    private SceneManager sceneManager;

    private Canvas canvas;
    private Paint titlePaint;

    private long initialTime = System.currentTimeMillis();
    private int hue = 0;

    public MainMenuScene(SceneManager sceneManager) {
        this.sceneManager = sceneManager;

        playButton = new CustomImage(R.drawable.playbutton);
        playButton.setRectWidth(250);
        playButton.setHeightInRatio();
        playButton.setCenterX(AppConstants.SCREEN_WIDTH/2);
        playButton.setCenterY(3*AppConstants.SCREEN_HEIGHT/4);

        settingsButton = new CustomImage(R.drawable.settingsbutton);
        settingsButton.setRectWidth(100);
        settingsButton.setHeightInRatio();
        settingsButton.setCenterX(4*AppConstants.SCREEN_WIDTH/5);
        settingsButton.setCenterY(3*AppConstants.SCREEN_HEIGHT/4);

        statsButton = new CustomImage(R.drawable.statsbutton);
        statsButton.setRectWidth(100);
        statsButton.setHeightInRatio();
        statsButton.setCenterX(1*AppConstants.SCREEN_WIDTH/5);
        statsButton.setCenterY(3*AppConstants.SCREEN_HEIGHT/4);

        titlePaint = new Paint();
        titlePaint.setTypeface(Typeface.DEFAULT_BOLD);
        titlePaint.setTextSize(150);
        titlePaint.setColor(Color.BLACK);
        titlePaint.setTextAlign(Paint.Align.CENTER);
    }


    @Override
    public void draw(Canvas canvas) {
        canvas.drawColor(Color.HSVToColor(new float[]{hue, 1, 1}));
        playButton.draw(canvas);
        settingsButton.draw(canvas);
        statsButton.draw(canvas);
        canvas.drawText("SQUARE LAUNCH", AppConstants.SCREEN_WIDTH/2, 3* AppConstants.SCREEN_HEIGHT/10, titlePaint);
    }

    @Override
    public void update() {
        long currentTime = System.currentTimeMillis();
        if(currentTime - initialTime >= 50){
            hue = (hue + 1) % 360;
            initialTime = System.currentTimeMillis();
        }
    }

    private boolean playButtonTriggered = false;
    private boolean settingsButtonTriggered = false;
    private boolean statsButtonTriggered = false;

    @Override
    public void onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            Point point = new Point((int) event.getX(), (int) event.getY());
            if (playButton.opaqueIntersection(point)) {
                playButton.scaleRectHeight(0.9f, true);
                playButton.scaleRectWidth(0.9f, true);
                playButtonTriggered = true;
            }
            if (settingsButton.rectangleIntersection(point)) {
                settingsButton.scaleRectHeight(0.9f, true);
                settingsButton.scaleRectWidth(0.9f, true);
                settingsButtonTriggered = true;
            }
            if (statsButton.rectangleIntersection(point)) {
                statsButton.scaleRectHeight(0.9f, true);
                statsButton.scaleRectWidth(0.9f, true);
                statsButtonTriggered = true;
            }

            if (TextUtils.textIntersect(point, titlePaint, "SQUARE LAUNCH", AppConstants.SCREEN_WIDTH / 2, 3 * AppConstants.SCREEN_HEIGHT / 10, true)) {
                Toast toast = Toast.makeText(AppConstants.CURRENT_CONTEXT, (String) "Made by Netanya Robinson.", Toast.LENGTH_SHORT);
                toast.show();
            }
        }

        if(event.getAction() == MotionEvent.ACTION_UP){
            if(playButtonTriggered){
                playButton.scaleRectHeight(1.11f, true);
                playButton.scaleRectWidth(1.11f, true);
                sceneManager.setActiveScene(SceneType.GAME_PLAY);
                playButtonTriggered = false;
            }
            if(settingsButtonTriggered){
                settingsButton.scaleRectHeight(1.11f, true);
                settingsButton.scaleRectWidth(1.11f, true);
                settingsButtonTriggered = false;
            }
            if(statsButtonTriggered){
                statsButton.scaleRectHeight(1.11f, true);
                statsButton.scaleRectWidth(1.11f, true);
                statsButtonTriggered = false;
            }
        }
    }
}
