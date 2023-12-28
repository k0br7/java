package com.example.MyBookShopApp.repository;

import com.example.MyBookShopApp.data.book.Book;
import com.example.MyBookShopApp.data.book.review.BookReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookReviewEntityRepository extends JpaRepository<BookReviewEntity,Integer> {

    List<BookReviewEntity> findAllByBook(Book book);

}
