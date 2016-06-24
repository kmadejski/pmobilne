package pl.edu.pl.shopping.data.database.source;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import org.chalup.microorm.MicroOrm;

import java.util.List;

import pl.edu.pl.shopping.ShoppingApplication;
import pl.edu.pl.shopping.data.database.contentprovider.ListContentProvider;
import pl.edu.pl.shopping.data.entity.ListItem;
import pl.edu.pl.shopping.data.entity.ShoppingList;

/**
 * Created by wojtek on 24.06.16.
 */
public class SQLiteSource implements ShoppingSource{

    private ContentResolver resolver;
    private MicroOrm orm;

    public SQLiteSource() {
        this.resolver = ShoppingApplication.provideContentResolver();
        this.orm = new MicroOrm();
    }

    @Override
    public ShoppingList getShoppingList(long id) {
        Uri uri =  Uri.withAppendedPath(ListContentProvider.CONTENT_URI, Long.toString(id));
        Cursor cursor = resolver.query(uri,null,null,null,null);

        cursor.moveToFirst();

        return orm.fromCursor(cursor, ShoppingList.class);
    }

    @Override
    public List<ShoppingList> getShoppingLists() {
        Cursor cursor = resolver.query(ListContentProvider.CONTENT_URI,null,null,null,null);
        return orm.listFromCursor(cursor, ShoppingList.class);
    }

    @Override
    public List<ListItem> getShoppingListItems() {
        return null;
    }
}
