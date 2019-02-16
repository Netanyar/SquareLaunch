package com.robinson.netanya.squarelaunch.GameObjects;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.robinson.netanya.squarelaunch.AppConstants;

public class Floor implements GameObject {

    private Rect rect;
    private Paint paint;
    public static final int FLOOR_HEIGHT = (4*AppConstants.SCREEN_HEIGHT)/5;


    public Floor(){
        paint = new Paint();
        paint.setColor(Color.BLACK);
        rect = new Rect(0, FLOOR_HEIGHT, AppConstants.SCREEN_WIDTH, FLOOR_HEIGHT + 10);
    }


    @Override
    public void draw(Canvas canvas) {
        canvas.drawRect(rect, paint);
    }

    @Override
    public void update() {

    }

    public boolean collision(Rect rect){
        return (this.rect.intersect(rect));
    }
}
