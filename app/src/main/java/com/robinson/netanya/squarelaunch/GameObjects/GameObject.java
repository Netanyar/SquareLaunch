package com.robinson.netanya.squarelaunch.GameObjects;

import android.graphics.Canvas;
import android.graphics.Rect;

public interface GameObject {

    public void draw(Canvas canvas);
    public void update();
    public boolean collision(Rect rect);

}
