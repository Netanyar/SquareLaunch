package com.robinson.netanya.squarelaunch.GameObjects;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.robinson.netanya.squarelaunch.AppConstants;

public class RectPlayer implements GameObject{

    private Rect rect;
    private Paint paint;
    private int hue = 0;
    private long initialTime = System.currentTimeMillis();
    private long previousTime = System.currentTimeMillis();
    private boolean isJumping = false;
    private float yVelocity = 0.0f;
    private float xVelocity = 0.0f;
    private int jumpNumber = 0;

    public RectPlayer(){
        rect = new Rect(2*AppConstants.SCREEN_WIDTH/15, AppConstants.SCREEN_HEIGHT, 3*AppConstants.SCREEN_WIDTH/15, Floor.FLOOR_HEIGHT);
        rect.top = rect.bottom - rect.width();
        paint = new Paint();
        paint.setColor(Color.HSVToColor(new float[]{0, 1, 1}));
    }

    public void setCenter(){
    }

    public void setJumping(boolean isJumping){
        this.isJumping = isJumping;
    }

    public boolean isJumping(){
        return isJumping;
    }

    public void offsetY(int dy){
        if(rect.top - dy < 0){
            int height = rect.height();
            rect.top = 0;
            rect.bottom = height;
            yVelocity = 0;
        }else{
            rect.offset(0, -dy);
        }

        if(rect.bottom - dy> Floor.FLOOR_HEIGHT){
            yVelocity = 0;
            int height = rect.height();
            rect.top = Floor.FLOOR_HEIGHT - height;
            rect.bottom = Floor.FLOOR_HEIGHT;
            setJumping(false);
            jumpNumber = 0;
        }else{
            rect.offset(0, -dy);
        }
    }

    public void offsetX(int dx){
        if(rect.left + dx < 0){
            xVelocity = 0;
            int width = rect.width();
            rect.left = 0;
            rect.right = width;
        }else{
            rect.offset(dx, 0);
        }

        if(rect.right + dx > AppConstants.SCREEN_WIDTH){
            xVelocity = 0;
            int width = rect.width();
            rect.left = AppConstants.SCREEN_WIDTH - width;
            rect.right = AppConstants.SCREEN_WIDTH;
        }else{
            rect.offset(dx, 0);
        }
    }

    public void setYVelocity(float velocity){
        this.yVelocity = velocity;
    }

    public float getYVelocity(){
        return yVelocity;
    }

    public void setXVelocity(float velocity){
        this.xVelocity = velocity;
    }

    public float getXVelocity(){
        return yVelocity;
    }

    public void setJumpNumber(int jumpNumber){
        this.jumpNumber = jumpNumber;
    }

    public int getJumpNumber(){
        return jumpNumber;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawRect(rect, paint); }

    @Override
    public void update() {
        long currentTime = System.currentTimeMillis();
        long timeElapsed = (currentTime - previousTime);
        if(timeElapsed >= 50){
            hue = (hue + 1) % 360;
            previousTime = System.currentTimeMillis();
        }
        paint.setColor(Color.HSVToColor(new float[]{hue, 1, 1}));

        if(isJumping){
            setYVelocity(getYVelocity() - (AppConstants.GRAVITATIONAL_ACCELERATION * timeElapsed/1000));
        }

        if(yVelocity != 0.0f){
            offsetY((int)(yVelocity * timeElapsed/1000));
        }

        if(xVelocity != 0.0f){
            offsetX((int)(xVelocity * timeElapsed/1000));
        }
    }

    @Override
    public boolean collision(Rect rect){
        return true;
    }

    public Rect getRect(){
        return rect;
    }
}
