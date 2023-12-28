package com.example.MyBookShopApp.repository;

import com.example.MyBookShopApp.data.book.links.Book2UserEntity;
import com.example.MyBookShopApp.data.book.links.Book2UserTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Book2UserRepository extends JpaRepository<Book2UserEntity,Integer> {
    List<Book2UserEntity> findAllByType(Book2UserTypeEntity type);

    @Query(value = "SELECT * FROM book2user;",nativeQuery = true)
    List<Book2UserEntity> custom();

}
