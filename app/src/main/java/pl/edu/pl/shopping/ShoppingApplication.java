package pl.edu.pl.shopping;

import android.app.Application;
import android.content.ContentResolver;

/**
 * Created by wojtek on 24.06.16.
 */

public class ShoppingApplication extends Application {


    private static ContentResolver resolver;

    @Override
    public void onCreate() {
        super.onCreate();

        resolver = this.getContentResolver();
    }

    public static ContentResolver provideContentResolver() {
        return resolver;
    }
}
