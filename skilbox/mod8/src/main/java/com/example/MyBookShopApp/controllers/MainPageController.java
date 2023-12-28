package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.book.Book;
import com.example.MyBookShopApp.data.dto.BooksPageDTO;
import com.example.MyBookShopApp.data.dto.SearchWordDTO;
import com.example.MyBookShopApp.data.tags.Tag;
import com.example.MyBookShopApp.service.BookService;
import com.example.MyBookShopApp.service.BooksRatingAndPopulatityService;
import com.example.MyBookShopApp.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@Controller
public class MainPageController {
    @Autowired
    private TagService tagCloudService;
    private final BookService bookService;
    @Autowired
    private BooksRatingAndPopulatityService booksRatingAndPopulatityService;


    @Autowired
    public MainPageController(BookService bookService) {
        this.bookService = bookService;
    }

    @ModelAttribute("recommendedBooks")
    public List<Book> recommendedBooks() {
        return bookService.getPageOfBooksData(0, 6).getContent();
    }

    @ModelAttribute("newBooks")
    public List<Book> newBooks() throws ParseException {
        var simpleDateFormat = new SimpleDateFormat("dd.MM.yyy");
        var from = simpleDateFormat.parse("01.04.2003");
        var to = simpleDateFormat.parse("01.01.2020");
        return bookService.getPageOfBooksDataByPubDate(from, to, 0, 6).getContent();
    }

    @ModelAttribute("popularBooks")
    public List<Book> popularBooks() {
        return booksRatingAndPopulatityService.getPageOfPopularBooks(0, 6).getContent();
    }

    @GetMapping("/")
    public String mainPage() {
        return "index";
    }

    @ModelAttribute("tagcloud")
    public Map<Tag,String> getTagCloud(){
        return tagCloudService.getTagCloud();
    }


    @ModelAttribute("searchWordDTO")
    public SearchWordDTO searchWordDTO(){
        return new SearchWordDTO();
    }
}
