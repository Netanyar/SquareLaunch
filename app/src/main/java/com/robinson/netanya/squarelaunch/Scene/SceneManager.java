package com.robinson.netanya.squarelaunch.Scene;

import android.graphics.Canvas;
import android.view.MotionEvent;

import java.util.ArrayList;

public class SceneManager {

    private ArrayList<Scene> scenes = new ArrayList<Scene>();
    private SceneType activeScene = SceneType.MAIN_MENU;

    public SceneManager(){
        populateScenes();
    }

    public void populateScenes(){
        scenes.add(new MainMenuScene(this));
        scenes.add(new GameplayScene(this));
        scenes.add(new GameoverScene(this));
    }

    public void draw(Canvas canvas){
        scenes.get(activeScene.ordinal()).draw(canvas);
    }

    public void update(){
        scenes.get(activeScene.ordinal()).update();
    }

    public void setActiveScene(SceneType scene){
        this.activeScene = scene;
    }

    public void onTouchEvent(MotionEvent event){
        scenes.get(activeScene.ordinal()).onTouchEvent(event);
    }
}
