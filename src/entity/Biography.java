package entity;

import jakarta.persistence.*;

@Entity
@Table(name = "BIOGRAPHY")
public class Biography {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "details", columnDefinition = "TEXT")
    private String details;

    @OneToOne
    @JoinColumn(name = "author_id")
    private Author author;

    public Biography() {}

    public Biography(String details, Author author) {
        this.details = details;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
