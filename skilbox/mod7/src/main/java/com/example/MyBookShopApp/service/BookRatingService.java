package com.example.MyBookShopApp.service;

import com.example.MyBookShopApp.data.book.Book;
import com.example.MyBookShopApp.data.book.BookRating;
import com.example.MyBookShopApp.data.dto.BookRatingDTO;
import com.example.MyBookShopApp.repository.BookRatingRepository;
import com.example.MyBookShopApp.repository.BookRepository;
import com.example.MyBookShopApp.repository.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class BookRatingService {

    private BookRatingRepository bookRatingRepository;
    private BookRepository bookRepository;
    private UserEntityRepository userEntityRepository;

    @Autowired
    public BookRatingService(BookRatingRepository bookRatingRepository, BookRepository bookRepository, UserEntityRepository userEntityRepository) {
        this.bookRatingRepository = bookRatingRepository;
        this.bookRepository = bookRepository;
        this.userEntityRepository = userEntityRepository;
    }

    public boolean rateBook(Integer userId, Integer bookId, short rating) {
        var book = bookRepository.findById(bookId);
        var user = userEntityRepository.findById(userId);


        if (book.isPresent() && user.isPresent() && 1 <= rating && rating <= 5) {
            var bk = bookRatingRepository.findAllByBookAndUser(book.get(), user.get());
            var ids = new ArrayList<Long>();

            for(var bkItem : bk)
                ids.add(bkItem.getId());

            if(!ids.isEmpty())
                bookRatingRepository.deleteAllById(ids);

            var brating = new BookRating();
            brating.setRating(rating);
            brating.setBook(book.get());

            //TODO исправить на principal
            brating.setUser(user.get());

            bookRatingRepository.save(brating);
            return true;
        }
        return false;
    }

    public BookRatingDTO getRatingByBook(Book book) {
        var bookRating = bookRatingRepository.getBookRating(book.getId());
        var sum = 0;
        var bookRatingDTO = new BookRatingDTO();

        bookRatingDTO.setRating(bookRating.orElse(0));
        for (int i = 1; i <= 5; i++) {
            var bookRatingForX = bookRatingRepository.countUsersByRating(book.getId(), i);
            bookRatingDTO.getUsersCountByRating().put(i, bookRatingForX.orElse(0));
            sum += bookRatingForX.orElse(0);
        }
        bookRatingDTO.setCountSum(sum);

        return bookRatingDTO;
    }

}
