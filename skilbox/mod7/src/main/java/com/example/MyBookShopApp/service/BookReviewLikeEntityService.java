package com.example.MyBookShopApp.service;

import com.example.MyBookShopApp.data.book.review.BookReviewEntity;
import com.example.MyBookShopApp.data.book.review.BookReviewLikeEntity;
import com.example.MyBookShopApp.data.dto.BookReviewLikeDTO;
import com.example.MyBookShopApp.repository.BookReviewLikeEntityRepository;
import org.springframework.stereotype.Service;

@Service
public class BookReviewLikeEntityService {

    private final BookReviewLikeEntityRepository bookReviewLikeEntityRepository;

    public BookReviewLikeEntityService(BookReviewLikeEntityRepository bookReviewLikeEntityRepository) {
        this.bookReviewLikeEntityRepository = bookReviewLikeEntityRepository;
    }

    public BookReviewLikeDTO getLikesByReview(BookReviewEntity bookReviewEntity){
        var like = bookReviewLikeEntityRepository.countByReviewIdAndValueGreaterThan(bookReviewEntity.getId(), (short) 0);
        var dislike = bookReviewLikeEntityRepository.countByReviewIdAndValueLessThan(bookReviewEntity.getId(), (short) 0);

        return new BookReviewLikeDTO(like,dislike);
    }

}
