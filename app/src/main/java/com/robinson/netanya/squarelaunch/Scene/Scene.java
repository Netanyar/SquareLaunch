package com.robinson.netanya.squarelaunch.Scene;

import android.graphics.Canvas;
import android.view.MotionEvent;

public interface Scene{

    public void draw(Canvas canvas);
    public void update();
    public void onTouchEvent(MotionEvent event);

}
