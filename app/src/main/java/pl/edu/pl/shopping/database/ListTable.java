package pl.edu.pl.shopping.database;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class ListTable {

    public static final String TABLE_NAME   = "shopping";
    public static final String COLUMN_ID    = "id";
    public static final String COLUMN_DATETIME  = "datetime";
    public static final String COLUMN_STATUS    = "status";
    public static final String COLUMN_AUTHOR    = "author";
    public static final String COLUMN_CONTENT   = "content";

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(
            "CREATE TABLE " + TABLE_NAME +
            "(" +
                COLUMN_ID + " integer primary key autoincrement, " +
                COLUMN_DATETIME + " integer not null default(current_timestamp) " +
                COLUMN_STATUS + " integer not null " +
                COLUMN_AUTHOR + " text not null " +
                COLUMN_CONTENT + " text not null " +
            ")"
        );
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        Log.w(ListTable.class.getName(), "Upgrading database from version " + oldVersion + " to " + newVersion + ". This operation will destroy all data!");
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(database);
    }

}
