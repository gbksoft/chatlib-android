package net.gbksoft.chatlibrary;

import android.app.Application;

import com.orm.SugarContext;

/**
 *
 * Need extends this class for a work with a library or add methods in a dependency project
 */

public class ChatApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SugarContext.init(getApplicationContext());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        SugarContext.terminate();
    }
}
