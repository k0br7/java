package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.book.Book;
import com.example.MyBookShopApp.data.dto.BooksPageDTO;
import com.example.MyBookShopApp.service.BookService;
import com.example.MyBookShopApp.service.BooksRatingAndPopulatityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BooksController {

    private BookService bookService;

    @Autowired
    private BooksRatingAndPopulatityService booksRatingAndPopulatityService;


    @Autowired
    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("recent")
    public String recentPage() {
        return "books/recent";
    }

    @GetMapping("popular")
    public String popularPage() {
        return "books/popular";
    }


    @ModelAttribute("booksList")
    public List<Book> bookList() {
        return booksRatingAndPopulatityService.getPageOfPopularBooks(0, 5).getContent();
    }

    @ModelAttribute("recentBookList")
    public List<Book> recentBookList() throws ParseException {
        var simpleDateFormat = new SimpleDateFormat("dd.MM.yyy");
        var from = simpleDateFormat.parse("01.01.2000");
        var to = simpleDateFormat.parse("01.01.2016");
        return bookService.getPageOfBooksDataByPubDate(from, to, 0, 10).getContent();
    }
}
