package entity;

import jakarta.persistence.*;

@Entity
@Table(name = "EBOOK")
public class EBook extends Book {

    @Column(name = "downloadLink")
    private String downloadLink;

    public EBook() {}

    public EBook(String title, Author author, String downloadLink) {
        super(title, author);
        this.downloadLink = downloadLink;
    }

    public String getDownloadLink() {
        return downloadLink;
    }

    public void setDownloadLink(String downloadLink) {
        this.downloadLink = downloadLink;
    }
}
