package com.example.MyBookShopApp.data.book;

import com.example.MyBookShopApp.data.user.UserEntity;

import javax.persistence.*;

@Entity
@Table(name = "book_rating")
public class BookRating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Book book;
    @OneToOne
    private UserEntity user;
    @Column(name = "rating", nullable = false)
    private short rating;

    public BookRating() {
    }

    @Override
    public String toString() {
        return "BookRating{" +
                "id=" + id +
                ", book=" + book.getId() +
                ", user=" + user.getId() +
                ", rating=" + rating +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public short getRating() {
        return rating;
    }

    public void setRating(short rating) {
        this.rating = rating;
    }
}
