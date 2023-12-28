package com.example.MyBookShopApp.repository;

import com.example.MyBookShopApp.data.book.Book;
import com.example.MyBookShopApp.data.book.links.Book2GenreEntity;
import com.example.MyBookShopApp.data.genre.GenreEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Book2GenreEntityRepository extends JpaRepository<Book2GenreEntity,Long> {

    Page<Book2GenreEntity> findAllBookByGenre(GenreEntity genre, Pageable nextPage);
}
