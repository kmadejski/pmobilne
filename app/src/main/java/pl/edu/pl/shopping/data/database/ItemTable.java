package pl.edu.pl.shopping.data.database;

import android.content.ContentResolver;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import pl.edu.pl.shopping.data.database.contentprovider.ListContentProvider;

/**
 * Created by wojtek on 24.06.16.
 */

public class ItemTable {

    public static final String TABLE_NAME   = "shopping_item";
    public static final String COLUMN_ID    = "id";
    public static final String COLUMN_SHOPPING_LIST_ID    = "shopping_id";
    public static final String COLUMN_NAME    = "name";
    public static final String COLUMN_QUANTITY    = "quantity";

    public static final Uri CONTENT_URI = Uri.withAppendedPath(ListContentProvider.CONTENT_URI, TABLE_NAME);
    public static final String CONTENT_LIST_TYPE =
            ContentResolver.CURSOR_DIR_BASE_TYPE + "/vnd." + ListContentProvider.AUTHORITY + "/" + TABLE_NAME;
    public static final String CONTENT_ITEM_TYPE =
            ContentResolver.CURSOR_ITEM_BASE_TYPE + "/vnd." + ListContentProvider.AUTHORITY + "/" + TABLE_NAME;

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(
                "CREATE TABLE " + TABLE_NAME +
                        "(" +
                        COLUMN_ID + " integer primary key autoincrement, " +
                        COLUMN_NAME + " text not null, " +
                        COLUMN_QUANTITY + " integer not null " +
                        ")"
        );
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(database);
    }

}
