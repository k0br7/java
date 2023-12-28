package com.example.MyBookShopApp.repository;

import com.example.MyBookShopApp.data.author.Author;
import com.example.MyBookShopApp.data.book.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
    Page<Book> findBookByTitleContainingIgnoreCase(String bookTitle, Pageable nextPage);
    Page<Book> findAllByPubDateBetweenOrderByPubDateDesc(Date from, Date to, Pageable nextPage);

    Page<Book> findAllByOrderByStatPDesc(Pageable nextPage);
    List<Book> findAllByOrderByStatPDesc();

    @Transactional
    @Modifying
    @Query(value = "UPDATE book SET statb=(select statbcount from (select book_id as bkid,count(user_id) as statbcount from book2user join book2user_type on book2user.type_id=book2user_type.id where book2user.type_id =2 group by book2user.book_id) as f where f.bkid =book.id)", nativeQuery = true)
    void updStatB();
    @Transactional
    @Modifying
    @Query(value = "UPDATE book SET statc=(select statbcount from (select book_id as bkid,count(user_id) as statbcount from book2user join book2user_type on book2user.type_id=book2user_type.id where book2user.type_id =1 group by book2user.book_id) as f where f.bkid =book.id)", nativeQuery = true)
    void updStatC();
    @Transactional
    @Modifying
    @Query(value = "UPDATE book SET statk=(select statbcount from (select book_id as bkid,count(user_id) as statbcount from book2user join book2user_type on book2user.type_id=book2user_type.id where book2user.type_id =3 group by book2user.book_id) as f where f.bkid =book.id)", nativeQuery = true)
    void updStatK();
    @Transactional
    @Modifying
    @Query(value = "UPDATE book SET statp=(select tmpp from (select id,(statb+0.7*statc+0.4*statk) as tmpp from book) as f where f.id=book.id)", nativeQuery = true)
    void updStatP();

    @Transactional
    @Modifying
    @Query(value = "update book set statp=0 where statp is null;update book set statb=0 where statb is null;update book set statc=0 where statc is null;update book set statk=0 where statk is null;", nativeQuery = true)
    void updNullStat();

   Optional<Book> findBySlug(String slug);
}
