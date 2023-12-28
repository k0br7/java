package com.example.MyBookShopApp.data.tags;

import com.example.MyBookShopApp.data.book.links.Book2Tag;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "tag")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String slug;

    @JsonIgnore
    @OneToMany(mappedBy = "tag", fetch = FetchType.LAZY)
    private Set<Book2Tag> book2Tags;

    public Tag() {
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Book2Tag> getBook2Tags() {
        return book2Tags;
    }

    public void setBook2Tags(Set<Book2Tag> book2Tags) {
        this.book2Tags = book2Tags;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
