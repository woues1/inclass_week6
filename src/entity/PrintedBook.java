package entity;

import jakarta.persistence.*;

@Entity
@Table(name = "PRINTED_BOOK")
public class PrintedBook extends Book {

    @Column(name = "pageCount")
    private int pageCount;

    public PrintedBook() {}

    public PrintedBook(String title, Author author, int pageCount) {
        super(title, author);
        this.pageCount = pageCount;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
}
