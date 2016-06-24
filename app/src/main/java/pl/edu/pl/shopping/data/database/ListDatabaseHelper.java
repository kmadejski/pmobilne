package pl.edu.pl.shopping.data.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by kamil on 23.06.16.
 */

public class ListDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "shopping_db";
    private static final int DATABASE_VERSION = 53;

    public ListDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        ListTable.onCreate(database);
        ItemTable.onCreate(database);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        ListTable.onUpgrade(database, oldVersion, newVersion);
        ItemTable.onUpgrade(database, oldVersion, newVersion);
    }
}
