package com.example.MyBookShopApp.data.user;

import com.example.MyBookShopApp.data.book.links.Book2UserEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String hash;

    @Column(columnDefinition = "TIMESTAMP NOT NULL")
    private LocalDateTime regTime;

    @Column(columnDefinition = "INT NOT NULL")
    private int balance;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String name;

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    private Set<Book2UserEntity> user2bookEntitySet;

    public UserEntity() {
    }

    public Set<Book2UserEntity> getUser2bookEntitySet() {
        return user2bookEntitySet;
    }

    public void setUser2bookEntitySet(Set<Book2UserEntity> user2bookEntitySet) {
        this.user2bookEntitySet = user2bookEntitySet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public LocalDateTime getRegTime() {
        return regTime;
    }

    public void setRegTime(LocalDateTime regTime) {
        this.regTime = regTime;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", hash='" + hash + '\'' +
                ", regTime=" + regTime +
                ", balance=" + balance +
                ", name='" + name + '\'' +
                ", user2bookEntitySet=" + user2bookEntitySet +
                '}';
    }
}
