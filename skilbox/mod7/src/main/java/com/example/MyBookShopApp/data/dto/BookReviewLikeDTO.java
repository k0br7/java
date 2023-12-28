package com.example.MyBookShopApp.data.dto;

public class BookReviewLikeDTO {

    private long like;
    private long dislike;

    public BookReviewLikeDTO(long like, long dislike) {
        this.like = like;
        this.dislike = dislike;
    }

    @Override
    public String toString() {
        return "BookReviewLikeDTO{" +
                "like=" + like +
                ", dislike=" + dislike +
                '}';
    }

    public long getLike() {
        return like;
    }

    public void setLike(long like) {
        this.like = like;
    }

    public long getDislike() {
        return dislike;
    }

    public void setDislike(long dislike) {
        this.dislike = dislike;
    }
}
