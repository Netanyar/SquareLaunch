package com.robinson.netanya.squarelaunch.GameObjects;

import android.graphics.Canvas;
import android.util.Log;

import com.robinson.netanya.squarelaunch.Scene.SceneManager;
import com.robinson.netanya.squarelaunch.Scene.SceneType;

import java.util.ArrayList;
import java.util.HashSet;

public class ObstacleManager {

    private ArrayList<GameObject> obstacles = new ArrayList<GameObject>();
    private HashSet<GameObject> passedObstacles = new HashSet<GameObject>();
    private RectPlayer rectPlayer;
    private static int score = 0;
    private static int highscore = 0;
    private SceneManager sceneManager;

    public static void incrementScore(){
        score++;
        highscore = Math.max(highscore, score);
    }

    public static int getScore(){
        return score;
    }

    public static void resetScore(){
        score = 0;
    }

    public static int getHighscore(){
        return highscore;
    }

    public ObstacleManager(RectPlayer rectPlayer, SceneManager sceneManager){
        this.rectPlayer = rectPlayer;
        populateObstacles();
        this.sceneManager = sceneManager;
    }

    private long currentTime = 0;
    public void populateObstacles(){
        if(currentTime == 0){
            long currentTime = System.currentTimeMillis();
        }
        long elapsedTime = System.currentTimeMillis() - currentTime;
        int speed = (int) ((Math.random() * elapsedTime/1000) + 1);

        if(obstacles.isEmpty()){
            obstacles.add(new SpikeObstacle(speed, 250, this));
            Log.d("obstacles", " speed = " + speed);
        }
        if(passedObstacles.contains(obstacles.get(obstacles.size() - 1))){
            obstacles.remove(obstacles.size() - 1);
        }
    }

    public void draw(Canvas canvas) {
        for (GameObject obstacle : obstacles) {
            obstacle.draw(canvas);
        }
    }

    public void update(){
        populateObstacles();
        incrementScore();
        for(GameObject obstacle : obstacles){
            obstacle.update();
            if(obstacle.collision(rectPlayer.getRect())){
                sceneManager.setActiveScene(SceneType.GAME_OVER);
                reset();

            }
        }
    }

    public void objectOffScreen(GameObject gameObject){
        passedObstacles.add(gameObject);
    }

    public void reset(){
        currentTime = 0;
        obstacles.clear();
    }

}
