package pl.edu.pl.shopping.contentprovider;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.util.Arrays;
import java.util.HashSet;

import pl.edu.pl.shopping.database.ListDatabaseHelper;
import pl.edu.pl.shopping.database.ListTable;

/**
 * Created by kamil on 23.06.16.
 */

public class ListContentProvider extends ContentProvider {

    private ListDatabaseHelper database;

    private static final int LISTS = 10;
    private static final int LIST_ID = 20;

    private static final String AUTHORITY = "pl.edu.pl.shopping.contentprovider";
    private static final String BASE_PATH = "lists";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH);
    public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/lists";
    public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/list";
    private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sURIMatcher.addURI(AUTHORITY, BASE_PATH, LISTS);
        sURIMatcher.addURI(AUTHORITY, BASE_PATH + "/#", LIST_ID);
    }

    @Override
    public boolean onCreate() {
        database = new ListDatabaseHelper(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        checkColumns(projection);
        queryBuilder.setTables(ListTable.TABLE_NAME);

        int uriType = sURIMatcher.match(uri);

        switch(uriType) {
            case LISTS:
                break;
            case LIST_ID:
                queryBuilder.appendWhere(ListTable.COLUMN_ID + "=" + uri.getLastPathSegment());
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
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        int uriType = sURIMatcher.match(uri);
        SQLiteDatabase sqlDB = database.getWritableDatabase();
        long id = 0;
        switch(uriType) {
            case LISTS:
                id = sqlDB.insert(ListTable.TABLE_NAME, null, values);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return Uri.parse(BASE_PATH + "/" + id);
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

    private void checkColumns(String[] projection) {
        String[] available = {
                ListTable.COLUMN_CONTENT, ListTable.COLUMN_AUTHOR, ListTable.COLUMN_DATETIME, ListTable.COLUMN_STATUS, ListTable.COLUMN_ID
        };
        if(projection != null) {
            HashSet<String> requestedColumns = new HashSet<String>(Arrays.asList(projection));
            HashSet<String> availableColumns = new HashSet<String>(Arrays.asList(available));

            if(!availableColumns.containsAll(requestedColumns)) {
                throw new IllegalArgumentException("Unknown columns in projection");
            }
        }
    }
}
