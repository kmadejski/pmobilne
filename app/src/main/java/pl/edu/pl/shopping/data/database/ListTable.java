package pl.edu.pl.shopping.data.database;

import android.content.ContentResolver;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import pl.edu.pl.shopping.data.database.contentprovider.ListContentProvider;

public class ListTable {

    public static final String TABLE_NAME   = "shopping";
    public static final String COLUMN_ID    = "id";
    public static final String COLUMN_DATETIME  = "datetime";
    public static final String COLUMN_AUTHOR    = "author";

    public static final Uri CONTENT_URI = Uri.withAppendedPath(ListContentProvider.CONTENT_URI, TABLE_NAME);
    public static final String CONTENT_LIST_TYPE =
            ContentResolver.CURSOR_DIR_BASE_TYPE + "/vnd." + ListContentProvider.AUTHORITY + "/" + TABLE_NAME;
    public static final String CONTENT_ITEM_TYPE =
            ContentResolver.CURSOR_ITEM_BASE_TYPE + "/vnd." + ListContentProvider.AUTHORITY + "/" + TABLE_NAME;

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(
            "CREATE TABLE " + TABLE_NAME +
            "(" +
                COLUMN_ID + " INTEGER NOT NULL PRIMARY KEY, " +
                COLUMN_DATETIME + " integer not null default(current_timestamp), " +
                COLUMN_AUTHOR + " text " +
            ")"
        );
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        Log.w(ListTable.class.getName(), "Upgrading database from version " + oldVersion + " to " + newVersion + ". This operation will destroy all data!");
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(database);
    }

}
