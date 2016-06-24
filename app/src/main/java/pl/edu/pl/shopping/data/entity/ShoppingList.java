package pl.edu.pl.shopping.data.entity;

import android.content.ContentValues;

import org.chalup.microorm.annotations.Column;

import pl.edu.pl.shopping.data.database.ItemTable;
import pl.edu.pl.shopping.data.database.ListTable;

/**
 * Created by wojtek on 24.06.16.
 */

public class ShoppingList {

    @Column(ListTable.COLUMN_ID)
    private long id;

    @Column(ListTable.COLUMN_DATETIME)
    private String date;

    @Column(ListTable.COLUMN_AUTHOR)
    private String author;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(ListTable.COLUMN_ID, getId());

        return values;
    }

}
