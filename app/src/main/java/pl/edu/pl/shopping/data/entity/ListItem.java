package pl.edu.pl.shopping.data.entity;

import android.content.ContentValues;

import org.chalup.microorm.annotations.Column;

import pl.edu.pl.shopping.data.database.ItemTable;

/**
 * Created by wojtek on 24.06.16.
 */

public class ListItem {

    @Column(ItemTable.COLUMN_ID)
    private Long id;

    @Column(ItemTable.COLUMN_SHOPPING_LIST_ID)
    private long shoppingID;

    @Column(ItemTable.COLUMN_NAME)
    private String name;

    @Column(ItemTable.COLUMN_QUANTITY)
    private String quantity;

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getShoppingID() {
        return shoppingID;
    }

    public void setShoppingID(long shoppingID) {
        this.shoppingID = shoppingID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public ContentValues toContentValue() {
        ContentValues values = new ContentValues();
        values.put(ItemTable.COLUMN_ID, getId());
        values.put(ItemTable.COLUMN_NAME, getName());
        values.put(ItemTable.COLUMN_QUANTITY, getQuantity());
        values.put(ItemTable.COLUMN_SHOPPING_LIST_ID, getShoppingID());

        return values;
    }


}
