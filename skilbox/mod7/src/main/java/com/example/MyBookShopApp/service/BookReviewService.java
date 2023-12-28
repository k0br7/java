package com.example.MyBookShopApp.service;

import com.example.MyBookShopApp.data.book.Book;
import com.example.MyBookShopApp.data.book.review.BookReviewEntity;
import com.example.MyBookShopApp.data.dto.BookReviewsDTO;
import com.example.MyBookShopApp.repository.BookReviewEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookReviewService {

    private final BookReviewEntityRepository bookReviewEntityRepository;

    @Autowired
    private BookReviewLikeEntityService bookReviewLikeEntityService;

    @Autowired
    public BookReviewService(BookReviewEntityRepository bookReviewEntityRepository) {
        this.bookReviewEntityRepository = bookReviewEntityRepository;
    }


    public List<BookReviewsDTO> getReviewByBook(Book book) {
        var reviews = bookReviewEntityRepository.findAllByBook(book);
        var reviewsDTO = new ArrayList<BookReviewsDTO>();

        for(var r : reviews){
            var likes = bookReviewLikeEntityService.getLikesByReview(r);
            var brdto = new BookReviewsDTO(r,likes);
            reviewsDTO.add(brdto);
        }

        return reviewsDTO;
    }

}


