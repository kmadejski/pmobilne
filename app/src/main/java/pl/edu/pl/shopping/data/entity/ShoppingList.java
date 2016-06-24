package pl.edu.pl.shopping.data.entity;

import org.chalup.microorm.annotations.Column;

import pl.edu.pl.shopping.data.database.ListTable;

/**
 * Created by wojtek on 24.06.16.
 */

public class ShoppingList {

    @Column(ListTable.COLUMN_ID)
    private long id;

    @Column(ListTable.COLUMN_DATETIME)
    private String date;

    @Column(ListTable.COLUMN_STATUS)
    private String status;

    @Column(ListTable.COLUMN_AUTHOR)
    private String author;

    @Column(ListTable.COLUMN_CONTENT)
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

}
