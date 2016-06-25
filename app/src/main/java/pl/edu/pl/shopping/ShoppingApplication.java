package pl.edu.pl.shopping;

import android.app.Application;
import android.content.ContentResolver;
import android.content.SharedPreferences;

import pl.edu.pl.shopping.presentation.SettingsActivity;

/**
 * Created by wojtek on 24.06.16.
 */

public class ShoppingApplication extends Application {


    private static ContentResolver resolver;
    private static SharedPreferences prefs;


    @Override
    public void onCreate() {
        super.onCreate();

        resolver = this.getContentResolver();
        prefs = getSharedPreferences(SettingsActivity.PREFS_NAME, MODE_PRIVATE);
    }

    public static ContentResolver provideContentResolver() {
        return resolver;
    }

    public static SharedPreferences provideSharedPreferences() {
        return prefs;
    }


}
