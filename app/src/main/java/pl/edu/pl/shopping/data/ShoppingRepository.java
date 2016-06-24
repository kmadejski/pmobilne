package pl.edu.pl.shopping.data;

import java.util.List;

import pl.edu.pl.shopping.data.database.source.SQLiteSource;
import pl.edu.pl.shopping.data.database.source.ShoppingSource;
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

    public ShoppingList createShoppingList(ShoppingList shoppingList) {
        return source.createShoppingList(shoppingList.toContentValues());
    }


}
