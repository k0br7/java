package com.example.MyBookShopApp.repository;

import com.example.MyBookShopApp.data.book.review.BookReviewLikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookReviewLikeEntityRepository extends JpaRepository<BookReviewLikeEntity,Integer> {

    List<BookReviewLikeEntity> findAllByReviewId(int reviewId);
    long countByReviewIdAndValueGreaterThan(int reviewId, short value);
    long countByReviewIdAndValueLessThan(int reviewId, short value);

}
