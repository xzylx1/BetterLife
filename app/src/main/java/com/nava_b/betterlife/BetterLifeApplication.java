package com.nava_b.betterlife;

import android.app.Application;
import com.nava_b.betterlife.core.AppAction;
import com.nava_b.betterlife.core.AppActionImpl;


public class BetterLifeApplication extends Application {
    public static AppAction mAction;
    @Override
    public void onCreate() {
        super.onCreate();
        mAction = new AppActionImpl(this);
    }
}
