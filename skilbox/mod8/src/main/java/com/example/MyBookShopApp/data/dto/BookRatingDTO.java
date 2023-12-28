package com.example.MyBookShopApp.data.dto;

import java.util.HashMap;
import java.util.Map;

public class BookRatingDTO {

    private Integer rating;
    private Integer countSum;
    private Map<Integer, Integer> usersCountByRating = new HashMap<>();

    @Override
    public String toString() {
        return "BookRatingDTO{" +
                "rating=" + rating +
                ", countSum=" + countSum +
                ", usersCountByRating=" + usersCountByRating +
                '}';
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Map<Integer, Integer> getUsersCountByRating() {
        return usersCountByRating;
    }

    public void setUsersCountByRating(Map<Integer, Integer> usersCountByRating) {
        this.usersCountByRating = usersCountByRating;
    }

    public Integer getCountSum() {
        return countSum;
    }

    public void setCountSum(Integer countSum) {
        this.countSum = countSum;
    }
}
