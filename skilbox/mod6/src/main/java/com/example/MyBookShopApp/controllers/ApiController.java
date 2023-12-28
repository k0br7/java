package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.book.Book;
import com.example.MyBookShopApp.data.dto.BooksPageDTO;
import com.example.MyBookShopApp.data.dto.SearchWordDTO;
import com.example.MyBookShopApp.service.BookService;
import com.example.MyBookShopApp.service.BooksRatingAndPopulatityService;
import com.example.MyBookShopApp.service.GenresService;
import com.example.MyBookShopApp.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

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
    public BooksPageDTO tags(@PathVariable String slug,@RequestParam("offset") Integer offset, @RequestParam("limit") Integer limit){

        var tag = tagService.getTagBySlug(slug);
        var booksList = bookService.getBooksByTag(tag,offset,limit);

        return new BooksPageDTO(booksList);
    }
}