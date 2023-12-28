package com.example.MyBookShopApp.data.book;

import com.example.MyBookShopApp.data.author.Author;
import com.example.MyBookShopApp.data.book.links.*;
import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "DATE NOT NULL", name = "pub_date")
    private Date pubDate;

    @Column(columnDefinition = "SMALLINT NOT NULL", name = "is_bestseller")
    private short isBestseller;
    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String slug;
    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String title;
    @Column(columnDefinition = "VARCHAR(255)",unique = true)
    private String image;

    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(columnDefinition = "INT NOT NULL")
    private Integer price;
    @Column(columnDefinition = "SMALLINT NOT NULL DEFAULT 0")
    private short discount;

    @JsonIgnore
    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    private Set<Book2UserEntity> book2UserEntitySet;

    @JsonManagedReference
    @OneToMany(mappedBy = "book", fetch = FetchType.EAGER)
    private Set<Book2AuthorEntity> book2AuthorEntities;

    @JsonIgnore
    @OneToMany(mappedBy = "book", fetch = FetchType.EAGER)
    private Set<Book2GenreEntity> book2GenreEntities;
    @JsonIgnore
    @OneToMany(mappedBy = "book", fetch = FetchType.EAGER)
    private Set<Book2Tag> book2Tags;

//    B — количество пользователей, купивших книгу,
//    C — количество пользователей, у которых книга находится в корзине, а
//    K — количество пользователей, у которых книга отложена.

    @Column(columnDefinition = "integer default 0")
    private int statB;
    @Column(columnDefinition = "integer default 0")
    private int statC;
    @Column(columnDefinition = "integer default 0")
    private int statK;
    @Column(columnDefinition = "Decimal(10,5) default '0'")
    private double statP;


    public Book() {
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", statB=" + statB +
                ", statC=" + statC +
                ", statK=" + statK +
                ", statP=" + statP +
                '}';
    }


    public Set<Book2Tag> getBook2Tags() {
        return book2Tags;
    }

    public void setBook2Tags(Set<Book2Tag> book2Tags) {
        this.book2Tags = book2Tags;
    }

    public Set<Book2GenreEntity> getBook2GenreEntities() {
        return book2GenreEntities;
    }

    public void setBook2GenreEntities(Set<Book2GenreEntity> book2GenreEntities) {
        this.book2GenreEntities = book2GenreEntities;
    }

    public int getStatB() {
        return statB;
    }

    public void setStatB(int statB) {
        this.statB = statB;
    }

    public int getStatC() {
        return statC;
    }

    public void setStatC(int statC) {
        this.statC = statC;
    }

    public int getStatK() {
        return statK;
    }

    public void setStatK(int statK) {
        this.statK = statK;
    }

    public double getStatP() {
        return statP;
    }

    public void setStatP(double statP) {
        this.statP = statP;
    }

    @JsonProperty("discountPrice")
    public int getDiscountPrice() {
        return price-price*discount/100;
    }

    @JsonGetter("authors")
    public List<Author> getAuthors() {
        var authors = new ArrayList<Author>();
        for (var b2a : this.book2AuthorEntities) {
            authors.add(b2a.getAuthor());
        }
        return authors;
    }

    public Set<Book2AuthorEntity> getBook2AuthorEntities() {
        return book2AuthorEntities;
    }

    public void setBook2AuthorEntities(Set<Book2AuthorEntity> book2AuthorEntities) {
        this.book2AuthorEntities = book2AuthorEntities;
    }

    public Set<Book2UserEntity> getBook2UserEntitySet() {
        return book2UserEntitySet;
    }

    public void setBook2UserEntitySet(Set<Book2UserEntity> book2UserEntitySet) {
        this.book2UserEntitySet = book2UserEntitySet;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public short getIsBestseller() {
        return isBestseller;
    }

    public void setIsBestseller(short isBestseller) {
        this.isBestseller = isBestseller;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public short getDiscount() {
        return discount;
    }

    public void setDiscount(short discount) {
        this.discount = discount;
    }


}

