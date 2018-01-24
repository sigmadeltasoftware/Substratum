package be.sigmadelta.substratumdemo;

import android.app.Application;

import timber.log.Timber;

/**
 * Creator: Bojan Belic
 * Date:    24/01/18
 * Company: Sigma Delta Software Solutions
 */

public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
    }
}
