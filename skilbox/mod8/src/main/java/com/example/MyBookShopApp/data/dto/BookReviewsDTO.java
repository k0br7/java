package com.example.MyBookShopApp.data.dto;

import com.example.MyBookShopApp.data.book.review.BookReviewEntity;
import com.example.MyBookShopApp.data.book.review.BookReviewLikeEntity;

public class BookReviewsDTO {

    private BookReviewEntity bookReviewEntity;
    private BookReviewLikeDTO bookReviewLikeDTO;

    public BookReviewsDTO(BookReviewEntity bookReviewEntity, BookReviewLikeDTO bookReviewLikeDTO) {
        this.bookReviewEntity = bookReviewEntity;
        this.bookReviewLikeDTO = bookReviewLikeDTO;
    }

    @Override
    public String toString() {
        return "BookReviewsDTO{" +
                "bookReviewEntity=" + bookReviewEntity +
                ", bookReviewLikeDTO=" + bookReviewLikeDTO +
                '}';
    }

    public BookReviewEntity getBookReviewEntity() {
        return bookReviewEntity;
    }

    public void setBookReviewEntity(BookReviewEntity bookReviewEntity) {
        this.bookReviewEntity = bookReviewEntity;
    }

    public BookReviewLikeDTO getBookReviewLikeDTO() {
        return bookReviewLikeDTO;
    }

    public void setBookReviewLikeDTO(BookReviewLikeDTO bookReviewLikeDTO) {
        this.bookReviewLikeDTO = bookReviewLikeDTO;
    }
}
