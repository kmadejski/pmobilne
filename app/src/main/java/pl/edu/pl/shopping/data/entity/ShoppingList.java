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
    private Long id;

    @Column(ListTable.COLUMN_DATETIME)
    private String date;

    @Column(ListTable.COLUMN_AUTHOR)
    private String author;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(ListTable.COLUMN_NAME)

    private String name;

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

    public Long getId() {
        return id != null ? id : null;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ContentValues toContentValue() {
        ContentValues values = new ContentValues();
        values.put(ListTable.COLUMN_ID, getId());
        values.put(ListTable.COLUMN_NAME, getName());
        return values;
    }

}
