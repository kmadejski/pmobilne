package pl.edu.pl.shopping.data;

import java.util.List;

import pl.edu.pl.shopping.data.database.source.SQLiteSource;
import pl.edu.pl.shopping.data.database.source.ShoppingSource;
import pl.edu.pl.shopping.data.entity.ListItem;
import pl.edu.pl.shopping.data.entity.ShoppingList;

/**
 * Created by wojtek on 24.06.16.
 */
public class ShoppingRepository {

    ShoppingSource source;

    public ShoppingRepository() {
        source = new SQLiteSource();
    }

    public ShoppingList getShoppingList(long id) {
        return source.getShoppingList(id);
    }

    public List<ShoppingList> getShoppingLists() {
        return source.getShoppingLists();
    }

    public List<ListItem> getShoppingListItems(long id) {
        return source.getShoppingListItems(id);
    }

    public ShoppingList createShoppingList(ShoppingList shoppingList) {
        return source.createShoppingList(shoppingList.toContentValue());
    }

    public ListItem createListItem(ListItem listItem) {
        return this.source.createListItem(listItem.toContentValue());
    }

    public boolean checkItem(long id, boolean checked) {
        return this.source.checkItem(id, checked);
    }



}
