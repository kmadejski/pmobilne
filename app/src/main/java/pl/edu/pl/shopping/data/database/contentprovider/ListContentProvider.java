package pl.edu.pl.shopping.data.database.contentprovider;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.graphics.Paint;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import java.util.Arrays;
import java.util.HashSet;

import pl.edu.pl.shopping.data.database.ItemTable;
import pl.edu.pl.shopping.data.database.ListDatabaseHelper;
import pl.edu.pl.shopping.data.database.ListTable;

/**
 * Created by kamil on 23.06.16.
 */

public class ListContentProvider extends ContentProvider {

    private ListDatabaseHelper database;

    private static final int LISTS = 10;
    private static final int LIST_ID = 20;

    private static final int ITEM_LIST = 30;
    private static final int ITEM_ID = 40;

    public static final String AUTHORITY = "pl.edu.pl.shopping";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/");

    private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sURIMatcher.addURI(AUTHORITY, "shopping", LISTS);
        sURIMatcher.addURI(AUTHORITY, "shopping/#", LIST_ID);
        sURIMatcher.addURI(AUTHORITY, "shopping_item", ITEM_LIST);
        sURIMatcher.addURI(AUTHORITY, "shopping_item/#", ITEM_ID);
    }

    @Override
    public boolean onCreate() {
        database = new ListDatabaseHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();


        int uriType = sURIMatcher.match(uri);

        switch(uriType) {
            case LISTS:
                queryBuilder.setTables(ListTable.TABLE_NAME);
                break;
            case LIST_ID:
                queryBuilder.setTables(ListTable.TABLE_NAME);
                queryBuilder.appendWhere(ListTable.COLUMN_ID + "=" + uri.getLastPathSegment());
                break;
            case ITEM_LIST:
                queryBuilder.setTables(ItemTable.TABLE_NAME);
                break;
            default:
                throw new IllegalArgumentException("Unknow URI: " + uri);
        }

        SQLiteDatabase db = database.getWritableDatabase();
        Cursor cursor = queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        switch (sURIMatcher.match(uri)) {
            case LIST_ID:
                return ListTable.CONTENT_ITEM_TYPE;
            case LISTS:
                return ListTable.CONTENT_ITEM_TYPE;
            case ITEM_ID:
                return ItemTable.CONTENT_ITEM_TYPE;
            case ITEM_LIST:
                return ItemTable.CONTENT_LIST_TYPE;
            default:
                throw new IllegalArgumentException();
        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase sqlDB = database.getWritableDatabase();
        Uri result = Uri.parse("dsa");
        long id;

        switch(sURIMatcher.match(uri)) {
            case LISTS:
                try {
                    id = sqlDB.insertOrThrow(ListTable.TABLE_NAME, null, values);
                    result = Uri.withAppendedPath(ListTable.CONTENT_URI, Long.toString(id));;
                }catch (Exception ex) {
                    Log.d("dsa", ex.getMessage());
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return result;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int uriType = sURIMatcher.match(uri);
        SQLiteDatabase sqlDB = database.getWritableDatabase();
        int rowsDeleted = 0;
        switch (uriType) {
            case LISTS:
                rowsDeleted = sqlDB.delete(ListTable.TABLE_NAME, selection, selectionArgs);
                break;
            case LIST_ID:
                String id = uri.getLastPathSegment();
                if(TextUtils.isEmpty(selection)) {
                    rowsDeleted = sqlDB.delete(ListTable.TABLE_NAME, ListTable.COLUMN_ID + "=" + id, null);
                }
                else {
                    rowsDeleted = sqlDB.delete(ListTable.TABLE_NAME, ListTable.COLUMN_ID + "=" + id + " AND " + selection, selectionArgs);
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int uriType = sURIMatcher.match(uri);
        SQLiteDatabase sqlDB = database.getWritableDatabase();
        int rowsUpdated = 0;
        switch (uriType) {
            case LISTS:
                rowsUpdated = sqlDB.update(ListTable.TABLE_NAME, values, selection, selectionArgs);
                break;
            case LIST_ID:
                String id = uri.getLastPathSegment();
                if(TextUtils.isEmpty(selection)) {
                    rowsUpdated = sqlDB.update(ListTable.TABLE_NAME, values, ListTable.COLUMN_ID + "=" + id, null);
                }
                else {
                    rowsUpdated = sqlDB.update(ListTable.TABLE_NAME, values, ListTable.COLUMN_ID + "=" + id + " AND " + selection, selectionArgs);
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsUpdated;
    }

}
