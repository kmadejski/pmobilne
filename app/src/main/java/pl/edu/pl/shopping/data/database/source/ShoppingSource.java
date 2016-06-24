package pl.edu.pl.shopping.data.database.source;

import android.content.ContentValues;

import java.util.List;

import pl.edu.pl.shopping.data.entity.ListItem;
import pl.edu.pl.shopping.data.entity.ShoppingList;

/**
 * Created by wojtek on 24.06.16.
 */
public interface ShoppingSource {

    ShoppingList getShoppingList(long id);
    List<ShoppingList> getShoppingLists();
    List<ListItem> getShoppingListItems(long id);
    ShoppingList createShoppingList(ContentValues values);
    ListItem createListItem(ContentValues values);

}
