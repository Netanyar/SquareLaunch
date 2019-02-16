package com.robinson.netanya.squarelaunch;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;

import com.robinson.netanya.squarelaunch.ImageLoader.ImageLoader;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        AppConstants.SCREEN_HEIGHT = dm.heightPixels;
        AppConstants.SCREEN_WIDTH = dm.widthPixels;
        AppConstants.CURRENT_CONTEXT = this;

        new ImageLoader();

        setContentView(new GameView(this));
    }
}
