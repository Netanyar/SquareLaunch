package com.robinson.netanya.squarelaunch.ImageLoader;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;

public class CustomImage {

    private Bitmap image;
    private Rect rect;
    private int initWidth;
    private int initHeight;

    public void scaleRectWidth(float scaleFactor, boolean centeredScale) {
        float newWidth = rect.width() * scaleFactor;
        Rect newRect = new Rect(0, 0, (int) newWidth, rect.bottom);
        if(centeredScale){
            newRect.set((int)(rect.left - ((newWidth-rect.width())/2)), rect.top, (int)(rect.right + ((newWidth-rect.width())/2)), rect.bottom);
        }else{
            newRect.offset(rect.left, rect.top);
        }
        rect = newRect;
    }

    public void scaleRectHeight(float scaleFactor, boolean centeredScale) {
        float newHeight = rect.height() * scaleFactor;
        Rect newRect = new Rect(0, 0, rect.right, (int) newHeight);
        if(centeredScale){
            newRect.set(rect.left, (int) (rect.top - ((newHeight-rect.height())/2)), rect.right, (int) (rect.bottom + ((newHeight-rect.height())/2)));
        }else{
            newRect.offset(rect.left, rect.top);
        }
        rect = newRect;
    }

    public void setRectWidth(int width) {
        Rect newRect = new Rect(0, 0, width, rect.bottom);
        newRect.offset(rect.left, rect.top);
        rect = newRect;
    }

    public void setRectHeight(int height) {
        Rect newRect = new Rect(0, 0, rect.right, height);
        newRect.offset(rect.left, rect.top);
        rect = newRect;
    }

    public int getImageHeight() {
        return image.getHeight();
    }

    public int getImageWidth(){
        return image.getWidth();
    }

    public void setHeightInRatio(){
        float whratio = initWidth/ initHeight;
        float accurateHeight = rect.width()/whratio;
        setRectHeight((int) accurateHeight);
    }

    public void setWidthInRatio(){
        float whratio = initWidth/ initHeight;
        float accurateWidth = whratio * rect.height();
        setRectHeight((int) accurateWidth);
    }

    public CustomImage(int id){
        image = ImageLoader.getImage(id);
        initWidth = image.getWidth();
        initHeight = image.getHeight();
        rect = new Rect(0, 0, initWidth, initHeight);
    }

    public void setCenterX(int centerX){
        rect.set(centerX - (rect.width()/2), rect.top, centerX + (rect.width()/2), rect.bottom);
    }

    public void setCenterY(int centerY){
        rect.set(rect.left, centerY - (rect.height()/2), rect.right, centerY + (rect.height()/2));

    }

    public int getCenterX(){
        return ((rect.right + rect.left)/2);
    }

    public int getCenterY(){
        return ((rect.top + rect.bottom)/2);
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(image, null, rect, null);
    }

    public Rect getRect(){
        return rect;
    }

    public boolean opaqueIntersection(Point point){
        if(rect.intersects(point.x, point.y, point.x, point.y)){

            int pixX = (initWidth*(point.x-rect.left))/rect.width();
            int pixY = (initHeight *(point.y-rect.top))/rect.height();

            if(image.getPixel(pixX, pixY) != Color.TRANSPARENT){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    public boolean rectangleIntersection(Point point){
        return (rect.intersects(point.x, point.y, point.x, point.y));
    }
}
