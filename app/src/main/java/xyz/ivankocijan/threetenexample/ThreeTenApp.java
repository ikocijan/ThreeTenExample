package xyz.ivankocijan.threetenexample;

import com.jakewharton.threetenabp.AndroidThreeTen;

import android.app.Application;

/**
 * @author Koc
 *         ivan.kocijan@infinum.hr
 * @since 23/01/16
 */
public class ThreeTenApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AndroidThreeTen.init(this);

    }
}
