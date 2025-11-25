package com.example.rere;

import android.app.Application;

/**
 * Simple Application class so it cannot crash on startup.
 * Room database / repositories are not used by the current code,
 * so all heavy initialization is removed.
 */
public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // no heavy initialization here
    }
}
