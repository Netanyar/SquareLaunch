package com.robinson.netanya.squarelaunch.GameObjects;

import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;

import com.robinson.netanya.squarelaunch.AppConstants;
import com.robinson.netanya.squarelaunch.ImageLoader.CustomImage;
import com.robinson.netanya.squarelaunch.R;

public class SpikeObstacle implements GameObject{

    CustomImage spikes;
    private int speed;
    private int size;
    private ObstacleManager obstacleManager;
    private long initTime = System.currentTimeMillis();

    public SpikeObstacle(int speed, int size, ObstacleManager obstacleManager){
        spikes = new CustomImage(R.drawable.spikes);
        spikes.setRectWidth(size);
        spikes.setHeightInRatio();
        spikes.setCenterY(Floor.FLOOR_HEIGHT -(spikes.getRect().height()/2));
        spikes.setCenterX(AppConstants.SCREEN_WIDTH + spikes.getRect().width());
        this.speed = speed;
        this.size = size;
        this.obstacleManager = obstacleManager;
    }

    @Override
    public void draw(Canvas canvas) {
        spikes.draw(canvas);
    }

    @Override
    public void update() {
        incrementX();
        if(spikes.getRect().right < 0){
            obstacleManager.objectOffScreen(this);
        }
    }

    public void incrementX(){
        long currentTime = System.currentTimeMillis();
        long timeElapsed = currentTime - initTime;
        spikes.setCenterX((int) (spikes.getCenterX()-(speed*timeElapsed)));
        initTime = System.currentTimeMillis();
    }

    public boolean collision(Rect rect) {
        boolean intersection = false;
        if(rect.intersects(spikes.getRect().left, spikes.getRect().top, spikes.getRect().right, spikes.getRect().bottom)){
            for (int x = rect.right; x >= rect.left; x--) {
                if(spikes.opaqueIntersection(new Point(x, rect.bottom))){
                    intersection = true;
                    break;
                }
            }
            for (int y = rect.bottom; y >= rect.top; y--) {
                if(spikes.opaqueIntersection(new Point(rect.right, y))){
                    intersection = true;
                    break;
                }
            }
        }
        return intersection;
    }
}
