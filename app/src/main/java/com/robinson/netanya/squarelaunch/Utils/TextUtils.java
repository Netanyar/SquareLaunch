package com.robinson.netanya.squarelaunch.Utils;

import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

public class TextUtils {
    public static Rect rect = new Rect();

    public static boolean textIntersect(Point point, Paint paint, String text, int xLoc, int yLoc, boolean centerAlign) {
        int xTap = point.x;
        int yTap = point.y;
        paint.getTextBounds(text, 0, text.length(), rect);
        if (centerAlign) {
            rect.offsetTo(xLoc - (rect.width()/2), yLoc - (rect.height()));
            return rect.intersects(xTap, yTap, xTap, yTap);
        } else {
            return rect.intersects(xTap - xLoc, yTap - yLoc, xTap - xLoc, yTap - yLoc);
        }
    }
}
