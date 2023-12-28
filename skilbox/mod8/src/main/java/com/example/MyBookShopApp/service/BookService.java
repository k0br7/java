package com.example.MyBookShopApp.service;

import com.example.MyBookShopApp.data.author.Author;
import com.example.MyBookShopApp.data.book.Book;
import com.example.MyBookShopApp.data.book.links.Book2AuthorEntity;
import com.example.MyBookShopApp.data.tags.Tag;
import com.example.MyBookShopApp.repository.AuthorRepository;
import com.example.MyBookShopApp.repository.Book2AuthorEntityRepository;
import com.example.MyBookShopApp.repository.Book2TagRepository;
import com.example.MyBookShopApp.repository.BookRepository;
import liquibase.pro.packaged.B;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.net.ContentHandler;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;
    @Autowired
    private Book2AuthorEntityRepository book2AuthorEntityRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private Book2TagRepository book2TagRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

        public List<Book> getBooksData() {
        return bookRepository.findAll();
    }

    public Page<Book> getPageOfBooksData(Integer offset, Integer limit) {
        var nextPage = PageRequest.of(offset, limit);
        return bookRepository.findAll(nextPage);
    }
    public Page<Book> getPageOfBooksDataByPubDate(Date from, Date to, Integer offset, Integer limit) {
        var nextPage = PageRequest.of(offset, limit);
        return bookRepository.findAllByPubDateBetweenOrderByPubDateDesc(from,to,nextPage);
    }

    public Page<Book> getPageOfSearchResultBooks(String searchWord, Integer offset, Integer limit) {
        var nextPage = PageRequest.of(offset, limit);
        return bookRepository.findBookByTitleContainingIgnoreCase(searchWord, nextPage);
    }

    public List<Book> getBooksByAuthor(Author author,Integer offset, Integer limit) {
        var nextPage = PageRequest.of(offset, limit);
        var b2a = book2AuthorEntityRepository.findAllByAuthor(author,nextPage).getContent();
        var books = new ArrayList<Book>();

        for(var b2aitem : b2a)
        {
            books.add(b2aitem.getBook());
        }

        return books;
    }
    public List<Book> getRecentBooksByAuthor(Author author,Integer offset, Integer limit) {
        var nextPage = PageRequest.of(offset, limit);
        var b2a = book2AuthorEntityRepository.findAllByAuthor(author,nextPage).getContent();
        var books = new ArrayList<Book>();

        for(var b2aitem : b2a)
        {
            books.add(b2aitem.getBook());
        }

        return books;
    }

    public int countByAuthor(Author author){
        return book2AuthorEntityRepository.countByAuthor(author);
    }

    public List<Book> getPageOfBooksByAuthorSlug(String authorSlug, Integer offset, Integer limit) {
        var author = authorRepository.findBySlug(authorSlug);
        var nextPage = PageRequest.of(offset, limit);
        var b2a = book2AuthorEntityRepository.findAllByAuthor(author,nextPage).getContent();
        var books = new ArrayList<Book>();

        for(var b2aitem : b2a)
        {
            books.add(b2aitem.getBook());
        }

        return books;
    }

    public List<Book> getBooksByTag(Tag tag,Integer offset, Integer limit) {
        var nextPage = PageRequest.of(offset,limit);
        var b2t = book2TagRepository.findByTag(tag,nextPage).getContent();
        var books = new ArrayList<Book>();

        for (var b2tItem : b2t)
            books.add(b2tItem.getBook());

        return books;
    }

    public Optional<Book> findBySlug(String slug) {
        return bookRepository.findBySlug(slug);
    }

    public List<Book> getBookFromCookie(String ids) {
        var cookieBooksIdsList = ids.split("/");
        var booksIds = new ArrayList<Integer>();
        for (var bookId : cookieBooksIdsList) {
            booksIds.add(Integer.parseInt(bookId));
        }
        return bookRepository.findAllById(booksIds);
    }
}
