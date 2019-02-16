package com.robinson.netanya.squarelaunch.ImageLoader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.robinson.netanya.squarelaunch.AppConstants;
import com.robinson.netanya.squarelaunch.R;

public class ImageLoader {

    public static Bitmap playButton;
    public static Bitmap settingsButton;
    public static Bitmap statsButton;
    public static Bitmap spikes;
    public static Bitmap resetButton;


    public ImageLoader(){
        BitmapFactory bmf = new BitmapFactory();
        playButton = bmf.decodeResource(AppConstants.CURRENT_CONTEXT.getResources(), R.drawable.playbutton);
        settingsButton = bmf.decodeResource(AppConstants.CURRENT_CONTEXT.getResources(), R.drawable.settingsbutton);
        statsButton = bmf.decodeResource(AppConstants.CURRENT_CONTEXT.getResources(), R.drawable.statsbutton);
        spikes = bmf.decodeResource(AppConstants.CURRENT_CONTEXT.getResources(), R.drawable.spikes);
        resetButton = bmf.decodeResource(AppConstants.CURRENT_CONTEXT.getResources(), R.drawable.reset);
    }

    public static Bitmap getImage(int id){
        switch(id){
            case(R.drawable.playbutton):
                return playButton;
            case(R.drawable.statsbutton):
                return statsButton;
            case(R.drawable.settingsbutton):
                return settingsButton;
            case(R.drawable.spikes):
                return spikes;
            case(R.drawable.reset):
                return resetButton;
        }
        return null;
    }
}
