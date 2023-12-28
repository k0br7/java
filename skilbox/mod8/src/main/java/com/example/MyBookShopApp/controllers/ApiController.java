package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.ApiResponse;
import com.example.MyBookShopApp.data.book.Book;
import com.example.MyBookShopApp.data.dto.BooksPageDTO;
import com.example.MyBookShopApp.data.dto.SearchWordDTO;
import com.example.MyBookShopApp.data.enums.B2UType;
import com.example.MyBookShopApp.service.*;
import jdk.jfr.Frequency;
import liquibase.pro.packaged.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private GenresService genresService;
    @Autowired
    private BookService bookService;
    @Autowired
    private BooksRatingAndPopulatityService booksRatingAndPopulatityService;
    @Autowired
    private TagService tagService;
    @Autowired
    private BookRatingService bookRatingService;

    @GetMapping("/books/genre/{slug}")
    @ResponseBody
    public BooksPageDTO booksByGenre(@PathVariable(required = true, value = "slug") String slug, @RequestParam("offset") Integer offset, @RequestParam("limit") Integer limit) {
        var genre = genresService.getBySlug(slug);
        var b2g = genresService.getAllByGenre(offset, limit, genre).getContent();
        var booksList = new ArrayList<Book>();

        for (var b : b2g)
            booksList.add(b.getBook());

        return new BooksPageDTO(booksList);
    }

    @GetMapping("/books/popular")
    @ResponseBody
    public BooksPageDTO popular(@RequestParam("offset") Integer offset, @RequestParam("limit") Integer limit) {
        return new BooksPageDTO(booksRatingAndPopulatityService.getPageOfPopularBooks(offset, limit).getContent());
    }

    @GetMapping("/books/recommended")
    @ResponseBody
    public BooksPageDTO recommended(@RequestParam("offset") Integer offset, @RequestParam("limit") Integer limit) {
        return new BooksPageDTO(bookService.getPageOfBooksData(offset, limit).getContent());
    }

    @GetMapping("/books/recent")
    @ResponseBody
    public BooksPageDTO getNextRecentPage(@RequestParam("offset") Integer offset, @RequestParam("limit") Integer limit, @RequestParam("from") String fromPattern, @RequestParam("to") String toPattern) throws ParseException {
        var simpleDateFormat = new SimpleDateFormat("dd.MM.yyy");
        var from = simpleDateFormat.parse(fromPattern);
        var to = simpleDateFormat.parse(toPattern);
        return new BooksPageDTO(bookService.getPageOfBooksDataByPubDate(from, to, offset, limit).getContent());
    }

    @GetMapping("/search/{searchWord}")
    @ResponseBody
    public BooksPageDTO getNextSearchPage(@PathVariable(required = true, value = "searchWord") SearchWordDTO searchWordDTO, @RequestParam("offset") Integer offset, @RequestParam("limit") Integer limit) {
        return new BooksPageDTO(bookService.getPageOfSearchResultBooks(searchWordDTO.getExample(), offset, limit).getContent());
    }

    @GetMapping("/books/author/{authorSlug}")
    @ResponseBody
    public BooksPageDTO getAuthorBooks(@PathVariable(required = true, value = "authorSlug") String authorSlug, @RequestParam("offset") Integer offset, @RequestParam("limit") Integer limit) {
        return new BooksPageDTO(bookService.getPageOfBooksByAuthorSlug(authorSlug, offset, limit));

    }


    @GetMapping("/books/tag/{slug}")
    @ResponseBody
    public BooksPageDTO tags(@PathVariable String slug, @RequestParam("offset") Integer offset, @RequestParam("limit") Integer limit) {

        var tag = tagService.getTagBySlug(slug);
        var booksList = bookService.getBooksByTag(tag, offset, limit);

        return new BooksPageDTO(booksList);
    }

    @Autowired
    private ChangeBookService changeBookService;

    @PostMapping("/changeBookStatus")
    @ResponseBody
    public ResponseEntity<ApiResponse<String>> changeBookStatus(@RequestParam B2UType status, @RequestParam("booksIds[]") Set<Integer> booksIds,
                                                                @CookieValue(name = "KEPT", required = false) String KEPT,
                                                                @CookieValue(name = "CART", required = false) String CART,
                                                                @CookieValue(name = "ARCHIVED", required = false) String ARCHIVED,
                                                                HttpServletResponse httpServletResponse) {
        changeBookService.changeBookStatus(status,booksIds,KEPT,CART,ARCHIVED,httpServletResponse);
        return ResponseEntity.ok(new ApiResponse<>(true, null));
    }

    @PostMapping("/rateBook")
    @ResponseBody
    public ResponseEntity<ApiResponse<String>> rateBook(@RequestParam Integer bookId, @RequestParam Short value) {
        var res = bookRatingService.rateBook(1, bookId, value);
        return ResponseEntity.ok(new ApiResponse<>(res, null));
    }
}